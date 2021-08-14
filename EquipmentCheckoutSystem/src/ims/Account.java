/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims;

import java.time.LocalDate;

/**
 *
 * @author Zach
 */
public class Account {
    private String borrowedItems;
    private int borrowedQty;
    private LocalDate dateBorrowed;
    private int returnedQty;
    private LocalDate returnedDate;

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

    public int getReturnedQty() {
        return returnedQty;
    }

    public void setReturnedQty(int returnedQty) {
        this.returnedQty = returnedQty;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    } 
}
