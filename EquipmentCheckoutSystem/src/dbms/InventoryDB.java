package dbms;

//Table Columns: equip_id | title | available | total | vendor_id

import java.sql.ResultSet;

public class InventoryDB {

    public InventoryDB()
    {

    }

    
    public static void add (String equipID, String title, int available, int total)
    {
        String errors = "";
        boolean valid = true;
        if (title.trim().equals("") || title.length() > 20) // Check if a blank vendor was added or the vendor's length is longer than database field
        {
            valid = false;
            if (title.trim().equals(""))
                errors += "blank Title\n";
            else
                errors += "Title is longer than 20 characters\n";
        }
        if (available > total) // The user is inputting a higher available amount than what the total is
        {
            valid = false;
            errors += "Available number exceeds the total amount.";
        }
        
        if (valid)
        {
            // Then create the database connection and insert the new Inventory item
            DBConnect db = new DBConnect();
            db.SqlInsert("Inventory", "equip_ID, title, available, total","'" + GetNewID() + 
                    "', '" + title + "', '" + available +"");
            db.Dispose(); // Remember to run this to ensure that the database connection is closed
            System.out.println("Inventory Item: " + title + " Available amount:" + available + " Total amount:" + total + " added successfully!");
        }
        else
            System.out.println("The user could not be added for the following reason(s):\n" + errors);
    }

    static void delete(String equipID) 
    {
        DBConnect db = new DBConnect();
        db.SqlDelete("Inventory", "equip_ID = '" + equipID + "'");
        db.Dispose();
        System.out.println("Inventory Item removed successfully!");
    }

    public static ResultSet Search (String filter, DBConnect db)
    {
        ResultSet rs = db.SqlSelectAll("SELECT * FROM inventory WHERE equip_id LIKE '%" + filter + "%'");
        return rs;
    }
   

     public static void update(String equip_ID, String newTitle, String newAvailable, String newTotal) {
        DBConnect db = new DBConnect();
        db.SqlUpdate("Inventory", "title = '" + newTitle + "', available = '" + newAvailable + "', total = '" + newTotal, equip_ID);
        db.Dispose();
        System.out.println("Inventory Item updated successfully!");
    }
    
    // This method will auto increment the current inventory ID
    private static String GetNewID() {
        DBConnect db = new DBConnect(); // Create a database connection
        String id = db.SqlSelectSingle("SELECT equip_id FROM Inventory ORDER BY equip_id DESC LIMIT 1"); // First try to get the top ID.
        if (id.equals("")) // This is incase there are no registered vendors in the software
            id = "001";
        else
            id = String.format("%03d", Integer.parseInt(id) + 1); // This will increment the top ID by one then format it to have three digits        
        db.Dispose(); // This will close our database connection for us
        return id;
}
}
