package dbms;
//Table Columns: empl_id | fname | lname | access

import java.sql.ResultSet;

public class EmployeeDB {
    //"employee", "empl_id, fname, lname, access, phone, username, password"
   

    public EmployeeDB() {
    }

    public static Boolean authenticate(String username, String password){
        DBConnect db = new DBConnect();
        Boolean login = false;
        //request given parameters from DB and confirm true false credentials
        if (db.SqlSelectAll("SELECT * WHERE username = '" + username + "' AND password = '" + password + "'")){
            System.out.println("Login correct");
        }
        else{
            System.out.println("Error logging in");
        }
        
        db.Dispose();
        return login;
    }
    public static void add(String fname, String lname, String access, String phone, String username, String password) {
        // First validate the data coming in
        String errors = "";
        boolean valid = true;
        if (fname.trim().equals("") || fname.length() > 15) // Check if a blank vendor was added or the vendor's length is longer than database field
        {
            valid = false;
            if (fname.trim().equals(""))
                errors += "blank first name\n";
            else
                errors += "first name is longer than 15 characters\n";
        }
        if (lname.trim().equals("") || lname.length() > 15) // We'll make the same checks against the phone field
        {
            valid = false;
            if (lname.trim().equals(""))
                errors += "blank last name\n";
            else
                errors += "last name is longer than 15 characters\n";
        }
        if (access.trim() != "1" || access.trim() != "2" || access.trim() != "3" || access.trim() != "4") // We'll make the same checks against the phone field
        {
            valid = false;
            if (access.trim().equals(""))
                errors += "blank access\n";
            else
                errors += "access level invalid\n";
        }
        if (phone.trim().equals("") || phone.length() > 12 || phone.length() <10) // We'll make the same checks against the phone field
        {
            valid = false;
            if (lname.trim().equals(""))
                errors += "blank phone number\n";
            else
                errors += "phone number invalid\n";
        }
        if (username.trim().equals("") || username.length() > 20) // Check if a blank vendor was added or the vendor's length is longer than database field
        {
            valid = false;
            if (username.trim().equals(""))
                errors += "blank username\n";
            else
                errors += "username is longer than 20 characters\n";
        }       
        if (password.trim().equals("") || password.length() > 20) // Check if a blank vendor was added or the vendor's length is longer than database field
        {
            valid = false;
            if (password.trim().equals(""))
                errors += "blank password\n";
            else
                errors += "password is longer than 20 characters\n";
        }
        
        if (valid)
        {
            // Then create the database connection and insert the new vendor
            DBConnect db = new DBConnect();
            db.SqlInsert("employee", "empl_id, fname, lname, access, phone, username, password", "'" + GetNewID() + 
                    "', '" + fname + "', '" + lname + "', '" + access + "', '" + phone + "', '" + username + "', '" + password + "'");
            db.Dispose(); // Remember to run this to ensure that the database connection is closed
            System.out.println("User " + fname + " " + lname + " added successfully!");
        }
        else
            System.out.println("The user could not be added for the following reason(s):\n" + errors);
    }
    public static void delete(String empl_id) {
        DBConnect db = new DBConnect();
        db.SqlDelete("employee", "empl_id = '" + empl_id + "'");
        db.Dispose();
        System.out.println("User removed successfully!");
    }
    public static void update(String empl_id, String newFname, String newLname, String newAccess, String newPhone, String newUsername, String newPassword) {
        DBConnect db = new DBConnect();
        db.SqlUpdate("employee", "fname = '" + newFname + "', lname = '" + newLname + "', access = '" + newAccess + "', phone = '" + 
                newPhone + "', username = '" + newUsername + "', password = '" + newPassword + "'", empl_id);
        db.Dispose();
        System.out.println("User profile updated successfully!");
    }
    public static ResultSet search(String filter) {
        DBConnect db = new DBConnect();
        ResultSet rs = db.SqlSelectAll("SELECT * FROM employee WHERE empl_id LIKE '%" + filter + "%' OR lname LIKE '%" + filter + "%' OR username LIKE '%" + filter + "%'");
        db.Dispose();
        return rs;
    }
    
    // This method will auto increment the current employee ID
    private static String GetNewID() {
        DBConnect db = new DBConnect(); // Create a database connection
        String id = db.SqlSelectSingle("SELECT empl_id FROM employee ORDER BY employee_id DESC LIMIT 1"); // First try to get the top ID.
        if (id.equals("")) // This is incase there are no registered vendors in the software
            id = "0001";
        else
            id = String.format("%04d", Integer.parseInt(id) + 1); // This will increment the top ID by one then format it to have three digits        
        db.Dispose(); // This will close our database connection for us
        return id;
    }
}
