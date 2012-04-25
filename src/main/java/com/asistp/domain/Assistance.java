package com.asistp.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Assistance {
	
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Calendar dateAssistance;

    @ManyToOne
    @JoinColumn(name = "id_worker")
    private Worker worker;

    public void register() {
         this.persist();
    }
    
    public boolean getEarly(){
    	
    	Schedule schedule = worker.getSchedule();
    	GregorianCalendar hourLateReferenceGc= (GregorianCalendar) schedule.getHourLate();
    	
    	int hourLateReference = hourLateReferenceGc.get(Calendar.HOUR_OF_DAY);
    	int minuteLateReference = hourLateReferenceGc.get(Calendar.MINUTE);
    	
    	int hourAssistance  = dateAssistance.get(Calendar.HOUR_OF_DAY);
    	int minuteAssistance = dateAssistance.get(Calendar.MINUTE);
    	
    	if (hourAssistance > hourLateReference ){
    		return false;
    	}
    	
    	if (hourAssistance == hourLateReference && minuteAssistance > minuteLateReference ){
    		return false;    		
    	}
    	
    	return true;
    }
}
