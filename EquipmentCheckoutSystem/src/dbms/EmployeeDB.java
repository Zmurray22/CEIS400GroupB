package dbms;
//Table Columns: empl_id | fname | lname | access
public class EmployeeDB {
    //"employee", "empl_id, fname, lname, access, phone, username, password"
   

    public EmployeeDB() {
    }

    public static Boolean authenticate(String user, String pswd){
        Boolean login = false;
        
        return login;
    }
    public void updateProfile() {
    //Sends changes in profile records to Employee table
        // Example use DBConnect.SqlUpdate("employee", "fname = 'Jon'", "emlp_id = '0002'");
    }

    public void updateAccount() {
        //Sends equipment rental record updates to user account
    }
}
