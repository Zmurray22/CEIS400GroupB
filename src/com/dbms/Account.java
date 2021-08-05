package com.dbms;
//import for the datetime variables
import java.util.LocalDate;


public class Account {
  private String Borrowed_Items;
  private int Borrowed_Qty;
  private datetime Date_Borrowed;
  private int Returned_Qty;
  private datetime Returned_Date;

//Account with no variables
  public display(){
    this.Borrowed_Items = "Unknown";
    this.Borrowed_Qty = 0
    this.Date_Borrowed = "Unknown"
    this.Returned_Qty = 0
    this.Returned_Date = "Unknown"
  }




public String getBorrowed_Items(){
  return Borrowed_Items;
}

public void setBorrowed_Items(String Borrowed_Items){
  this.Borrowed_Items = Borrowed_Items;
}

public int getBorrowed_Qty(){
  return Borrowed_Qty;
}

public void setBorrowed_Qty(int Borrowed_Qty){
  this.Borrowed_Qty = Borrowed_Qty;
}

public int getReturned_Qty(){
  return Returned_Qty;
}

public void setReturned_Qty(int Returned_Qty){
  this.Returned_Qty = Returned_Qty;
}

public getDate_Borrowed(){
  LocalDate now = LocalDate.now();
}

public getDate_Returned(){
  LocalDate now = LocalDate.now();
}
}
