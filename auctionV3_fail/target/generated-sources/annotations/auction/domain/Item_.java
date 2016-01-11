package auction.domain;

import auction.domain.Account;
import auction.domain.Bid;
import auction.domain.Category;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-27T19:13:45")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, Account> seller;
    public static volatile SingularAttribute<Item, String> descr;
    public static volatile SingularAttribute<Item, Long> item_id;
    public static volatile SingularAttribute<Item, Bid> highest;
    public static volatile SingularAttribute<Item, Category> category;

}