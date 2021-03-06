package nl.fontys.util;

import auction.domain.Account;
import auction.domain.Bid;
import auction.domain.Item;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

public class DatabaseCleaner {

    private static final Class<?>[] ENTITY_TYPES = {
        Item.class,
        Bid.class,
        Account.class,
    };
    
    private EntityManager em = Persistence.createEntityManagerFactory("auctionPU").createEntityManager();
    DatabaseCleaner db;
        
    public DatabaseCleaner(){
        db = new DatabaseCleaner(em);
    }
    public DatabaseCleaner(EntityManager entityManager) {
        em = entityManager;
    }

    public void clean() throws SQLException {
        em.getTransaction().begin();

        for (Class<?> entityType : ENTITY_TYPES) {
            deleteEntities(entityType);
        }
        em.getTransaction().commit();
        em.close();
    }

    private void deleteEntities(Class<?> entityType) {
        em.createQuery("delete from " + getEntityName(entityType)).executeUpdate();
    }

    protected String getEntityName(Class<?> clazz) {
        EntityType et = em.getMetamodel().entity(clazz);
        return et.getName();
    }
}
