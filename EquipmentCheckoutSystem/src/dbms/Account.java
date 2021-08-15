///Receives updates to account to create/update employee account table.
//Database Account table does not exist for a user until they rent equipment.
package dbms;

import java.time.LocalDate;
import com.sun.jdi.connect.spi.Connection;
import java.sql.*;
// fields- equip_id | title | qty | date
public class Account {
    private String borrowedItems;
    private int borrowedQty;
    private LocalDate dateBorrowed;

    public void Account(){
       
    }
    
    public static boolean checkExists(String table){
        DBConnect db = new DBConnect();
        String query = "SHOW TABLES LIKE '" + table + "'";
        
        String exists = db.SqlSelectSingle(query);
        db.Dispose();
        return Integer.parseInt(exists) != 0;       
    }
    
    public static void createAcc(String table){
        //Search for existing user account table
        
        DBConnect db = new DBConnect();
        db.SqlSelectSingle(table);
        db.Dispose();
        //if exist do nothing
    }

    public static ResultSet search(String tableName) {
        DBConnect db = new DBConnect();
        ResultSet rs = db.SqlSelectAll("SELECT * FROM " + tableName);
        return rs;
    }
    
    private void update(){
    
    //aggregate changes and update through db.sqlUpdate()
    }
}
