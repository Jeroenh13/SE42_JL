package auction.domain;

import auction.domain.Item;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-09T21:40:28")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SetAttribute<Account, Item> offeredItems;
    public static volatile SingularAttribute<Account, Long> acc_id;
    public static volatile SingularAttribute<Account, String> email;

}