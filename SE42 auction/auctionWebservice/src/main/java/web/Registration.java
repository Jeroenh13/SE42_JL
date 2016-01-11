/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import auction.domain.Account;
import auction.service.JPARegistrationMgr;
import javax.jws.WebService;

/**
 *
 * @author jeroe
 */
@WebService
public class Registration {

    JPARegistrationMgr registrationMgr = new JPARegistrationMgr();

    public Registration() {
    }

    public Account registerUser(String email) {
        return null;
    }

    public Account getUser(String email) {
        return null;
    }

}
