package com.asistp.controller;

import com.asistp.domain.Schedule;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/schedules")
@Controller
@RooWebScaffold(path = "schedules", formBackingObject = Schedule.class)
public class ScheduleController {
}
