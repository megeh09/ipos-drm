/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author megeh
 */
@Entity
@Table(name = "StockWithdrawal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockWithdrawal.findAll", query = "SELECT s FROM StockWithdrawal s"),
    @NamedQuery(name = "StockWithdrawal.findById", query = "SELECT s FROM StockWithdrawal s WHERE s.id = :id"),
    @NamedQuery(name = "StockWithdrawal.findByPurpose", query = "SELECT s FROM StockWithdrawal s WHERE s.purpose = :purpose"),
    @NamedQuery(name = "StockWithdrawal.findByQuantity", query = "SELECT s FROM StockWithdrawal s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "StockWithdrawal.findByDate", query = "SELECT s FROM StockWithdrawal s WHERE s.date = :date"),
    @NamedQuery(name = "StockWithdrawal.findByCreatedOn", query = "SELECT s FROM StockWithdrawal s WHERE s.createdOn = :createdOn"),
    @NamedQuery(name = "StockWithdrawal.findByUpdatedOn", query = "SELECT s FROM StockWithdrawal s WHERE s.updatedOn = :updatedOn"),
    @NamedQuery(name = "StockWithdrawal.findStockWithdrawalFromTo", query = "SELECT s FROM StockWithdrawal s WHERE s.date BETWEEN :from AND :to"),
    @NamedQuery(name = "StockWithdrawal.findStockWithdrawalFromToAndStock", query = "SELECT s FROM StockWithdrawal s WHERE s.stock.id = :stockId AND s.date BETWEEN :from AND :to")})
public class StockWithdrawal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "purpose")
    private String purpose;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "quantity")
    private BigDecimal quantity;
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
    @OneToOne
    @JoinColumn(name = "FK_stockId")
    private Stock stock;
    @OneToOne
    @JoinColumn(name = "FK_personnelId")
    private Personnel personnel;
    @OneToOne
    @JoinColumn(name = "FK_createdByUserId")
    private User user;

    public StockWithdrawal() {
    }

    public StockWithdrawal(Integer id) {
        this.id = id;
    }

    public StockWithdrawal(Integer id, String purpose, BigDecimal quantity, Date date, Date createdOn, Date updatedOn, Stock stock, Personnel personnel, User user) {
        this.id = id;
        this.purpose = purpose;
        this.quantity = quantity;
        this.date = date;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.stock = stock;
        this.personnel = personnel;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof StockWithdrawal)) {
            return false;
        }
        StockWithdrawal other = (StockWithdrawal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return purpose;
    }
    
}
