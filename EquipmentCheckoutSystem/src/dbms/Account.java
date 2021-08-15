///Receives updates to account to create/update employee account table.
//Database Account table does not exist for a user until they rent equipment.
package dbms;

import java.time.LocalDate;
import com.sun.jdi.connect.spi.Connection;
import java.sql.*;

public class Account {
    private String borrowedItems;
    private int borrowedQty;
    private LocalDate dateBorrowed;

    public void Account(){
       
    }
    
    public String getBorrowedItems() {
        return borrowedItems;
    }

    public void setBorrowedItems(String borrowedItems) {
        this.borrowedItems = borrowedItems;
    }

    public int getBorrowedQty() {
        return borrowedQty;
    }

    public void setBorrowedQty(int borrowedQty) {
        this.borrowedQty = borrowedQty;
    }

    public LocalDate getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(LocalDate dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }
    
    public static boolean checkExists(String table){
        DBConnect db = new DBConnect();
        table = "SELECT count(*) "
                + "FROM information_schema.tables "
                + "WHERE table_name = ?"
                + "LIMIT 1;";
        
        String exists = db.SqlSelectSingle(table);
        db.Dispose();
        return Integer.parseInt(exists) != 0;       
    }
    
    public void createAcc(Connection con, String table){
        //Search for existing user account table
        if (!checkExists(table)){
            
        }
        //if exist do nothing
    }
    
    private void update(){
    
//aggregate changes and update through db.sqlUpdate()
    }
}
