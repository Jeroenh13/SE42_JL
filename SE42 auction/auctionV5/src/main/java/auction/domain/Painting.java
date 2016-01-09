/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author jeroe
 */
@Entity
public class Painting extends Item{

    private String painter;
    private String title;
    
    public Painting() {
    }
    
    public Painting(Account seller, Category category, String description, String title, String painter) {
        super(seller, category, description);
        this.title = title;
        this.painter = painter;
    }
    
    /**
     * Get the value of painter
     *
     * @return the value of painter
     */
    public String getPainter() {
        return painter;
    }

    /**
     * Set the value of painter
     *
     * @param painter new value of painter
     */
    public void setPainter(String painter) {
        this.painter = painter;
    }

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }   
}
