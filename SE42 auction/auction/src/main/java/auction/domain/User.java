package auction.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String email;

    public User() {
    }
    
    public User(String email) {
        this.email = email;

    }

    public String getEmail() {
        return email;
    }
}
