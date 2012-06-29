package com.asistp.domain;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Schedule {

    @NotNull
    @Column(unique = true)
    @Size(min = 10, max = 50)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule")
    private Set<Worker> workers = new HashSet<Worker>();

    @Value("9")
    @Min(7L)
    @Max(12L)
    @Digits(integer = 2, fraction = 0)
    private int hourLimit;

    @Value("0")
    @Min(0L)
    @Max(59L)
    @Digits(integer = 2, fraction = 0)
    private int minuteHourLimit;
}
