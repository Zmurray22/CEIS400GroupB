//Test driver for dbms.Account methods

package testDrivers;

import dbms.Account;
import java.sql.SQLException;
import java.text.ParseException;

public class AccountTest {
    public static void main(String[] args) throws ParseException, SQLException{
        String table = "Zach_Murray";
        String username = "zmurray22";
        String equip_id = "11";
        String qty = "2";
        
        String table2 = "rich_farley";
        String username2 = "richf";
        String equip_id2 = "15";
        String qty2 = "2";
        
        System.out.println("------------------------Verify checking if account table exists");
        //Verify checking if account table exists
        if (Account.checkExists(table)){
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
       
        System.out.println("------------------------Verify if existing account can be displayed");
        //Verify if existing account can be displayed
        Account.showAccount(username);
        Account.showAccount(username2);
        
        //Verify if account table can be updated
        //Account.update(username, equip_id, qty);
        //Account.update(username, equip_id, qty);
       
        System.out.println("------------------------Verify if userProfile can be displayed");
        //Verify if userProfile can be displayed
        String profileArr[] = Account.userProfile(username);
                    
                    //Print the profile data
                    System.out.println("Employee ID: " + profileArr[0] + "\nFirst Name: " + profileArr[1] + 
                     "\nLast Name: " + profileArr[2] + "\nAccess Level: " + profileArr[3] + "\nPhone: " + 
                    profileArr[4] + "\nUsername: " + profileArr[5]);
        
        String profileArr2[] = Account.userProfile(username2);
                    
                    //Print the profile data
                    System.out.println("Employee ID: " + profileArr2[0] + "\nFirst Name: " + profileArr2[1] + 
                     "\nLast Name: " + profileArr2[2] + "\nAccess Level: " + profileArr2[3] + "\nPhone: " + 
                    profileArr2[4] + "\nUsername: " + profileArr2[5]);
       
        System.out.println("------------------------Verify if account exists, or create one");
        //Verify if account exists, or create one
        Account.createAcc(username);       
        Account.createAcc(username2);
    }
}
