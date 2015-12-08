package auction.dao;

import auction.domain.Account;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityExistsException;

public class UserDAOCollectionImpl implements UserDAO {

    private HashMap<String, Account> users;

    public UserDAOCollectionImpl() {
        users = new HashMap<String, Account>();
    }

    @Override
    public int count() {
        return users.size();
    }

    @Override
    public void create(Account user) {
         if (findByEmail(user.getEmail()) != null) {
            throw new EntityExistsException();
        }
        users.put(user.getEmail(), user);
    }

    @Override
    public void edit(Account user) {
        if (findByEmail(user.getEmail()) == null) {
            throw new IllegalArgumentException();
        }
        users.put(user.getEmail(), user);
    }


    @Override
    public List<Account> findAll() {
        return new ArrayList<Account>(users.values());
    }

    @Override
    public Account findByEmail(String email) {
        return users.get(email);
    }

    @Override
    public void remove(Account user) {
        users.remove(user.getEmail());
    }
}
