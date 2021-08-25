///Receives updates to account to create/update employee account table.
//Database Account table does not exist for a user until they rent equipment.
package dbms;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.sql.*;
import java.text.ParseException;
import java.util.Arrays;

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

        String tableName = ims.main.User.getTableName();
        if (Account.checkExists(tableName)){
            System.out.println("Account " + tableName + " exists");
            ResultSet rs = db.SqlSelectAll("SELECT * FROM " + tableName);
            while(rs.next()){
                System.out.println("Transaction Id: " + rs.getString("transaction_id") + "Equipment ID: " + rs.getString("equip_id") + " | Title: " + rs.getString("title") + " | Date: " + rs.getString("date"));
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

        //Set account tablename
        String tableName = ims.main.User.getTableName();
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

        //Set account tablename
        String tableName = ims.main.User.getTableName();
        ///drop table
        
        db.Dispose();
    }
    
    public static void update(String username, String order[]) throws ParseException, SQLException{
        //Updates Account, Inventory, Equipment_hist, and emp_equipment tables
        DBConnect db = new DBConnect();
        //Store the time of transaction     
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime dateTime = LocalDateTime.now();
        String now = dateTime.format(dtf);
        
        //Set account tablename
        String tableName = ims.main.User.getTableName();
        
        //Get equipment elements
        //Inventory: equip_id, title(, available, total, vendor Not Needed in Array)
        // 2 dimensional Array  cart= {{equip_id, title}, {equip_id, title}, {equip_id, title}.....}
        Connection Conn = db.getConn();
        Statement sqlStmt = Conn.createStatement();
        String[][] cart =  new String[order.length][2];//2d Array of order items
        
        //Loop through items to populate 2D array of order list
        for (int i = 0; i < order.length; i++){
            String query = "SELECT equip_id, title FROM inventory WHERE equip_id = " + order[i] + "";
            ResultSet rs = sqlStmt.executeQuery(db.Clean(query));            

            while (rs.next()){
                cart[i][0]= rs.getString(1);
                cart[i][1]= rs.getString(2);
            }
        rs.close();
        }
        
        System.out.println(Arrays.toString(cart));
        
        //Loop through the tables to update each
        for(int i = 0; i < order.length; i ++){
            String transactionID = GetNewID(tableName);
            String equipID = cart[i][0];
            String title = cart[i][1];
            
            //Insert user account record
            db.SqlInsert(tableName, "transaction_id, equip_id, title, date", "'" + transactionID + "', '" + equipID + "', '" + title + "', '" + now + "'");

            //Update Inventory available
            String subInv = "available - 1";
            InventoryDB.update(Integer.parseInt(equipID), subInv);
            
            //Update equipment_hist
            String hist_id = GetNewID("equipment_hist");
            db.SqlInsert("equipment_hist", "transaction_id, empl_id, equip_id, action, hist_date", "'" + hist_id + "', '" + ims.main.User.getUserID() + "', '" + equipID + "', 'Checkout', '" + now + "'");

            //Update emp_equipment
            db.SqlInsert("emp_equipment", "empl_id, equip_id, title", "'" + ims.main.User.getUserID() + "', '" + equipID + "', '" + title + "'");
            //If new qty equals 0 then drop record
            ///working issue- can't insert if exists, but can't update if doesn't exist.
        }
        db.Dispose();
    }
    
    public static void userProfile(String username) throws SQLException{
        //Pull employee profile record from the database with username entered earlier
        
        DBConnect db = new DBConnect();
        //Create array to hold the fields
        ResultSet rs = EmployeeDB.search(username, db);
        String[] profileArr = new String[6];
        while(rs.next()){
            for (int i = 1; i < 7; i++){
                String record = rs.getString(i);
                profileArr[i-1] = record;
            }
        }
        ims.main.User.setUserID(profileArr[0]);
        ims.main.User.setUserName(profileArr[1]);
        ims.main.User.setUserName(profileArr[2]);
        ims.main.User.setUserName(profileArr[3]);
        ims.main.User.setUserName(profileArr[5]);
        //return array of profile elements
        db.Dispose();
    }
    
    public static void returnEquip(String tableName, Integer transactionID) throws SQLException{
        DBConnect db = new DBConnect();
        
        Connection Conn = db.getConn();
        Statement sqlStmt = Conn.createStatement();
        
        String query = "DELETE FROM " + tableName + " WHERE transaction_id=" + transactionID + "";
        ResultSet rs = sqlStmt.executeQuery(db.Clean(query));       
    }
    
     // This method will auto increment the current equipment_hist hist_id 
    private static String GetNewID(String tableName) {
        DBConnect db = new DBConnect(); // Create a database connection
        String id = db.SqlSelectSingle("SELECT transaction_id FROM " + tableName + " ORDER BY transaction_id DESC LIMIT 1"); // First try to get the top ID.
        if (id.equals("")) // This is incase there are no registered vendors in the software
            id = "1";
        else
            id = String.format("%o", Integer.parseInt(id) + 1); // This will increment the top ID by one     
        db.Dispose(); // This will close our database connection for us
        return id;
    }
}