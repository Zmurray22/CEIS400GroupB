package ims;

import dbms.DBConnect;

public class main {
    public static void main(String[] args) {
        DBConnect db = new DBConnect();
        
        if (db.DBReadyForUse()) 
        {
            // Run some test queries
            System.out.println();
            System.out.println("The first user is: " + db.SqlSelectSingle("SELECT concat(fname, ' ', lname) FROM employee WHERE empl_id = '0001'"));
            
            System.out.println();
            db.SqlInsert("employee", "empl_id, fname, lname, access, phone, username, password", "'9999', 'Tester', 'McTest', '1', 'No Phone', 'T-Act', 'NotSecure123'");
            System.out.println("Newly created user is: " + db.SqlSelectSingle("SELECT concat(fname, ' ', lname) FROM employee WHERE empl_id = '9999'"));
            
            System.out.println();
            db.SqlUpdate("employee", "password = 'This_is_b3tt3r'", "empl_id = '9999'");
            System.out.println("The updated password is: " + db.SqlSelectSingle("SELECT password FROM employee WHERE empl_id = '9999'"));
            
            System.out.println();
            db.SqlDelete("employee", "empl_id = '9999'");
            if (db.SqlSelectSingle("SELECT concat(fname, ' ', lname) FROM employee WHERE empl_id = '9999'").equals(""))
                System.out.println("The test employee no longer exists");
            else
                System.out.println("This method failed");
        }
    }
}
