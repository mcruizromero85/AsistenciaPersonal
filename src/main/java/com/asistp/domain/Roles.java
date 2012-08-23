package com.asistp.domain;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Roles {

    @ManyToOne
    @JoinColumn(name = "id_worker")
    private Worker worker;

    @NotNull
    @Value("ROLE_USER")
    private String name;
    
    public static List<Roles> findByField(String field, String value) {
    	return entityManager().createQuery("SELECT r FROM Roles r where " + field + "='" + value + "'",Roles.class).getResultList();
    }
}
