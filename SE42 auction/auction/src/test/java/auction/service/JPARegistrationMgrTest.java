package auction.service;

import java.util.List;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import auction.domain.Account;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import nl.fontys.util.DatabaseCleaner;

public class JPARegistrationMgrTest {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
    public EntityManager em = emf.createEntityManager();

    private JPARegistrationMgr registrationMgr;
    
    @Before
    public void setUp() throws Exception {
        System.out.print("before");
        registrationMgr = new JPARegistrationMgr();
        DatabaseCleaner dc = new DatabaseCleaner(Persistence.createEntityManagerFactory("auctionPU").createEntityManager());
        dc.clean();
    }

    @Test
    public void registerUser() {
        Account user1 = registrationMgr.registerUser("xxx1@yyy");
        em.getTransaction().begin();
        em.persist(user1);
        assertTrue(user1.getEmail().equals("xxx1@yyy"));
        Account user2 = registrationMgr.registerUser("xxx2@yyy2");
        em.persist(user2);
        em.getTransaction().commit();
        assertTrue(user2.getEmail().equals("xxx2@yyy2"));
        Account user2bis = registrationMgr.registerUser("xxx2@yyy2");
        //equals van de emails werkt wel, kijkt op naam
        assertEquals(user2bis.getEmail(), user2.getEmail());
        //geen @ in het adres
        assertNull(registrationMgr.registerUser("abc"));
        em.close();
    }

    @Test
    public void getUser() {
        em.getTransaction().begin();
        Account user1 = registrationMgr.registerUser("xxx5@yyy5");
        em.persist(user1);
        em.getTransaction().commit();
        Account userGet = registrationMgr.getUser("xxx5@yyy5");
        //check on the same email
        assertEquals(userGet.getEmail(), user1.getEmail());
        assertNull(registrationMgr.getUser("aaa4@bb5"));
        registrationMgr.registerUser("abc");
        assertNull(registrationMgr.getUser("abc"));
        em.close();
    }

    @Test
    public void getUsers() {
        em.getTransaction().begin();
        List<Account> users = registrationMgr.getUsers();
        assertEquals(0, users.size());

        Account user1 = registrationMgr.registerUser("xxx8@yyy");
        em.persist(user1);
        em.getTransaction().commit();
        users = registrationMgr.getUsers();
        assertEquals(1, users.size());
        assertEquals(users.get(0).getEmail(), user1.getEmail());

        em.getTransaction().begin();
        Account user2 = registrationMgr.registerUser("xxx9@yyy");
        em.persist(user2);
        em.getTransaction().commit();
        users = registrationMgr.getUsers();
        assertEquals(2, users.size());

        registrationMgr.registerUser("abc");
        //geen nieuwe user toegevoegd, dus gedrag hetzelfde als hiervoor
        users = registrationMgr.getUsers();
        assertEquals(2, users.size());
    }
}
