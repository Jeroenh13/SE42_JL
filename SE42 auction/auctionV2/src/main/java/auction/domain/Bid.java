package auction.domain;

import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

public class Bid {

    private FontysTime time;
    private Account buyer;
    private Money amount;

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
}
