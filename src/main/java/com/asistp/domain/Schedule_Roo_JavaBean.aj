// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.asistp.domain;

import com.asistp.domain.Schedule;
import com.asistp.domain.Worker;
import java.util.Set;

privileged aspect Schedule_Roo_JavaBean {
    
    public String Schedule.getName() {
        return this.name;
    }
    
    public void Schedule.setName(String name) {
        this.name = name;
    }
    
    public Set<Worker> Schedule.getWorkers() {
        return this.workers;
    }
    
    public void Schedule.setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }
    
    public int Schedule.getHourLimit() {
        return this.hourLimit;
    }
    
    public void Schedule.setHourLimit(int hourLimit) {
        this.hourLimit = hourLimit;
    }
    
    public int Schedule.getMinuteHourLimit() {
        return this.minuteHourLimit;
    }
    
    public void Schedule.setMinuteHourLimit(int minuteHourLimit) {
        this.minuteHourLimit = minuteHourLimit;
    }
    
}
