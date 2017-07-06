/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.jpa.controller;

import com.ipos.entity.StockWithdrawal;
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
public class StockWithdrawalJpaController implements Serializable {

    public StockWithdrawalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StockWithdrawal stockWithdrawal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(stockWithdrawal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StockWithdrawal stockWithdrawal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            stockWithdrawal = em.merge(stockWithdrawal);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = stockWithdrawal.getId();
                if (findStockWithdrawal(id) == null) {
                    throw new NonexistentEntityException("The stockWithdrawal with id " + id + " no longer exists.");
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
            StockWithdrawal stockWithdrawal;
            try {
                stockWithdrawal = em.getReference(StockWithdrawal.class, id);
                stockWithdrawal.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The stockWithdrawal with id " + id + " no longer exists.", enfe);
            }
            em.remove(stockWithdrawal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StockWithdrawal> findStockWithdrawalEntities() {
        return findStockWithdrawalEntities(true, -1, -1);
    }

    public List<StockWithdrawal> findStockWithdrawalEntities(int maxResults, int firstResult) {
        return findStockWithdrawalEntities(false, maxResults, firstResult);
    }

    private List<StockWithdrawal> findStockWithdrawalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StockWithdrawal.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<StockWithdrawal> findStockWithdrawalFromTo(Date from, Date to) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("StockWithdrawal.findStockWithdrawalFromTo")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<StockWithdrawal> findStockWithdrawalFromToAndStock(Date from, Date to, Integer stockId) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("StockWithdrawal.findStockWithdrawalFromToAndStock")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("stockId", stockId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public StockWithdrawal findStockWithdrawal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StockWithdrawal.class, id);
        } finally {
            em.close();
        }
    }

    public int getStockWithdrawalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StockWithdrawal> rt = cq.from(StockWithdrawal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
