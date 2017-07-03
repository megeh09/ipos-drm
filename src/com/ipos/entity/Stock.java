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
@Table(name = "Stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s"),
    @NamedQuery(name = "Stock.findAllOrderByIdDesc", query = "SELECT s FROM Stock s ORDER BY s.id DESC"),
    @NamedQuery(name = "Stock.findById", query = "SELECT s FROM Stock s WHERE s.id = :id"),
    @NamedQuery(name = "Stock.findByCode", query = "SELECT s FROM Stock s WHERE s.code = :code"),
    @NamedQuery(name = "Stock.findByStockCardNumber", query = "SELECT s FROM Stock s WHERE s.stockCardNumber = :stockCardNumber"),
    @NamedQuery(name = "Stock.findByQuantity", query = "SELECT s FROM Stock s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "Stock.findByUnitPrice", query = "SELECT s FROM Stock s WHERE s.unitPrice = :unitPrice"),
    @NamedQuery(name = "Stock.findByIsExpirable", query = "SELECT s FROM Stock s WHERE s.isExpirable = :isExpirable"),
    @NamedQuery(name = "Stock.findByExpiryDate", query = "SELECT s FROM Stock s WHERE s.expiryDate = :expiryDate"),
    @NamedQuery(name = "Stock.findByDate", query = "SELECT s FROM Stock s WHERE s.date = :date"),
    @NamedQuery(name = "Stock.findByCreatedOn", query = "SELECT s FROM Stock s WHERE s.createdOn = :createdOn"),
    @NamedQuery(name = "Stock.findByUpdatedOn", query = "SELECT s FROM Stock s WHERE s.updatedOn = :updatedOn"),
    @NamedQuery(name = "Stock.findByFKsupplierId", query = "SELECT s FROM Stock s WHERE s.fKsupplierId = :fKsupplierId"),
    @NamedQuery(name = "Stock.findByFKpersonnelId", query = "SELECT s FROM Stock s WHERE s.fKpersonnelId = :fKpersonnelId"),
    @NamedQuery(name = "Stock.findByFKitemId", query = "SELECT s FROM Stock s WHERE s.fKitemId = :fKitemId"),
    @NamedQuery(name = "Stock.findByFKcreatedByUserId", query = "SELECT s FROM Stock s WHERE s.fKcreatedByUserId = :fKcreatedByUserId"),
    @NamedQuery(name = "Stock.findAlmostOutOfStockWithLimit", query = "SELECT s FROM Stock s WHERE s.quantity <= :quantityLimit"),
    @NamedQuery(name = "Stock.findStocksFromTo", query = "SELECT s FROM Stock s WHERE s.date BETWEEN :from AND :to")})
public class Stock implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "quantity")
    private BigDecimal quantity;
    @Basic(optional = false)
    @Column(name = "unitPrice")
    private BigDecimal unitPrice;
    @Basic(optional = false)
    @Column(name = "isExpirable")
    private boolean isExpirable;
    @Column(name = "expiryDate")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
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
    @Column(name = "FK_supplierId")
    private int fKsupplierId;
    @Basic(optional = false)
    @Column(name = "FK_personnelId")
    private int fKpersonnelId;
    @Basic(optional = false)
    @Column(name = "FK_itemId")
    private int fKitemId;
    @Basic(optional = false)
    @Column(name = "FK_createdByUserId")
    private int fKcreatedByUserId;

    public Stock() {
    }

    public Stock(Integer id) {
        this.id = id;
    }

    public Stock(Integer id, String code, String stockCardNumber, BigDecimal quantity, BigDecimal unitPrice, boolean isExpirable, Date date, Date createdOn, Date updatedOn, int fKsupplierId, int fKpersonnelId, int fKitemId, int fKcreatedByUserId) {
        this.id = id;
        this.code = code;
        this.stockCardNumber = stockCardNumber;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.isExpirable = isExpirable;
        this.date = date;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.fKsupplierId = fKsupplierId;
        this.fKpersonnelId = fKpersonnelId;
        this.fKitemId = fKitemId;
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

    public boolean getIsExpirable() {
        return isExpirable;
    }

    public void setIsExpirable(boolean isExpirable) {
        this.isExpirable = isExpirable;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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

    public int getFKsupplierId() {
        return fKsupplierId;
    }

    public void setFKsupplierId(int fKsupplierId) {
        this.fKsupplierId = fKsupplierId;
    }

    public int getFKpersonnelId() {
        return fKpersonnelId;
    }

    public void setFKpersonnelId(int fKpersonnelId) {
        this.fKpersonnelId = fKpersonnelId;
    }

    public int getFKitemId() {
        return fKitemId;
    }

    public void setFKitemId(int fKitemId) {
        this.fKitemId = fKitemId;
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
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return code;
    }
    
}
