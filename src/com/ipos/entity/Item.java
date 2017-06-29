/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.entity;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author megeh
 */
@Entity
@Table(name = "Item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findAllOrderByDesc", query = "SELECT i FROM Item i ORDER BY i.id DESC"),
    @NamedQuery(name = "Item.findById", query = "SELECT i FROM Item i WHERE i.id = :id"),
    @NamedQuery(name = "Item.findByCode", query = "SELECT i FROM Item i WHERE i.code = :code"),
    @NamedQuery(name = "Item.findByStockCardNumber", query = "SELECT i FROM Item i WHERE i.stockCardNumber = :stockCardNumber"),
    @NamedQuery(name = "Item.findByName", query = "SELECT i FROM Item i WHERE i.name = :name"),
    @NamedQuery(name = "Item.findByDescription", query = "SELECT i FROM Item i WHERE i.description = :description"),
    @NamedQuery(name = "Item.findByDate", query = "SELECT i FROM Item i WHERE i.date = :date"),
    @NamedQuery(name = "Item.findByCreatedOn", query = "SELECT i FROM Item i WHERE i.createdOn = :createdOn"),
    @NamedQuery(name = "Item.findByUpdatedOn", query = "SELECT i FROM Item i WHERE i.updatedOn = :updatedOn"),
    @NamedQuery(name = "Item.findByFKunitId", query = "SELECT i FROM Item i WHERE i.fKunitId = :fKunitId"),
    @NamedQuery(name = "Item.findByFKcreatedByUserId", query = "SELECT i FROM Item i WHERE i.fKcreatedByUserId = :fKcreatedByUserId")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "stockCardNumber")
    private String stockCardNumber;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "createdOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Basic(optional = false)
    @Column(name = "updatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    @Basic(optional = false)
    @Column(name = "FK_unitId")
    private int fKunitId;
    @Basic(optional = false)
    @Column(name = "FK_createdByUserId")
    private int fKcreatedByUserId;

    public Item() {
    }

    public Item(Integer id) {
        this.id = id;
    }

    public Item(Integer id, String code, String stockCardNumber, String name, String description, Date date, Date createdOn, Date updatedOn, int fKunitId, int fKcreatedByUserId) {
        this.id = id;
        this.code = code;
        this.stockCardNumber = stockCardNumber;
        this.name = name;
        this.description = description;
        this.date = date;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.fKunitId = fKunitId;
        this.fKcreatedByUserId = fKcreatedByUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStockCardNumber() {
        return stockCardNumber;
    }

    public void setStockCardNumber(String stockCardNumber) {
        this.stockCardNumber = stockCardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public int getFKunitId() {
        return fKunitId;
    }

    public void setFKunitId(int fKunitId) {
        this.fKunitId = fKunitId;
    }

    public int getFKcreatedByUserId() {
        return fKcreatedByUserId;
    }

    public void setFKcreatedByUserId(int fKcreatedByUserId) {
        this.fKcreatedByUserId = fKcreatedByUserId;
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
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
