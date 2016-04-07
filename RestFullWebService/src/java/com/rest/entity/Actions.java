/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bunna Kal
 */
@Entity
@Table(name = "actions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actions.findAll", query = "SELECT a FROM Actions a"),
    @NamedQuery(name = "Actions.findById", query = "SELECT a FROM Actions a WHERE a.id = :id"),
    @NamedQuery(name = "Actions.findByActionDate", query = "SELECT a FROM Actions a WHERE a.actionDate = :actionDate"),
    @NamedQuery(name = "Actions.findByActionStatus", query = "SELECT a FROM Actions a WHERE a.actionStatus = :actionStatus"),
    @NamedQuery(name = "Actions.findByActionBy", query = "SELECT a FROM Actions a WHERE a.actionBy = :actionBy"),
    @NamedQuery(name = "Actions.findByActionOn", query = "SELECT a FROM Actions a WHERE a.actionOn = :actionOn")})
public class Actions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actionDate")
    @Temporal(TemporalType.DATE)
    private Date actionDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "actionStatus")
    private String actionStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actionBy")
    private long actionBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actionOn")
    private long actionOn;

    public Actions() {
    }

    public Actions(Integer id) {
        this.id = id;
    }

    public Actions(Integer id, Date actionDate, String actionStatus, long actionBy, long actionOn) {
        this.id = id;
        this.actionDate = actionDate;
        this.actionStatus = actionStatus;
        this.actionBy = actionBy;
        this.actionOn = actionOn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public long getActionBy() {
        return actionBy;
    }

    public void setActionBy(long actionBy) {
        this.actionBy = actionBy;
    }

    public long getActionOn() {
        return actionOn;
    }

    public void setActionOn(long actionOn) {
        this.actionOn = actionOn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actions)) {
            return false;
        }
        Actions other = (Actions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rest.entity.Actions[ id=" + id + " ]";
    }
    
}
