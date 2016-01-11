package auction.service;

import org.junit.Ignore;
import javax.persistence.*;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.Account;
import auction.domain.Bid;
import java.util.Iterator;
import nl.fontys.util.DatabaseCleaner;
import nl.fontys.util.Money;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemsFromSellerTest {

    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
    final EntityManager em = emf.createEntityManager();
    private AuctionMgr auctionMgr;
    private JPARegistrationMgr registrationMgr;
    private SellerMgr sellerMgr;

    public ItemsFromSellerTest() {
    }

    @Before
    public void setUp() throws Exception {
        registrationMgr = new JPARegistrationMgr();
        auctionMgr = new AuctionMgr();
        sellerMgr = new SellerMgr();
        new DatabaseCleaner(em).clean();
    }

    @Test
 //   @Ignore
    public void numberOfOfferdItems() {

        String email = "ifu1@nl";
        String omsch1 = "omsch_ifu1";
        String omsch2 = "omsch_ifu2";

        Account user1 = registrationMgr.registerUser(email);
        assertEquals(0, user1.numberOfOfferedItems());

        Category cat = new Category("cat2");
        Item item1 = sellerMgr.offerItem(user1, cat, omsch1);

       
        // test number of items belonging to user1
        //assertEquals(0, user1.numberOfOfferedItems());
        assertEquals(1, user1.numberOfOfferedItems());
        
        /*
         *  expected: which one of te above two assertions do you expect to be true?
         *  QUESTION:
         *    Explain the result in terms of entity manager and persistance context.
         *  ANSWER: assert 2 would be true since the item is linked to the account with persist.
         */
         
         
        assertEquals(1, item1.getSeller().numberOfOfferedItems());


        Account user2 = registrationMgr.getUser(email);
        assertEquals(1, user2.numberOfOfferedItems());
        Item item2 = sellerMgr.offerItem(user2, cat, omsch2);
        assertEquals(2, user2.numberOfOfferedItems());

        Account user3 = registrationMgr.getUser(email);
        assertEquals(2, user3.numberOfOfferedItems());

        Account userWithItem = item2.getSeller();
        assertEquals(2, userWithItem.numberOfOfferedItems());
        //assertEquals(3, userWithItem.numberOfOfferedItems());
        /*
         *  expected: which one of te above two assertions do you expect to be true?
         *  QUESTION:
         *    Explain the result in terms of entity manager and persistance context.
         *  ANSWER: 
         */
        
        
        //assertNotSame(user3.getEmail(), userWithItem.getEmail());
        assertEquals(user3, userWithItem);
    }

    @Test
    public void testBids()
    {
        String email = "ifu1@nl";
        String email2 = "pp@pp.nl";
        Account user1 = registrationMgr.registerUser(email);
        Account user2 = registrationMgr.registerUser("jj@jj.com");
        Account user3 = registrationMgr.registerUser((email2));
        String omsch1 = "omsch_ifu1";
        Category cat = new Category("cat2");
        
        Item item1 = sellerMgr.offerItem(user1, cat, omsch1);
        
        item1.newBid(user2, new Money(5000, Money.EURO));
        
        assertEquals(5000, item1.getHighestBid().getAmount().getCents());
        assertEquals(user2, item1.getHighestBid().getBuyer());
        assertEquals(user1, item1.getSeller());
        
        Bid bid2 = auctionMgr.newBid(item1, user3, new Money(5001, Money.EURO));
        
        assertEquals(5001, item1.getHighestBid().getAmount().getCents());
        assertEquals(user3, item1.getHighestBid().getBuyer());
    }
  
    @Test
    public void getItemsFromSeller() {
        String email = "ifu1@nl";
        String omsch1 = "omsch_ifu1";
        String omsch2 = "omsch_ifu2";

        Category cat = new Category("cat2");

        Account user10 = registrationMgr.registerUser(email);
        Item item10 = sellerMgr.offerItem(user10, cat, omsch1);
        Iterator<Item> it = user10.getOfferedItems();
        // testing number of items of java object
        assertTrue(it.hasNext());
        
        // now testing number of items for same user fetched from db.
        Account user11 = registrationMgr.getUser(email);
        Iterator<Item> it11 = user11.getOfferedItems();
        assertTrue(it11.hasNext());
        it11.next();
        assertFalse(it11.hasNext());

        // Explain difference in above two tests for te iterator of 'same' user

        Account user20 = registrationMgr.getUser(email);
        Item item20 = sellerMgr.offerItem(user20, cat, omsch2);
        Iterator<Item> it20 = user20.getOfferedItems();
        assertTrue(it20.hasNext());
        it20.next();
        assertTrue(it20.hasNext());


        Account user30 = item20.getSeller();
        Iterator<Item> it30 = user30.getOfferedItems();
        assertTrue(it30.hasNext());
        it30.next();
        assertTrue(it30.hasNext());

    }
}
