package auction.service;

import auction.web.WebServiceMethods;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import javax.persistence.Persistence;
import web.service.Account;
import web.service.Category;
import web.service.Item;
import web.service.Money;

public class SellerMgrTest {


    

    @Before
    public void setUp() throws Exception {
        WebServiceMethods.cleanDatabase();
    }

    /**
     * Test of offerItem method, of class SellerMgr.
     */
    @Test
    public void testOfferItem() {
        
        String omsch = "omsch";

        Account user1 = WebServiceMethods.registerUser("xx@nl");
        
        Category cat = new Category();
        cat.setDescription(omsch);
        Item item1 = WebServiceMethods.OfferItem(user1, cat, omsch);
        assertEquals(omsch, item1.getDescr());
        assertNotNull(item1.getItemId());
    }

    /**
     * Test of revokeItem method, of class SellerMgr.
     */
    @Test
    public void testRevokeItem() {
        
        String omsch = "omsch";
        String omsch2 = "omsch2";

        Account seller = WebServiceMethods.registerUser("sel@nl");
        Account buyer = WebServiceMethods.registerUser("buy@nl");
        Category cat = new Category();
        cat.setDescription("cat1");
        
        Item item1 = WebServiceMethods.OfferItem(seller, cat, omsch);
        boolean res = WebServiceMethods.revokeItem(item1);
        assertTrue(res);
         
        int count = WebServiceMethods.findItemByDescription(omsch).size();
        assertEquals(0,count);

        Item item2 = WebServiceMethods.OfferItem(seller, cat, omsch2);
        Money money = new Money();
        money.setCents(100);
        money.setCurrency("eur");
        WebServiceMethods.newBid(item2, buyer, money);
        item2 = WebServiceMethods.getItem(item2.getItemId());
        boolean res2 = WebServiceMethods.revokeItem(item2);
        assertFalse(res2);
        int count2 = WebServiceMethods.findItemByDescription(omsch2).size();
        assertEquals(1,count2);
    }
}
