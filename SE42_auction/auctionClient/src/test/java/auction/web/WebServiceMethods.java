/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.web;

import java.util.List;
import web.service.*;

/**
 *
 * @author Jeroen Hendriks
 */
public class WebServiceMethods {
    private static final AuctionService auctionService = new AuctionService();
    private static final RegistrationService registrationService = new RegistrationService();
    private static final WebDatabaseCleanService databaseCleaner = new WebDatabaseCleanService();

    public static Item getItem(Long id)
    {
        Auction port = auctionService.getAuctionPort();
        return port.getItem(id);
    }
    
    public static List<Item> findItemByDescription(String description)
    {
        Auction port = auctionService.getAuctionPort();
        return port.findItemByDescription(description);
    }
    
    public static Bid newBid(Item item, Account buyer, Money amount)
    {
        Auction port = auctionService.getAuctionPort();
        return port.newBid(item, buyer, amount);
    }
    
    public static Item OfferItem(Account seller,Category cat, String description)
    {
        Auction port = auctionService.getAuctionPort();
        return port.offerItem(seller, cat, description);
    }
    
    public static boolean revokeItem(Item item)
    {
        Auction port = auctionService.getAuctionPort();
        return port.revokeItem(item);
    }
    
    public static Account registerUser(String email)
    {
        Registration port = registrationService.getRegistrationPort();
        return port.registerUser(email);
    }
    
    public static Account getUser(String email)
    {
        Registration port = registrationService.getRegistrationPort();
        return port.getUser(email);
    }
    
    public static void cleanDatabase()
    {
        WebDatabaseClean port = databaseCleaner.getWebDatabaseCleanPort();
        port.cleanDatabase();
    }
}
