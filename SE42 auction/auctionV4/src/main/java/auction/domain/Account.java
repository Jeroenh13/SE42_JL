package auction.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(
   name = "Account.findByEmail", 
   query = "select a from Account as a where a.email = :email"
)

public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long acc_id;
    private String email;

    public Account() {
    }

    public Account(String email) {
        this.email = email;

    }

    public String getEmail() {
        return email;
    }
}
