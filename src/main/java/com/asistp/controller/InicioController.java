package com.asistp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit.ItalicAction;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asistp.assistance.utils.Constantes;
import com.asistp.assistance.utils.Conversiones;
import com.asistp.domain.Assistance;
import com.asistp.domain.Worker;

@RequestMapping("/index")
@Controller
public class InicioController {
	
	private Logger logger = Logger.getLogger(InicioController.class);
	
    @RequestMapping (produces = "text/html"  )
    public String show(Model uiModel) {
    	
    	User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	uiModel.addAttribute("usuarioLogueado", usuario);
    	showAssistanceList(uiModel);
    	
    	Collection collectionAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    	Object[] objArray=collectionAuthorities.toArray();
    	SimpleGrantedAuthority simpleGrantedAuthority=(SimpleGrantedAuthority)objArray[0];
    	
    	if (simpleGrantedAuthority.getAuthority().equals(Constantes.SECURITY_ROLE_VALUE_ROLE_ADMIN)){
    		return "indexAdmin";
    	}else{
    		return "index";
    	}
    }
    

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String registroAsistencia(Model uiModel, HttpServletRequest httpServletRequest) {
		
		showAssistanceList(uiModel);
		
    	GregorianCalendar fechaRegistroAsistencia = new GregorianCalendar();
    	SimpleDateFormat df = new SimpleDateFormat("HH:mm");
    	String dateAssistanceTestForHour = httpServletRequest.getParameter("dateAssistanceTestForHour");
    	    	    	
    	if (dateAssistanceTestForHour != null){
    		try {
    			fechaRegistroAsistencia = Conversiones.parserHoraFromStringToGregorianCalendar(dateAssistanceTestForHour);
    		} catch (ParseException e1) {
    			logger.info(e1,e1);
    		}
    	}
    	
    	User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	String username=usuario.getUsername();
    	Worker objWorker =null;
    	try{
    		objWorker = Worker.findByField("username",username);
    	}catch (Exception e) {
    		uiModel.addAttribute("horaRegistroAsistencia", "User: "+username + " not registered in Workers'registers");
    		return "index";
		}
    	
    	uiModel.addAttribute("usuarioLogueado", usuario);
    	Assistance objAsistencia = new Assistance();
    	objAsistencia.setDateAssistance(fechaRegistroAsistencia);
    	objAsistencia.setWorker(objWorker);
    	try {
			objAsistencia.register();
		} catch (Exception e) {
			logger.error(e,e);
			uiModel.addAttribute("messageError",e.getMessage());
			return "index";
		}
    	
    	uiModel.addAttribute("horaRegistroAsistencia", "Assistance Registered at "+df.format(fechaRegistroAsistencia.getTime()));
    	    	
        return "index";
    }
    
    private void showAssistanceList(Model uiModel) {
    	uiModel.addAttribute("assistances", Assistance.findAllAssistances());
        addDateTimeFormatPatterns(uiModel);
	}
    
    private void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("assistance_dateassistance_date_format", "yyyy-MM-dd hh:mm:ss");
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("assistance", Assistance.findAssistance(id));
        uiModel.addAttribute("itemId", id);
        return "assistances/showReadOnly";
    }
}
