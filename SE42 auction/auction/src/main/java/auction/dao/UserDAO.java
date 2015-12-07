package auction.dao;

import auction.domain.Account;
import java.util.List;

public interface UserDAO {

    /**
     *
     * @return number of user instances 
     */
    int count();

    /**
     * The user is persisted. If a user with the same email allready exists an EntityExistsException is thrown
     * @param user
     */
    void create(Account user);

    /**
     * Merge the state of the given user into persistant context. If the user did not exist an IllegalArgumentException is thrown
     * @param user
     */
    void edit(Account user);


    /**
     *
     * @return list of user instances
     */
    List<Account> findAll();

    /**
     *
     * @param email
     * @return unique user instance with parameter email or null if such user doesn't exist
     */
    Account findByEmail(String email);

    /**
     * Remove the entity instance
     * @param user - entity instance
     */
    void remove(Account user);
}
