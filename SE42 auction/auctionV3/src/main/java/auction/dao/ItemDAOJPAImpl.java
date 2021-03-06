/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.dao;

import auction.domain.Account;
import auction.domain.Item;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Jeroen Hendriks
 */
public class ItemDAOJPAImpl implements ItemDAO {

    EntityManager em;

    public ItemDAOJPAImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public int count() {
        Query q = em.createNamedQuery("Item.count", Item.class);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(Item item) {
        em.persist(item);
    }

    @Override
    public void edit(Item item) {
        em.merge(item);
    }

    @Override
    public Item find(Long id) {
        Query q = em.createNamedQuery("Item.findByID", Item.class);
        q.setParameter("id", id);
        return (Item) q.getSingleResult();
    }

    @Override
    public List<Item> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Item.class));
        return new ArrayList(em.createQuery(cq).getResultList());
    }

    @Override
    public ArrayList<Item> findByDescription(String description) {
        Query q = em.createNamedQuery("Item.findByDescription");
        q.setParameter("description", description);
        return new ArrayList(q.getResultList());
    }

    @Override
    public void remove(Item item) {
        em.remove(item);
    }
}
