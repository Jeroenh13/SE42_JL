/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.persistence.Persistence;
import nl.fontys.util.DatabaseCleaner;

/**
 *
 * @author Jeroen Hendriks
 */
@WebService
public class WebDatabaseClean {
    DatabaseCleaner dc;
    
    public WebDatabaseClean(){};
    
    public void CleanDatabase()
    {
        try {
            dc = new DatabaseCleaner();
            dc.clean();
        } catch (SQLException ex) {
            Logger.getLogger(WebDatabaseClean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
