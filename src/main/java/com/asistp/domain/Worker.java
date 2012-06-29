package com.asistp.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Worker {

    @NotNull
    @Column(unique = true)
    @Size(min = 8)
    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker")
    private Set<Assistance> asistencias = new HashSet<Assistance>();

    @ManyToOne
    @JoinColumn(name = "id_schedule")
    private Schedule schedule;

    
	public static Worker findByField(String field, String username) {
		return (entityManager().createQuery("SELECT o FROM Worker o where o."+field+"='"+username+"'", Worker.class).getResultList()).get(0);
	}
}
