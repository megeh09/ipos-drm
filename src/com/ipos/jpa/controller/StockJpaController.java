/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.jpa.controller;

import com.ipos.entity.Item;
import com.ipos.entity.Stock;
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
public class StockJpaController implements Serializable {

    public StockJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Stock stock) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(stock);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Stock stock) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            stock = em.merge(stock);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = stock.getId();
                if (findStock(id) == null) {
                    throw new NonexistentEntityException("The stock with id " + id + " no longer exists.");
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
            Stock stock;
            try {
                stock = em.getReference(Stock.class, id);
                stock.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The stock with id " + id + " no longer exists.", enfe);
            }
            em.remove(stock);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Stock> findStockEntities(String bodega) {
        return findStockEntities(true, -1, -1, bodega);
    }

    public List<Stock> findStockEntities(int maxResults, int firstResult, String bodega) {
        return findStockEntities(false, maxResults, firstResult, bodega);
    }

    private List<Stock> findStockEntities(boolean all, int maxResults, int firstResult, String bodega) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Stock.class));
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

    public List<Stock> findStocks(String bodega) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Stock.findAll")
                    .setParameter("bodega", bodega)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Stock> findStockEntitiesOrderByIdDesc() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Stock.findAllOrderByIdDesc").getResultList();
        } finally {
            em.close();
        }
    }

    public List<Stock> findStock(Item item, String bodega) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Stock.findByItemId")
                    .setParameter("itemId", item.getId())
                    .setParameter("bodega", bodega)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Stock> findAlmostOutOfStockWithLimit(Integer maxQuantity, String bodega) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Stock.findAlmostOutOfStockWithLimit")
                    .setParameter("quantityLimit", maxQuantity)
                    .setParameter("bodega", bodega)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Stock> findStocksFromTo(Date from, Date to, String bodega) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Stock.findStocksFromTo")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("bodega", bodega)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Stock> findStocksFromToAndStock(Date from, Date to, Integer stockId, String bodega) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Stock.findStocksFromToAndStock")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("stockId", stockId)
                    .setParameter("bodega", bodega)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Stock findStock(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Stock.class, id);
        } finally {
            em.close();
        }
    }

    public int getStockCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Stock> rt = cq.from(Stock.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
