package auction.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import auction.web.WebServiceMethods;
import java.util.List;
import web.service.*;

public class JPARegistrationMgrTest {
    
    @Before
    public void setUp() throws Exception {
        WebServiceMethods.cleanDatabase();
    }

    @Test
    public void registerUser() {
        Account user1 = WebServiceMethods.registerUser("jj@PP.nl");
        assertTrue(user1.getEmail().equals("jj@PP.nl"));
        
        Account user2 = WebServiceMethods.registerUser("pp@jj");
        assertTrue(user2.getEmail().equals("pp@jj"));
    }

    @Test
    public void getUser() {
        Account user1 = WebServiceMethods.registerUser("jj@pp");
        Account getUser = WebServiceMethods.getUser("jj@pp");
        
        assertEquals(user1.getEmail(), getUser.getEmail());
        assertNull(WebServiceMethods.getUser("test123"));
        WebServiceMethods.registerUser("abc");
        assertNull(WebServiceMethods.getUser("abc"));
    }

    
    @Test
    public void getUsers() {
        List<Account> users = WebServiceMethods.getUsers();
        assertEquals(0, users.size());

        Account user1 = WebServiceMethods.registerUser("xxx8@yyy");
        users = WebServiceMethods.getUsers();
        assertEquals(1, users.size());
        assertEquals(users.get(0).getEmail(), user1.getEmail());

        Account user2 = WebServiceMethods.registerUser("xxx9@yyy");
        users = WebServiceMethods.getUsers();
        assertEquals(2, users.size());

        WebServiceMethods.registerUser("abc");
        //geen nieuwe user toegevoegd, dus gedrag hetzelfde als hiervoor
        users = WebServiceMethods.getUsers();
        assertEquals(2, users.size());
    }
}
