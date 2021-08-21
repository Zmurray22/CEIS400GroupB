///Receives updates to account to create/update employee account table.
//Database Account table does not exist for a user until they rent equipment.
package dbms;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.sql.*;
import java.text.ParseException;

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
        String tableName = userProfile(username);
        //Search for existing user account table
        //Return if already exists
        if (checkExists(tableName)){
            System.out.println("Account already exists");
            return;
        }
        //Create table
        db.SqlCreateFromTemplate("account_temp", tableName);
        if (checkExists(tableName)){
            System.out.println("Account created");
            return;
        } 
        db.Dispose();
    }
    
    public static void deleteAcc(String username) throws SQLException{
        //Delete user Account table
        DBConnect db = new DBConnect();
        //Pull the first and last name of the user from username
        String tableName = userProfile(username);
        //drop table
        
        db.Dispose();
    }

    public static ResultSet search(String tableName) {
        //Find a table
        
        DBConnect db = new DBConnect();
        ResultSet rs = db.SqlSelectAll("SELECT * FROM " + tableName);
        
        db.Dispose();
        return rs;
    }
    
    public static void update(String userName, String equip_id, String qty) throws ParseException, SQLException{
        //Updates Account, Inventory, Equipment_hist, and emp_equipment tables
        DBConnect db = new DBConnect();
        //Store the time of transaction     
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String tableName = userProfile(userName);
        

        //Update user account
        db.SqlInsert(tableName, "equip_id, title, qty, date", "'0001', 'John', 'Doe', '1', '123-456-7890', 'jdoe', 'MySuperSecretPwd'");
        
        
        //Update Inventory available
        db.SqlUpdate("employee", "fname = 'Jon'", "emlp_id = '0002'");
        
        //Update equipment_hist
        String hist_id = GetNewID();
        db.SqlInsert("equipment_hist", hist_id + "empl_id, fname, lname, access, phone, username, password", "'0001', 'John', 'Doe', '1', '123-456-7890', 'jdoe', 'MySuperSecretPwd'");
        
        //Update emp_equipment
        db.SqlUpdate("employee", "fname = 'Jon'", "emlp_id = '0002'");
        
        db.Dispose();
    }
    
    public static String[] userProfile(String username) throws SQLException{
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
        db.Dispose();
        return profileArr;
    }
    
    public static void showAccount(String username) throws SQLException{
         //Check for account table under user's name and print
        DBConnect db = new DBConnect();
        
        String[] profile = Account.userProfile(username);
        String tableName = profile[1] + "_" + profile[2];
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
    
     // This method will auto increment the current equipment_hist hist_id 
    private static String GetNewID() {
        DBConnect db = new DBConnect(); // Create a database connection
        String id = db.SqlSelectSingle("SELECT hist_id FROM equipment_hist ORDER BY hist_id DESC LIMIT 1"); // First try to get the top ID.
        if (id.equals("")) // This is incase there are no registered vendors in the software
            id = "0001";
        else
            id = String.format("%04d", Integer.parseInt(id) + 1); // This will increment the top ID by one then format it to have three digits        
        db.Dispose(); // This will close our database connection for us
        return id;
    }
}