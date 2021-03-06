package auction.service;

import static org.junit.Assert.*;

import nl.fontys.util.Money;

import org.junit.Before;
import org.junit.Test;

import auction.domain.Category;
import auction.domain.Item;
import auction.domain.Account;
import java.util.ArrayList;
import javax.persistence.Persistence;
import nl.fontys.util.DatabaseCleaner;

public class SellerMgrTest {

    private AuctionMgr auctionMgr;
    private JPARegistrationMgr registrationMgr;
    private SellerMgr sellerMgr;

    DatabaseCleaner dc = new DatabaseCleaner(Persistence.createEntityManagerFactory("auctionPU").createEntityManager());

    @Before
    public void setUp() throws Exception {
        registrationMgr = new JPARegistrationMgr();
        auctionMgr = new AuctionMgr();
        sellerMgr = new SellerMgr();
        dc.clean();
    }

    /**
     * Test of offerItem method, of class SellerMgr.
     */
    @Test
    public void testOfferItem() {
        String omsch = "omsch";

        Account user1 = registrationMgr.registerUser("xx@nl");
        Category cat = new Category("cat1");
        Item item1 = sellerMgr.offerItem(user1, cat, omsch);
        assertEquals(omsch, item1.getDescription());
        assertNotNull(item1.getId());
    }

    /**
     * Test of revokeItem method, of class SellerMgr.
     */
    @Test
    public void testRevokeItem() {
        String omsch = "omsch";
        String omsch2 = "omsch2";

        Account seller = registrationMgr.registerUser("sel@nl");
        Account buyer = registrationMgr.registerUser("buy@nl");
        Category cat = new Category("cat1");

        // revoke before bidding
        Item item1 = sellerMgr.offerItem(seller, cat, omsch);
        boolean res = sellerMgr.revokeItem(item1);
        assertTrue(res);
        int count = auctionMgr.findItemByDescription(omsch).size();
        assertEquals(0, count);

        // revoke after bid has been made
        Item item2 = sellerMgr.offerItem(seller, cat, omsch2);
        auctionMgr.newBid(item2, buyer, new Money(100, "Euro"));
        boolean res2 = sellerMgr.revokeItem(item2);
        assertFalse(res2);
        int count2 = auctionMgr.findItemByDescription(omsch2).size();
        assertEquals(1, count2);
    }
}
