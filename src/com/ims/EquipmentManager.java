package com.ims;


import org.w3c.dom.ls.LSOutput;

import java.util.Objects;

public class EquipmentManager {


    public static void main(String[] args) {

    }
    private String fName;
    private String lName;
    private String passwordVerified;
    private int id;
    private int verifyEquipmentsManager;
    private int searchDataBase;

// This will grab the Employee Information
    public EquipmentManager(String fName, String lName, int id, String passwordVerified) {
        this.fName = fName;
       this.lName = lName;
       this.id =id;
       this.passwordVerified = passwordVerified;
        System.out.println("Employee is verified");
    }

    public String getfName() {
        return fName;

    }

    public void setlName(String fName) {
        this.lName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setfName(String lName) {
        this.lName = lName;
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

    public void setPassword(String passwordVerifed) {
        this.passwordVerified = passwordVerifed;
    }

    public int getVerifyEquipmentsManager() {
        return verifyEquipmentsManager;
    }

    public void setSearchDataBase(int searchDataBase) {
        this.searchDataBase = searchDataBase;
    }

    public int getSearchDataBase() {
        return searchDataBase;
    }

    public void setVerifyEquipmentsManager(int verifyEquipmentsManager) {
        this.verifyEquipmentsManager = verifyEquipmentsManager;
    }

    @Override
    public String toString() {
        return "EquipmentManager{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", password='" + passwordVerified + '\'' +
                ", id=" + id +
                ", verifyEquipmentsManager=" + verifyEquipmentsManager +
                ", searchDataBase=" + searchDataBase +
                '}';
    }
}
