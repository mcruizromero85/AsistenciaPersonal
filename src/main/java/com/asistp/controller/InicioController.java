package com.asistp.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/index")
@Controller
public class InicioController {
	
    @RequestMapping (produces = "text/html" )
    public String show(Model uiModel) {
    	
    	User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	uiModel.addAttribute("usuarioLogueado", usuario);
    	
        return "index";
    }
    
}
