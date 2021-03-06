// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.asistp.controller;

import com.asistp.controller.ScheduleController;
import com.asistp.domain.Schedule;
import com.asistp.domain.Worker;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect ScheduleController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String ScheduleController.create(@Valid Schedule schedule, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, schedule);
            return "schedules/create";
        }
        uiModel.asMap().clear();
        schedule.persist();
        return "redirect:/schedules/" + encodeUrlPathSegment(schedule.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String ScheduleController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Schedule());
        return "schedules/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String ScheduleController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("schedule", Schedule.findSchedule(id));
        uiModel.addAttribute("itemId", id);
        return "schedules/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String ScheduleController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("schedules", Schedule.findScheduleEntries(firstResult, sizeNo));
            float nrOfPages = (float) Schedule.countSchedules() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("schedules", Schedule.findAllSchedules());
        }
        return "schedules/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String ScheduleController.update(@Valid Schedule schedule, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, schedule);
            return "schedules/update";
        }
        uiModel.asMap().clear();
        schedule.merge();
        return "redirect:/schedules/" + encodeUrlPathSegment(schedule.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String ScheduleController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Schedule.findSchedule(id));
        return "schedules/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String ScheduleController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Schedule schedule = Schedule.findSchedule(id);
        schedule.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/schedules";
    }
    
    void ScheduleController.populateEditForm(Model uiModel, Schedule schedule) {
        uiModel.addAttribute("schedule", schedule);
        uiModel.addAttribute("workers", Worker.findAllWorkers());
    }
    
    String ScheduleController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
