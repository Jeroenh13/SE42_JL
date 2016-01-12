/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import javax.xml.ws.Endpoint;

/**
 *
 * @author Jeroen Hendriks
 */
public class StartServices {

    private static final String auctionURL = "http://localhost:9090/Auction";
    private static final String registrationURL = "http://localhost:9091/Registration";
    private static final String databaseCleaner = "http://localhost:9092/DatabaseCleaner";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Endpoint.publish(auctionURL, new Auction());
        Endpoint.publish(registrationURL, new Registration());
        Endpoint.publish(databaseCleaner, new WebDatabaseClean());
    }
    
}
