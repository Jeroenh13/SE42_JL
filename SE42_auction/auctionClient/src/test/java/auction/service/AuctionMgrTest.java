package auction.service;

import auction.web.WebServiceMethods;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import web.service.*;

public class AuctionMgrTest {

    @Before
    public void setUp() throws Exception {
        WebServiceMethods.cleanDatabase();
    }

    @Test
    public void getItem() {
        String email = "xx2@nl";
        String omsch = "omsch";

        Account seller1 = WebServiceMethods.registerUser(email);
        Category cat = new Category();
        cat.setDescription(omsch);
        Item item1 = WebServiceMethods.OfferItem(seller1, cat, omsch);
        Item item2 = WebServiceMethods.getItem(item1.getItemId());

        assertEquals(omsch, item2.getDescr());
        assertEquals(email, item2.getSeller().getEmail());
    }

    @Test
    public void findItemByDescription() {
        String email3 = "xx3@nl";
        String omsch = "omsch";
        String email4 = "xx4@nl";
        String omsch2 = "omsch2";

        Account seller3 = WebServiceMethods.registerUser(email3);
        Account seller4 = WebServiceMethods.registerUser(email4);
        Category cat = new Category();
        cat.setDescription(omsch);
        Item item1 = WebServiceMethods.OfferItem(seller3, cat, omsch);
        Item item2 = WebServiceMethods.OfferItem(seller4, cat, omsch);

        ArrayList<Item> res = (ArrayList<Item>) WebServiceMethods.findItemByDescription(omsch2);

        assertEquals(0, res.size());

        res = (ArrayList<Item>) WebServiceMethods.findItemByDescription(omsch);
        assertEquals(2, res.size());
    }

    @Test
    public void newBid() {
        String email = "ss2@nl";
        String emailb = "bb@nl";
        String emailb2 = "bb2@nl";
        String omsch = "omsch_bb";

        Account seller = WebServiceMethods.registerUser(email);
        Account buyer = WebServiceMethods.registerUser(emailb);
        Account buyer2 = WebServiceMethods.registerUser(emailb2);

        Category cat = new Category();
        cat.setDescription(omsch);

        Item item1 = WebServiceMethods.OfferItem(seller, cat, omsch);
        Money money = new Money();
        money.setCents(100);
        money.setCurrency("eur");
        Bid bid1 = WebServiceMethods.newBid(item1, buyer, money);

        assertEquals(emailb, bid1.getBuyer().getEmail());
        
        item1 = WebServiceMethods.getItem(item1.getItemId());
        money.setCents(90);
        Bid bid2 = WebServiceMethods.newBid(item1, buyer2, money);
        assertNull(bid2);

        item1 = WebServiceMethods.getItem(item1.getItemId());
        money.setCents(110);
        Bid bid3 = WebServiceMethods.newBid(item1, buyer2, money);
        assertEquals(emailb2, bid3.getBuyer().getEmail());
    }
}
