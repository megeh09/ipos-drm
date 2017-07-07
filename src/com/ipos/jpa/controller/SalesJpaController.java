/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.jpa.controller;

import com.ipos.entity.Sales;
import com.ipos.jpa.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author megeh
 */
public class SalesJpaController implements Serializable {

    public SalesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sales sales) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sales sales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sales = em.merge(sales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sales.getId();
                if (findSales(id) == null) {
                    throw new NonexistentEntityException("The sales with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sales sales;
            try {
                sales = em.getReference(Sales.class, id);
                sales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sales with id " + id + " no longer exists.", enfe);
            }
            em.remove(sales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sales> findSalesEntities(String bodega) {
        return findSalesEntities(true, -1, -1, bodega);
    }

    public List<Sales> findSalesEntities(int maxResults, int firstResult, String bodega) {
        return findSalesEntities(false, maxResults, firstResult, bodega);
    }

    private List<Sales> findSalesEntities(boolean all, int maxResults, int firstResult, String bodega) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sales.class));
            Query q = em.createQuery(cq)
                    .setParameter("bodega", bodega);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Sales> findSales(String bodega) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Sales.findAll")
                    .setParameter("bodega", bodega)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Sales> findHotSales(String bodega) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Sales.findAllHot")
                    .setMaxResults(10)
                    .setParameter("bodega", bodega)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Sales> findSalesFromTo(Date from, Date to, String bodega) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Sales.findSalesFromTo")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("bodega", bodega)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Sales> findSalesFromToAndStock(Date from, Date to, Integer stockId, String bodega) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Sales.findSalesFromToAndStock")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("stockId", stockId)
                    .setParameter("bodega", bodega)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Sales findSales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sales.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sales> rt = cq.from(Sales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
