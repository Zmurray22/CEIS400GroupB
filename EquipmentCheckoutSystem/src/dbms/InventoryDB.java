package dbms;

//Table Columns: equip_id | title | available | total | vendor_id

import java.sql.ResultSet;

public class InventoryDB {
    
    public static void InventoryDB(){
}
    
    public static void add (String equipTitle, String numAvailable, String totalStock, String vendorID)
    {
        //Get a new ID variable for the added Equipment
        String newID = GetNewID();
        //Validation is ran on the InventoryGUI, connecting to the Database and entering the information is below.
            DBConnect db = new DBConnect();
            
            db.SqlInsert("Inventory", "equip_id, title, available, total, vendor_id", "'" + newID + 
                    "', '" + equipTitle + "', '" + numAvailable + "', '" + totalStock + "', '"+ "00" + vendorID + "'");
            db.Dispose();
            System.out.println( " has been put into the database.");  
    }

    static void delete(String equipID) 
    {
        DBConnect db = new DBConnect();
        db.SqlDelete("Inventory", "equip_ID = '" + equipID + "'");
        db.Dispose();
        System.out.println("Inventory Item removed successfully!");
    }

    public static ResultSet search(String filter, DBConnect db)
    {
        ResultSet rs = db.SqlSelectAll("SELECT * FROM inventory WHERE equip_id =" + filter + "");
        return rs;
    }
   
    //newAvailable variable = "+ or - 1"
     public static void update(Integer equip_ID, String newAvailable) {
        DBConnect db = new DBConnect();
        db.SqlUpdate("Inventory", "available = '" + newAvailable + "'", "equip_id = '" + equip_ID + "'");
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
