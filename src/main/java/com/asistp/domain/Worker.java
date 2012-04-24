package com.asistp.domain;

import javax.persistence.Column;
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
    private String login;

    @NotNull
    @Size(min = 8)
    private String password;

    private Assistance assistances;
}