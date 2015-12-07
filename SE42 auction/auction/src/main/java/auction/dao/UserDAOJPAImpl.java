package auction.dao;

import auction.domain.Account;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class UserDAOJPAImpl implements UserDAO {

    private final EntityManager em;
    
    public UserDAOJPAImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public int count() {
        Query q = em.createNamedQuery("User.count",Account.class);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(Account user) {
        em.persist(user);
    }

    @Override
    public void edit(Account user) {
        em.merge(user);
    }


    @Override
    public List<Account> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Account.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public Account findByEmail(String email) {
        Query q = em.createNamedQuery("User.findByEmail", Account.class);
        q.setParameter("email", email);
        return (Account) q.getSingleResult();
    }

    @Override
    public void remove(Account user) {
        em.remove(em.merge(user));
    }
}
