/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import auction.domain.Account;
import auction.domain.Bid;
import auction.domain.Category;
import auction.domain.Item;
import auction.service.AuctionMgr;
import auction.service.JPARegistrationMgr;
import auction.service.RegistrationMgr;
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
    AuctionMgr AuctionMgr = new AuctionMgr();
    SellerMgr sellerMgr = new SellerMgr();

    public Auction() {
    }
    
    public Item getItem(Long id)
    {
        return null;
    }
    
    public List<Item> findItemByDescription(String description)
    {
        return null;
    }
    
    public Bid newBid(Item item, Account buyer, Money amount)
    {
        return null;
    }
    
    public Item offerItem(Account seller, Category cat, String description)
    {
        return null;
    }
    
    public boolean revokeItem(Item item)
    {
        return false;
    }
}
