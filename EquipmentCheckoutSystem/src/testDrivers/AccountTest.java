//Test driver for dbms.Account methods

package testDrivers;

import dbms.Account;
import java.sql.SQLException;
import java.text.ParseException;

public class AccountTest {
    public static void main(String[] args) throws ParseException, SQLException{
        String table = "zach_murray";
        String username = "zmurray22";
        String equip_id = "11";
        String qty = "2";
        
        String table2 = "rich_farley";
        String username2 = "richf";
        String equip_id2 = "15";
        String qty2 = "2";
        
        //Verify checking if account table exists
        if (Account.checkExists("table")){
            System.out.println("Table " + table + " exists");
        }
        else{
            System.out.println("Table " + table + " does not exist");
        }
        if (Account.checkExists(table2)){
            System.out.println("Table " + table2 + " exists");
        }
        else{
            System.out.println("Table " + table2 + " does not exist");
        }
   
        //Verify if existing account can be displayed
        Account.showAccount(username);
        Account.showAccount(username2);
        
        //Verify if account table can be updated
        Account.update(username, equip_id, qty);
        Account.update(username, equip_id, qty);
        
        //Verify if userProfile can be displayed
        Account.userProfile(username);
        Account.userProfile(username);
        
        //Verify if account exists, or create one
        Account.createAcc(username);       
        Account.createAcc(username2);
    }
}
