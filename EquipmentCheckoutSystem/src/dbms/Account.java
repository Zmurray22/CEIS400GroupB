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
        String exists = "0";
        
        String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = '" + table.toLowerCase() + "'";
        
        exists = db.SqlSelectSingle(query);
        
        db.Dispose();
        if (exists == "1")
            return true;
        
        return !"0".equals(exists);       
    }
    
    public static void showAccount(String username) throws SQLException{
         //Check for account table under user's name and print
        DBConnect db = new DBConnect();
        
        String[] profile = Account.userProfile(username);
        String tableName = profile[1] + "_" + profile[2];
        if (Account.checkExists(tableName)){
            System.out.println("Account " + tableName + " exists");
            ResultSet rs = db.SqlSelectAll("SELECT * FROM " + tableName);
            while(rs.next()){
                System.out.println("Equipment ID: " + rs.getString("equip_id") + " | Title: " + rs.getString("title") + 
                " | Quantity: " + rs.getInt("qty") + " | Date: " + rs.getString("date"));
            }
        }
        else{
            System.out.println("Account " + tableName + " does not exist");
        }
        db.Dispose();
    }
    
    public static void createAcc(String username) throws SQLException{
        //Create an account table for a given user if it does not already exist
        DBConnect db = new DBConnect();
     
         //Get profile elements
        String profileArr[] = userProfile(username);
        //Set account tablename
        String tableName = profileArr[1] + "_" + profileArr[2];
        //Search for existing user account table
        //Return if already exists
        if (checkExists(tableName)){
            System.out.println("Account " + tableName + " already exists");
            return;
        }
        //Create table
        db.SqlCreateFromTemplate("account_temp", tableName);
        if (checkExists(tableName)){
            System.out.println("Account created");
        }
        else{
            System.out.println("Account could not be created");
        }
        db.Dispose();
        return;
    }
    
    public static void deleteAcc(String username) throws SQLException{
        //Delete user Account table
        DBConnect db = new DBConnect();
        //Pull the first and last name of the user from username
        //Get profile elements
        String profileArr[] = userProfile(username);
        //Set account tablename
        String tableName = profileArr[1] + "_" + profileArr[2];
        ///drop table
        
        db.Dispose();
    }
    
    public static void update(String username, String equip_id, String qty) throws ParseException, SQLException{
        //Updates Account, Inventory, Equipment_hist, and emp_equipment tables
        DBConnect db = new DBConnect();
        //Store the time of transaction     
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        //Get profile elements
        //employee: empl_id, fname, lname, access, phone, username, password
        String profileArr[] = userProfile(username);
        //Set account tablename
        String tableName = profileArr[1] + "_" + profileArr[2];
        
        //Get equipment elements
        //Inventory: equip_id, title, available, total, vendor
        ResultSet rs = InventoryDB.search(equip_id, db);
        String[] equipArr = new String[5];
        while(rs.next()){
            for (int i = 1; i < 6; i++){
                String record = rs.getString(i);
                equipArr[i-1] = record;
            }
        }
        String title = equipArr[1];
        String newInvQty = Integer.parseInt(equipArr[2]) + qty;

        //Insert user account record
        String transactionID = GetNewID(tableName);
        db.SqlInsert(tableName, "transaction_id, equip_id, title, qty, date", "'" + transactionID + "'" + equip_id + "', '" + title + "', '" + qty + "', '" + now + "'");
        
        //Update Inventory available
        db.SqlUpdate("inventory", "available = '" + newInvQty + "'", "equip_id = '" + equip_id + "'");
        
        //Update equipment_hist
        String hist_id = GetNewID("equipment_hist");
        db.SqlInsert("equipment_hist", "transaction_id, empl_id, equip_id, action, hist_date", "'" + hist_id + "', '" + profileArr[0] + "', '" + qty + "', '" + now + "'");
        
        //Update emp_equipment
        //db.SqlInsert("emp_equipment", "empl_id, equip_id, total", "'" + profileArr[0] + "', '" + equip_id + "', '" + qty + "'");
        //If new qty equals 0 then drop record
        ///working issue- can't insert if exists, but can't update if doesn't exist.
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

        //return array of profile elements
        db.Dispose();
        return profileArr;
    }
    
     // This method will auto increment the current equipment_hist hist_id 
    private static String GetNewID(String tableName) {
        DBConnect db = new DBConnect(); // Create a database connection
        String id = db.SqlSelectSingle("SELECT transaction_id FROM " + tableName + " ORDER BY hist_id DESC LIMIT 1"); // First try to get the top ID.
        if (id.equals("")) // This is incase there are no registered vendors in the software
            id = "1";
        else
            id = String.format("%o", Integer.parseInt(id) + 1); // This will increment the top ID by one     
        db.Dispose(); // This will close our database connection for us
        return id;
    }
}