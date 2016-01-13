package auction.service;

import auction.dao.ItemDAO;
import auction.dao.ItemDAOJPAImpl;
import auction.dao.UserDAO;
import auction.dao.UserDAOJPAImpl;
import nl.fontys.util.Money;
import auction.domain.Bid;
import auction.domain.Item;
import auction.domain.Account;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AuctionMgr {

    private ItemDAO itemDAO;
    private final EntityManager em = Persistence.createEntityManagerFactory("auctionPU").createEntityManager();

    public AuctionMgr() {
        itemDAO = new ItemDAOJPAImpl(em);
    }

    /**
     * @param id
     * @return het item met deze id; als dit item niet bekend is wordt er null
     * geretourneerd
     */
    public Item getItem(Long id) {
        return itemDAO.find(id);
    }

    /**
     * @param description
     * @return een lijst met items met @desciption. Eventueel lege lijst.
     */
    public List<Item> findItemByDescription(String description) {
        return itemDAO.findByDescription(description);
    }

    /**
     * @param item
     * @param buyer
     * @param amount
     * @return het nieuwe bod ter hoogte van amount op item door buyer, tenzij
     * amount niet hoger was dan het laatste bod, dan null
     */
    public Bid newBid(Item item, Account buyer, Money amount) {

        Bid bid = null;
        if (item.getHighestBid() != null) {
            if (item.getHighestBid().getAmount().getCents() < amount.getCents()) {
                em.getTransaction().begin();
                bid = item.newBid(buyer, amount);
                itemDAO.addBid(item, bid);
                itemDAO.edit(item);
                em.getTransaction().commit();
            }

        } else {
            em.getTransaction().begin();
            bid = item.newBid(buyer, amount);
            itemDAO.addBid(item, bid);
            itemDAO.edit(item);
            em.getTransaction().commit();
        }
        return bid;
    }
}
