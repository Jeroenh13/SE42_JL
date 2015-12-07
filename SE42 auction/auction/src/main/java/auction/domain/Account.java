package auction.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Account implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
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
