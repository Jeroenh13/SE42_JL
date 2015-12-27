package auction.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import nl.fontys.util.Money;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "Item.count",
            query = "select count(i) from Item as i"
    ),
    @NamedQuery(
            name = "Item.findByID",
            query = "select i from Item as i where i.item_id = :id"),
    @NamedQuery(
            name = "Item.findByDescription",
            query = "select i from Item as i where i.descr LIKE :description")
})

public class Item implements Comparable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;
    @ManyToOne
    private Account seller;
    private Category category;
    private String descr;
    private Bid highest;

    public Item() {
    }

    public Item(Account seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.descr = description;
    }

    public Long getId() {
        return item_id;
    }

    public Account getSeller() {
        return seller;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return descr;
    }

    public Bid getHighestBid() {
        return highest;
    }

    public Bid newBid(Account buyer, Money amount) {
        if (highest != null && highest.getAmount().compareTo(amount) >= 0) {
            return null;
        }
        highest = new Bid(buyer, amount);
        return highest;
    }

    public int compareTo(Object arg0) {
        //TODO
        return -1;
    }

    public boolean equals(Object o) {
        //TODO
        return false;
    }

    public int hashCode() {
        //TODO
        return 0;
    }
}
