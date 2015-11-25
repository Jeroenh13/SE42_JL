/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import bank.domain.Account;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.DatabaseCleaner;

/**
 *
 * @author Jeroen Hendriks
 */
public class Opdracht1 {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankPU");
    public EntityManager em = emf.createEntityManager();
    DatabaseCleaner dc = new DatabaseCleaner(em);
    
    public Opdracht1() {
    }

    /*
    
    */
    @Test
    public void testAccount1()
    {
        Account account = new Account(111L);
        em.getTransaction().begin();
        em.persist(account);
        //TODO: verklaar en pas eventueel aan
        assertNull(account.getId());
        em.getTransaction().commit();
        System.out.println("AccountId: " + account.getId());
        //TODO: verklaar en pas eventueel aan
        assertTrue(account.getId() > 0L);
    }
    
    
    @Test
    public void testAccount2()
    {
        Account account = new Account(111L);
        em.getTransaction().begin();
        em.persist(account);
        assertNull(account.getId());
        em.getTransaction().rollback();
        // TODO code om te testen dat table account geen records bevat. Hint: bestudeer/gebruik AccountDAOJPAImpl
    }
    
    @Test
    public void testAccount3()
    {
        Long expected = -100L;
        Account account = new Account(111L);
        account.setId(expected);
        em.getTransaction().begin();
        em.persist(account);
        //TODO: verklaar en pas eventueel aan
        //assertNotEquals(expected, account.getId();
        em.flush();
        //TODO: verklaar en pas eventueel aan
        //assertEquals(expected, account.getId();
        em.getTransaction().commit();
        //TODO: verklaar en pas eventueel aan
    }
    
    
     @Test
    public void testAccount4()
    {
        Long expectedBalance = 400L;
        Account account = new Account(114L);
        em.getTransaction().begin();
        em.persist(account);
        account.setBalance(expectedBalance);
        em.getTransaction().commit();
        assertEquals(expectedBalance, account.getBalance());
        //TODO: verklaar de waarde van account.getBalance
        Long acId = account.getId();
        account = null;
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        Account found = em2.find(Account.class, acId);
        //TODO: verklaar de waarde van found.getBalance
        assertEquals(expectedBalance, found.getBalance());
    }
    
    
    @Test
    public void testAccount5()
    {
        
    
    }
    
    @Test
    public void testAccount6()
    {
        
    
    }
    
    @Test
    public void testAccount7()
    {
        
    
    }
    
    @Test
    public void testAccount8()
    {
        
    
    }
    
    @Test
    public void testAccount9()
    {
        
    
    }

}
