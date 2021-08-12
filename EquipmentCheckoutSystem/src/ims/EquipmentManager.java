package ims;


import org.w3c.dom.ls.LSOutput;

import java.util.Objects;

public class EquipmentManager {

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
        System.out.println("Employee is verified");
    }

    public String getfName() { return fName; }

    public void setfName(String fName) { this.fName = fName; }
    
    public String getlName() { return lName; }
    
    public void setlName(String lName) { this.lName = fName; }
    
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getPasswordVerifed() { return passwordVerified; }

    public void setPassword(String passwordVerifed) {
        this.passwordVerified = passwordVerifed;
    }

    public int getVerifyEquipMngr() { return verifyEquipManager; }

    public void setVerifyEquipMngr(int verifyEquipManager) {
        this.verifyEquipManager = verifyEquipManager;
    }

    public int getSearchDatabase() { return searchDatabase; }

    public void setSearchDatabase(int searchDatabase) {
        this.searchDatabase = searchDatabase;
    }

    @Override
    public String toString() {
        return "EquipmentManager{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", password='" + passwordVerified + '\'' +
                ", id=" + id +
                ", verifyEquipManager=" + verifyEquipManager +
                ", searchDatabase=" + searchDatabase +
                '}';
    }
}
