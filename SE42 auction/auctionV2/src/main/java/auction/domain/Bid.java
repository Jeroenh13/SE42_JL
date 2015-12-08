package auction.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

@Entity
public class Bid {

    private FontysTime time;
    private Account buyer;
    private Money amount;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Bid(Account buyer, Money amount) {
        //TODO
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
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
