package auction.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Bid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid_id;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Account buyer;

    @Embedded
    private Money amount;
    
    //@ManyToOne(cascade = CascadeType.PERSIST)
    //@JoinColumn(nullable=false)
    //private Item item;
    
    @Column
    private FontysTime time;

    public Bid() {
    }

    public Bid(Account buyer, Money amount) {
        this.buyer = buyer;
        this.amount = amount;
    }

    public FontysTime getTime() {
        return time;
    }

    public Account getBuyer() {
        return buyer;
    }

    public Money getAmount() {
        return amount;
    }

    public Long getId() {
        return bid_id;
    }

    public void setId(Long id) {
        this.bid_id = id;
    }
}
