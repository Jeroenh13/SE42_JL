package auction.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQuery(
        name = "Account.findByEmail",
        query = "select a from Account as a where a.email = :email"
)

public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long acc_id;

    @Column(unique = true)
    private String email;

    //@XmlTransient
    //@OneToMany(mappedBy = "seller")
    //Set<Item> offeredItems = new HashSet<>();

    public Account() {
    }

    public Account(String email) {
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    /*
    public void addItem(Item i) {
        offeredItems.add(i);
    }

    public int numberOfOfferedItems() {
        return offeredItems.size();
    }

    public Iterator getOfferedItems() {
        return offeredItems.iterator();
    }
    */
}
