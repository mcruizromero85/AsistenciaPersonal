// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.asistp.domain;

import com.asistp.domain.Worker;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Worker_Roo_Jpa_Entity {
    
    declare @type: Worker: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Worker.id;
    
    @Version
    @Column(name = "version")
    private Integer Worker.version;
    
    public Long Worker.getId() {
        return this.id;
    }
    
    public void Worker.setId(Long id) {
        this.id = id;
    }
    
    public Integer Worker.getVersion() {
        return this.version;
    }
    
    public void Worker.setVersion(Integer version) {
        this.version = version;
    }
    
}