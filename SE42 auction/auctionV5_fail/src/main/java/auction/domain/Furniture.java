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
public class Furniture extends Item{

    private String material;
    
    public Furniture() {
    }

    public Furniture(Account seller, Category category, String description, String material) {
        super(seller, category, description);
        this.material = material;
    }
    

    /**
     * Get the value of material
     *
     * @return the value of material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Set the value of material
     *
     * @param material new value of material
     */
    public void setMaterial(String material) {
        this.material = material;
    }
}
