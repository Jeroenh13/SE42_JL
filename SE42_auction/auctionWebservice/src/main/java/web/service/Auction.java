/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import auction.domain.Account;
import auction.domain.Bid;
import auction.domain.Category;
import auction.domain.Item;
import auction.service.AuctionMgr;
import auction.service.JPARegistrationMgr;
import auction.service.SellerMgr;
import java.util.List;
import javax.jws.WebService;
import nl.fontys.util.Money;

/**
 *
 * @author jeroe
 */
@WebService
public class Auction {
    AuctionMgr auctionMgr = new AuctionMgr();
    SellerMgr sellerMgr = new SellerMgr();

    public Auction() {
    }
    
    public Item getItem(Long id)
    {
        return auctionMgr.getItem(id);
    }
    
    public List<Item> findItemByDescription(String description)
    {
        return auctionMgr.findItemByDescription(description);
    }
    
    public Bid newBid(Item item, Account buyer, Money amount)
    {
        return auctionMgr.newBid(item, buyer, amount);
    }
    
    public Item offerItem(Account seller, Category cat, String description)
    {
        return sellerMgr.offerItem(seller, cat, description);
    }
    
    public boolean revokeItem(Item item)
    {
        return sellerMgr.revokeItem(item);
    }
}
