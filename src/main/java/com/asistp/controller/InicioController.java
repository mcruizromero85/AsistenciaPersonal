package com.asistp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asistp.asistencia.utils.Conversiones;
import com.asistp.domain.Asistencia;
import com.asistp.domain.Usuario;

@RequestMapping("/index")
@Controller
public class InicioController {
	
	private Logger logger = Logger.getLogger(InicioController.class);
	
    @RequestMapping (produces = "text/html"  )
    public String show(Model uiModel) {
    	
    	User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	uiModel.addAttribute("usuarioLogueado", usuario);
    	
        return "index";
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String registroAsistencia(Model uiModel, HttpServletRequest httpServletRequest) {
        
    	GregorianCalendar fechaRegistroAsistencia = new GregorianCalendar();
    	SimpleDateFormat df = new SimpleDateFormat("HH:mm");
    	String fechaAsistenciaAuxiliar = httpServletRequest.getParameter("fechaAsistenciaAuxiliarPruebas");
    	
    	    	
    	if (fechaAsistenciaAuxiliar != null){
    		try {
    			fechaRegistroAsistencia = Conversiones.parserHoraFromStringToGregorianCalendar(fechaAsistenciaAuxiliar);
    		} catch (ParseException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    	}
    	
    	User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	Usuario objUsuario = new Usuario();
//    	objUsuario.setLogin(usuario.getUsername());
    	
    	Asistencia objAsistencia = new Asistencia();
    	objAsistencia.setFechaHoraAsistencia(fechaRegistroAsistencia);
    	objAsistencia.setUsuario(objUsuario);
    	objAsistencia.registrarAsistencia();
    	
    	uiModel.addAttribute("horaRegistroAsistencia", df.format(fechaRegistroAsistencia.getTime()));
    	
        return "index";
    }
    
}
