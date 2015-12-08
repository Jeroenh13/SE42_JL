package auction.service;

import auction.dao.ItemDAO;
import auction.dao.ItemDAOJPAImpl;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.Account;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class SellerMgr {

    private ItemDAO itemDAO;
    private final EntityManager em = Persistence.createEntityManagerFactory("auctionPU").createEntityManager();

    public SellerMgr() {
        itemDAO = new ItemDAOJPAImpl(em);
    }

    /**
     * @param seller
     * @param cat
     * @param description
     * @return het item aangeboden door seller, behorende tot de categorie cat
     * en met de beschrijving description
     */
    public Item offerItem(Account seller, Category cat, String description) {
        Item i = new Item(seller, cat, description);
        itemDAO.create(i);
        return i;
    }

    /**
     * @param item
     * @return true als er nog niet geboden is op het item. Het item word
     * verwijderd. false als er al geboden was op het item.
     */
    public boolean revokeItem(Item item) {
        if (item.getBids().size() == 0) {
            System.out.println("deleted");
            itemDAO.remove(item);
            return true;
        }
        System.out.println("NO!!!");
        return false;
    }
}
