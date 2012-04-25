package com.asistp.controller;

import com.asistp.domain.Worker;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/workers")
@Controller
@RooWebScaffold(path = "workers", formBackingObject = Worker.class)
public class WorkerController {
}
