package com.asistp.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.asistp.assistance.utils.Constantes;
import com.asistp.assistance.utils.Conversiones;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Assistance {

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar dateAssistance;

    @ManyToOne
    @JoinColumn(name = "id_worker")
    private Worker worker;

    private String status;

    private String hourLimitReference;

    public void register() throws Exception {
    	this.defineStatus();
    	this.validateOneAssistanceForDay();
        this.persist();
    }

    private void validateOneAssistanceForDay() throws Exception {
    	
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	
    	GregorianCalendar now = new GregorianCalendar();
    	
    	GregorianCalendar nowInitDay = Conversiones.getDateTimeInitialByDay(now);
    	GregorianCalendar nowFinishDay = Conversiones.getDateTimeFinishByDay(now);
    	
    	    	
    	Query query= entityManager().createQuery("SELECT o FROM Assistance o where o.dateAssistance BETWEEN :arg1 and :arg2 and o.worker.id = :arg3 ",Assistance.class);
    	query.setParameter("arg1", nowInitDay , TemporalType.TIMESTAMP );
    	query.setParameter("arg2", nowFinishDay , TemporalType.TIMESTAMP);
    	query.setParameter("arg3", this.getWorker().getId() );
    	List<Assistance> listResult = query.getResultList();
    	
    	if (listResult.size()>0){
    		Assistance assistanceResult = (Assistance)listResult.get(0);        	
        	String hourRegistered = Conversiones.parserCalendarToHoraString(assistanceResult.getDateAssistance());        	
    		throw new Exception("Your assistance is registered at "+hourRegistered +" today");
    	}
	}

	public void defineStatus() {
        Schedule schedule = worker.getSchedule();
        int hourLateReference = schedule.getHourLimit();
        int minuteLateReference = schedule.getMinuteHourLimit();
        int hourAssistance = dateAssistance.get(Calendar.HOUR_OF_DAY);
        int minuteAssistance = dateAssistance.get(Calendar.MINUTE);
        
        String hourLateReferenceString = "00" + hourLateReference;
        String minuteLateReferenceString = "00" + minuteLateReference;
        
        hourLateReferenceString = hourLateReferenceString.substring(hourLateReferenceString.length()-2, hourLateReferenceString.length()); 
        minuteLateReferenceString= minuteLateReferenceString.substring(minuteLateReferenceString.length()-2, minuteLateReferenceString.length());
        
        hourLimitReference=hourLateReferenceString+":"+minuteLateReferenceString;
        
        if (hourAssistance > hourLateReference) {
            status=Constantes.ASISTENCIA_STATUS_VALUE_TARDE;
            return;
        }
        if (hourAssistance == hourLateReference && minuteAssistance > minuteLateReference) {
        	status=Constantes.ASISTENCIA_STATUS_VALUE_TARDE;
        	return;
        }
        status=Constantes.ASISTENCIA_STATUS_VALUE_TEMPRANO;
    }
	
//	public static Assistance findByUsernameAndDate(String worker_id, Calendar dateAssistance) {
//		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		return (entityManager().createQuery("SELECT o FROM Assitance o where o.worker_id="+worker_id+" and o.dateAssistance ='"+ df.format(dateAssistance.getTime()) +"'" , Assistance.class).getResultList()).get(0);
//	}
}
