/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDrivers;

import dbms.Account;
import java.sql.SQLException;
import java.text.ParseException;

public class AccountTest {
    public static void main(String[] args) throws ParseException, SQLException{
        String table = "";
        String username = "";
        String equip_id = "";
        String qty = "";
        
        
        Account.checkExists(table);
        Account.createAcc(table);
        Account.search(table);
        Account.showAccount(username);
        Account.update(equip_id, qty);
        Account.userProfile(username);
    }
}
