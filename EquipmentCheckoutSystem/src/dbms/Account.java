///Receives updates to account to create/update employee account table.
//Database Account table does not exist for a user until they rent equipment.
package dbms;

import java.time.LocalDate;
import com.sun.jdi.connect.spi.Connection;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
// fields- equip_id | title | qty | date
public class Account {

    public void Account(){
       
    }
    
    public static boolean checkExists(String table){
        DBConnect db = new DBConnect();
        String query = "SHOW TABLES LIKE '" + table + "'";
        
        String exists = db.SqlSelectSingle(query);
        db.Dispose();
        return !"0".equals(exists);       
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
    
    public static void update(String equip_id, String qty) throws ParseException{
        String string = "January 2, 2010";
        Date date = (Date) new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(string);
    //aggregate changes and update through db.sqlUpdate()
    //In Construction!!
    }
}
