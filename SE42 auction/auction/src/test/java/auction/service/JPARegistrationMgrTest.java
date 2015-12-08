package auction.service;

import java.util.List;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import auction.domain.Account;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPARegistrationMgrTest {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
    public EntityManager em = emf.createEntityManager();

    private JPARegistrationMgr registrationMgr;

    @Before
    public void setUp() throws Exception {
        
        registrationMgr = new JPARegistrationMgr();
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
    }

    public void getUser() {
        Account user1 = registrationMgr.registerUser("xxx5@yyy5");
        Account userGet = registrationMgr.getUser("xxx5@yyy5");
        assertSame(userGet, user1);
        assertNull(registrationMgr.getUser("aaa4@bb5"));
        registrationMgr.registerUser("abc");
        assertNull(registrationMgr.getUser("abc"));
    }

    public void getUsers() {
        List<Account> users = registrationMgr.getUsers();
        assertEquals(0, users.size());

        Account user1 = registrationMgr.registerUser("xxx8@yyy");
        users = registrationMgr.getUsers();
        assertEquals(1, users.size());
        assertSame(users.get(0), user1);


        Account user2 = registrationMgr.registerUser("xxx9@yyy");
        users = registrationMgr.getUsers();
        assertEquals(2, users.size());

        registrationMgr.registerUser("abc");
        //geen nieuwe user toegevoegd, dus gedrag hetzelfde als hiervoor
        users = registrationMgr.getUsers();
        assertEquals(2, users.size());
    }
}
