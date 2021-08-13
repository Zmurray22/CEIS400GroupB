package dbms;

import java.sql.*;
//Table Columns: equip_id | title | available | total | vendor_id
public class InventoryDB {

    public InventoryDB()
    {

    }

    static void add(String title, String total)//Total amount will be initial starting amount aka available
    {
        //validation - Check to ensure all variables are filled with the proper information
        String errors = ""; // Collection of error statements to be displayed after validation
        boolean valid = true; // 'valid' boolean to ensure information is entered correctly or not
        
        if(title.trim().equals("")) 
        {
        valid = false; 
        errors += "Invalid Entry. Title is empty.\n";
        }
          
        if (total.trim().equals(""))
        {
            valid = false;
            errors += "Invalid entry. Total amount of items not specified.\n";
        }
        
      //if the checks are passsed
         if(valid)
         {
         DBConnect db = new DBConnect();
        
         }
         else
         {
             System.out.println("Inventory information could not be added to the database for the following reasons: " + errors);
         }
    }

    static void delete() 
    {

    }

    public static String search(String filter)
    {
        DBConnect DB = new DBConnect();
        String rs = DB.SqlSelectSingle("SELECT title FROM Inventory WHERE equip_id = " + filter);
        DB.Dispose();
        return rs;
    }

    static void update()
    {

    }
}
