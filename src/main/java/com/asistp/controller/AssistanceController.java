package com.asistp.controller;

import com.asistp.domain.Assistance;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/assistances")
@Controller
@RooWebScaffold(path = "assistances", formBackingObject = Assistance.class)
public class AssistanceController {
}
