//Test driver for dbms.Account methods

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
        
        //Verify checking if account table exists
        if (Account.checkExists("zach_murray")){
            System.out.println("Table zach_murray exists");
        }
        if (Account.checkExists("rich_farley")){
            System.out.println("Table rich_farley exists");
        }
        else{
            System.out.println("Table rich_farley does not exist");
        }
        
        //Verify if account exists, or create one
        Account.createAcc(table);
        Account.search(table);
        Account.showAccount(username);
        Account.update(equip_id, qty);
        Account.userProfile(username);
    }
}
