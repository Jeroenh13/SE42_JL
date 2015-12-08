package auction.service;

import java.util.*;
import auction.domain.Account;
import auction.dao.UserDAOCollectionImpl;
import auction.dao.UserDAO;

public class RegistrationMgr {

    private UserDAO userDAO;

    public RegistrationMgr() {
        userDAO = new UserDAOCollectionImpl();
    }

    /**
     * Registreert een gebruiker met het als parameter gegeven e-mailadres, mits
     * zo'n gebruiker nog niet bestaat.
     * @param email
     * @return Een Userobject dat geïdentificeerd wordt door het gegeven
     * e-mailadres (nieuw aangemaakt of reeds bestaand). Als het e-mailadres
     * onjuist is ( het bevat geen '@'-teken) wordt null teruggegeven.
     */
    public Account registerUser(String email) {
        if (!email.contains("@")) {
            return null;
        }
        Account user = userDAO.findByEmail(email);
        if (user != null) {
            return user;
        }
        user = new Account(email);
        userDAO.create(user);
        return user;
    }

    /**
     *
     * @param email een e-mailadres
     * @return Het Userobject dat geïdentificeerd wordt door het gegeven
     * e-mailadres of null als zo'n User niet bestaat.
     */
    public Account getUser(String email) {
        return userDAO.findByEmail(email);
    }

    /**
     * @return Een iterator over alle geregistreerde gebruikers
     */
    public List<Account> getUsers() {
        return userDAO.findAll();
    }
}
