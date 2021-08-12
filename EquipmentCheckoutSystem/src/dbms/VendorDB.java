package dbms;
//Table Columns: vendor_id | title | phone

import java.sql.ResultSet;

public class VendorDB
{
    public VendorDB() {

    }

    public static void add(String vendor, String phone) {
        // First validate the data coming in
        String errors = "";
        boolean valid = true;
        if (vendor.trim().equals("") || vendor.length() > 20) // Check if a blank vendor was added or the vendor's length is longer than database field
        {
            valid = false;
            if (vendor.trim().equals(""))
                errors += "blank vendor name\n";
            else
                errors += "vendor name is longer than 20 characters\n";
        }
        if (phone.trim().equals("") || phone.length() > 12) // We'll make the same checks against the phone field
        {
            valid = false;
            if (phone.trim().equals(""))
                errors += "blank phone number\n";
            else
                errors += "phone number is longer than 20 characters\n";
        }
                
        if (valid)
        {
            // Then create the database connection and insert the new vendor
            DBConnect db = new DBConnect();
            db.SqlInsert("vendor", "vendor_id, title, phone", "'" + GetNewID() + "', '" + vendor + "', '" + phone + "'");
            db.Dispose(); // Remember to run this to ensure that the database connection is closed
            System.out.println("Vendor " + vendor + " added successfully!");
        }
        else
            System.out.println("The vendor could not be added for the following reason(s):\n" + errors);
    }
    public static void delete(String vendorID) {
        DBConnect db = new DBConnect();
        db.SqlDelete("vendor", "vendor_id = '" + vendorID + "'");
        db.Dispose();
        System.out.println("Vendor removed successfully!");
    }
    public static void update(String vendorID, String newTitle, String newPhone) {
        DBConnect db = new DBConnect();
        db.SqlUpdate("vendor", "title = '" + newTitle + "', phone = '" + newPhone + "'", newPhone);
        db.Dispose();
        System.out.println("Vendor updated successfully!");
    }
    public static ResultSet search(String filter) {
        DBConnect db = new DBConnect();
        ResultSet rs = db.SqlSelectAll("SELECT * FROM vendor WHERE vendor_id LIKE '%" + filter + "%' OR title LIKE '%" + filter + "%' OR phone LIKE '%" + filter + "%'");
        db.Dispose();
        return rs;
    }
    
    // This method will auto increment the current vendor ID
    private static String GetNewID() {
        DBConnect db = new DBConnect(); // Create a database connection
        String id = db.SqlSelectSingle("SELECT vendor_id FROM vendor ORDER BY vendor_id DESC LIMIT 1"); // First try to get the top ID.
        if (id.equals("")) // This is incase there are no registered vendors in the software
            id = "001";
        else
            id = String.format("%03d", Integer.parseInt(id) + 1); // This will increment the top ID by one then format it to have three digits        
        db.Dispose(); // This will close our database connection for us
        return id;
    }
}