package ims;

import org.w3c.dom.ls.LSOutput;

import java.util.Objects; 

import dbms.EmployeeDB;

import dbms.InventoryDB;

import dbms.DBConnect;

import dbms.EquipmentRequest;

import dbms.OrderDetail;



public class EquipmentManager {

    private static String username;
    public static void main(String[] args) {
    }
    private String fName;
    private String lName;
    private String password;
    private int employ_id;
    private int verifyEquipManager;
    private int searchDatabase;
    
// This will grab the Employee Information
    public EquipmentManager(String fName, String lName, int employ_id, String password) {
       this.fName = fName;
       this.lName = lName;
       this.employ_id =employ_id;
       this.password = password;
       System.out.println("You are verified.");
    }
    public String getfirstName() {
        return fName;
    }
    public void setfirstName(String fName) {
        this.fName = fName;
    }
    public String getlName() {
        return lName;
    }
    public void setlName(String lName) {
        this.lName = lName;
    }
    public int getemploy_Id() {
        return employ_id;
    }
    public void setemploy_Id(int employ_id) {
        this.employ_id = employ_id;
    }
    public String get() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getVerifyEquipMngr() {
        return verifyEquipManager;
    }
    public void setVerifyEquipMngr(int verifyEquipManager) {
        this.verifyEquipManager = verifyEquipManager;
    }
    public int getSearchDatabase() {
        return searchDatabase;
    }
    public void setSearchDatabase(int searchDatabase) {
        this.searchDatabase = searchDatabase;
    }
    public Boolean Verify(){
        Boolean login = true;
        return login;
    }
    public void verifyEqupiManager(){
    }
    private void searchDataBase(){
        System.out.println("Your order is on the way.");
    }
    public static ResultSet search(String fName, String lName, String employ_id, String password, DBConnect db) {
        ResultSet rs = (ResultSet) db.SqlSelectAll("SELECT * FROM employee WHERE empl_id LIKE '%" + employ_id + "%' OR lname LIKE '%" + lName + "%' OR username LIKE '%" + username + "%' OR password LIKE '%" + password + "%'");
        return rs;
    }

    private static class ResultSet {

        public ResultSet() {
        } 
    }
    
    }
