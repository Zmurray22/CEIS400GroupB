///Receives updates to account to create/update employee account table.
//Database Account table does not exist for a user until they rent equipment.
package dbms;

import java.time.LocalDate;

/**
 *
 * @author Zach
 */
public class Account {
    private String borrowedItems;
    private int borrowedQty;
    private LocalDate dateBorrowed;

    public void Account(){
        //Search for existing user account table
        //if exist do nothing
        //if not found, create table
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
    
    private void update(){
    
//aggregate changes and update through db.sqlUpdate()
    }
}
