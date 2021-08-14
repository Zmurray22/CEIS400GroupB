package ims;

import org.w3c.dom.ls.LSOutput;

import java.util.Objects;

import dbms.EmployeeDB;

public class EquipmentManager {
    public static void main(String[] args) {
    }
    private String fName;
    private String lName;
    private String passwordVerified;
    private int id;
    private int verifyEquipManager;
    private int searchDatabase;
    
// This will grab the Employee Information
    public EquipmentManager(String fName, String lName, int id, String passwordVerified) {
       this.fName = fName;
       this.lName = lName;
       this.id =id;
       this.passwordVerified = passwordVerified;
       System.out.println("You are verified.");
    }
    public String getfName() {
        return fName;
    }
    public void setfName(String fName) {
        this.fName = fName;
    }
    public String getlName() {
        return lName;
    }
    public void setlName(String lName) {
        this.lName = fName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPasswordVerifed() {
        return passwordVerified;
    }
    public void setPasswordVerified(String passwordVerifed) {
        this.passwordVerified = passwordVerifed;
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
        Boolean login = false;
        return login;
    }
    public void verifyEqupiManager(){
    }
    private void searchDataBase(){
        System.out.println("Your order is on the way.");
        
        
        EmployeeDB.search(fName, lName, id, passwordVerified);
    }
}
