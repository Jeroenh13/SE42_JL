package auction.service;

import java.util.*;
import auction.domain.Account;
import auction.dao.UserDAOCollectionImpl;
import auction.dao.UserDAO;
import auction.dao.UserDAOJPAImpl;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RegistrationMgr {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");

    /**
     * Registreert een gebruiker met het als parameter gegeven e-mailadres, mits
     * zo'n gebruiker nog niet bestaat.
     * @param email
     * @return Een Userobject dat geïdentificeerd wordt door het gegeven
     * e-mailadres (nieuw aangemaakt of reeds bestaand). Als het e-mailadres
     * onjuist is ( het bevat geen '@'-teken) wordt null teruggegeven.
     */
    public Account registerUser(String email) {
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO = new UserDAOJPAImpl(em);
        em.getTransaction().begin();
        if (!email.contains("@")) {
            return null;
        }
        Account user = new Account(email);
        try{
            userDAO.create(user);
            em.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }

    /**
     *
     * @param email een e-mailadres
     * @return Het Userobject dat geïdentificeerd wordt door het gegeven
     * e-mailadres of null als zo'n User niet bestaat.
     */
    public Account getUser(String email) {
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO = new UserDAOJPAImpl(em);
        Account user = null;
        em.getTransaction().begin();
        try{
            user = userDAO.findByEmail(email);
            em.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }

    /**
     * @return Een iterator over alle geregistreerde gebruikers
     */
    public List<Account> getUsers() {
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO = new UserDAOJPAImpl(em);
        List<Account> users = null;
        em.getTransaction().begin();
        try{
            users = userDAO.findAll();
            em.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return users;
    }
}
