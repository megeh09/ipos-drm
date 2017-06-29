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
@Table(name = "Sales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sales.findAll", query = "SELECT s FROM Sales s"),
    @NamedQuery(name = "Sales.findAllHot", query = "SELECT s FROM Sales s ORDER BY s.quantity ASC, s.totalAmount ASC"),
    @NamedQuery(name = "Sales.findById", query = "SELECT s FROM Sales s WHERE s.id = :id"),
    @NamedQuery(name = "Sales.findByQuantity", query = "SELECT s FROM Sales s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "Sales.findByUnitPrice", query = "SELECT s FROM Sales s WHERE s.unitPrice = :unitPrice"),
    @NamedQuery(name = "Sales.findByCashAmount", query = "SELECT s FROM Sales s WHERE s.cashAmount = :cashAmount"),
    @NamedQuery(name = "Sales.findByChangeAmount", query = "SELECT s FROM Sales s WHERE s.changeAmount = :changeAmount"),
    @NamedQuery(name = "Sales.findByTotalAmount", query = "SELECT s FROM Sales s WHERE s.totalAmount = :totalAmount"),
    @NamedQuery(name = "Sales.findByDate", query = "SELECT s FROM Sales s WHERE s.date = :date"),
    @NamedQuery(name = "Sales.findByCreatedOn", query = "SELECT s FROM Sales s WHERE s.createdOn = :createdOn"),
    @NamedQuery(name = "Sales.findByUpdatedOn", query = "SELECT s FROM Sales s WHERE s.updatedOn = :updatedOn"),
    @NamedQuery(name = "Sales.findByFKstockId", query = "SELECT s FROM Sales s WHERE s.fKstockId = :fKstockId"),
    @NamedQuery(name = "Sales.findByFKcreatedByUserId", query = "SELECT s FROM Sales s WHERE s.fKcreatedByUserId = :fKcreatedByUserId"),
    @NamedQuery(name = "Sales.findSalesFromTo", query = "SELECT s FROM Sales s WHERE s.date BETWEEN :from AND :to")})
public class Sales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "quantity")
    private BigDecimal quantity;
    @Basic(optional = false)
    @Column(name = "unitPrice")
    private BigDecimal unitPrice;
    @Basic(optional = false)
    @Column(name = "cashAmount")
    private BigDecimal cashAmount;
    @Basic(optional = false)
    @Column(name = "changeAmount")
    private BigDecimal changeAmount;
    @Basic(optional = false)
    @Column(name = "totalAmount")
    private BigDecimal totalAmount;
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
    @Column(name = "FK_stockId")
    private int fKstockId;
    @Basic(optional = false)
    @Column(name = "FK_createdByUserId")
    private int fKcreatedByUserId;

    public Sales() {
    }

    public Sales(Integer id) {
        this.id = id;
    }

    public Sales(Integer id, BigDecimal quantity, BigDecimal unitPrice, BigDecimal cashAmount, BigDecimal changeAmount, BigDecimal totalAmount, Date date, Date createdOn, Date updatedOn, int fKstockId, int fKcreatedByUserId) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.cashAmount = cashAmount;
        this.changeAmount = changeAmount;
        this.totalAmount = totalAmount;
        this.date = date;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.fKstockId = fKstockId;
        this.fKcreatedByUserId = fKcreatedByUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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

    public int getFKstockId() {
        return fKstockId;
    }

    public void setFKstockId(int fKstockId) {
        this.fKstockId = fKstockId;
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
        if (!(object instanceof Sales)) {
            return false;
        }
        Sales other = (Sales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipos.entity.Sales[ id=" + id + " ]";
    }
    
}
