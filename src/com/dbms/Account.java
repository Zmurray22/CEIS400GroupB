package com.dbms;
//import for the LocalDate variables
import java.time.LocalDate;

public class Account {
  private String borrowedItems;
  private int borrowedQty;
  private LocalDate dateBorrowed;
  private int returnedQty;
  private LocalDate returnedDate;

//Account with no variables
 public Account() {
        this.borrowedItems = "Unknown";
        this.borrowedQty = 0;
        this.dateBorrowed = null;
        this.returnedQty = 0;
        this.returnedDate = null;
    }

//Account with provided info
    public Account(String borrowedItems, int borrowedQty, LocalDate dateBorrowed, int returnedQty, LocalDate returnedDate) {
        this.borrowedItems = borrowedItems;
        this.borrowedQty = borrowedQty;
        this.dateBorrowed = dateBorrowed;
        this.returnedQty = returnedQty;
        this.returnedDate = returnedDate;
    }


//Borrowed Items Getter-Setter
//Getter
public String getBorrowedItems(){
  return borrowedItems;
}
//Setter
public void setBorrowedItems(String borrowedItems){
  this.borrowedItems = borrowedItems;
}

//Borrowed Quantity Getter-Setter
//Getter
public int getBorrowedQty(){
  return borrowedQty;
}

//Setter
public void setBorrowedQty(int borrowedQty){
  this.borrowedQty = borrowedQty;
}

// Returned Quantity Getter-Setter
// Getter
public int getReturnedQty(){
  return returnedQty;
}

// Setter
public void setReturnedQty(int returnedQty){
  this.returnedQty = returnedQty;
}

//Date Borrowed Getter-Setter
// Getter
public LocalDate getdateBorrowed() {
    return dateBorrowed;
}

// Setter
public void setdateBorrowed(LocalDate dateBorrowed) {
    this.dateBorrowed = dateBorrowed;
}

// Returned Date Getter-Setter
// Getter
public LocalDate getreturnedDate() {
    return returnedDate;
}

// Setter
public void setreturnDate(LocalDate returnedDate) {
    this.returnedDate = returnedDate;
}
}
