///Receives updates to account to create/update employee account table.
//Database Account table does not exist for a user until they rent equipment.
package dbms;

import java.time.LocalDate;
import com.sun.jdi.connect.spi.Connection;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
// fields- equip_id | title | qty | date
public class Account {

    public void Account(){
       
    }
    
    public static boolean checkExists(String table){
        //Verify if a given table exists
        DBConnect db = new DBConnect();
        String query = "SHOW TABLES LIKE '" + table + "'";
        
        String exists = db.SqlSelectSingle(query);
        db.Dispose();
        return !"0".equals(exists);       
    }
    
    public static void createAcc(String username) throws SQLException{
        //Create an account table for a given user if it does not already exist
        DBConnect db = new DBConnect();
        //Pull the first and last name of user from username and
        //Search for existing user account table
        db.SqlSelectSingle(userProfile(username));
        
        db.Dispose();
        //if exist do nothing
    }
    
    public static void deleteAcc(String username){
        //Delete user Account table
        DBConnect db = new DBConnect();
        //Pull the first and last name of the user from username
    }

    public static ResultSet search(String tableName) {
        //Find a table
        
        DBConnect db = new DBConnect();
        ResultSet rs = db.SqlSelectAll("SELECT * FROM " + tableName);
        return rs;
    }
    
    public static void update(String equip_id, String qty) throws ParseException{
        //Updates Account, Inventory, Equipment_hist, and emp_equipment tables
        
        String string = "January 2, 2010";
        Date date = (Date) new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(string);
    //aggregate changes and update through db.sqlUpdate()
    //In Construction!!
    }
    
    public static String userProfile(String username) throws SQLException{
        //Pull employee profile record from the database with username entered earlier
        
        DBConnect db = new DBConnect();
        //Create array to hold the fields
        ResultSet rs = EmployeeDB.search(username);
        String[] profileArr = new String[6];
        while(rs.next()){
            for (int i = 1; i < 7; i++){
                String record = rs.getString(i);
                profileArr[i-1] = record;
            }
        }
        //Print the profile data
        System.out.println("Employee ID: " + profileArr[0] + "\nFirst Name: " + profileArr[1] + 
                "\nLast Name: " + profileArr[2] + "\nAccess Level: " + profileArr[3] + "\nPhone: " + 
                profileArr[4] + "\nUsername: " + profileArr[5]);
        //return first and last name
        return profileArr[1] + "_" + profileArr[2];
    }
    
    public static void showAccount(String username) throws SQLException{
         //Check for account table under user's name and print
        DBConnect db = new DBConnect();
        
        String tableName = Account.userProfile(username);
        if (Account.checkExists(tableName)){
            System.out.println("Account exists");
            ResultSet rs = Account.search(tableName);
            String[] accountArr = new String[4];
            while(rs.next()){
                System.out.println("Equipment ID: " + rs.getString("equip_id") + " | Title: " + rs.getString("title") + 
                " | Quantity: " + rs.getInt("qty") + " | Date: " + rs.getString("date"));
            }
        }          
        db.Dispose();
    }
}