package ims;

import System_Forms.Login_Form;
import com.sun.jdi.connect.spi.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;
import dbms.DBConnect;
import dbms.InventoryDB;
import dbms.EmployeeDB;
import dbms.VendorDB;
import dbms.Account;
import dbms.EquipmentRequest;
import ims.EquipmentManager;
import ims.MaintEmployee;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JFrame;
//testing for push and pull from github desktop

//test for Zach
public class main { 
    public static void main(String[] args) throws SQLException {
        //DBConnect db = new DBConnect();
        byte choice;
        do{
            //Testing console UI
            System.out.println("Equipment Checkout System");
            System.out.println("*".repeat(25) );

            choice = mainMenu();
            switch (choice) {
            case 1:
                testDatabase();
                break;
            case 2:
                System.out.println("Log In Menu\n\n");
                logIn();

                break;
            case 3:
                System.out.println("Check Out Equipment\n\n");
                /*checkOut();*/
                break;
            case 4:
                System.out.println("Check In Equipment\n\n");
                checkIn();
                break;
            case 5:
                launchGUI();
            case 0:
                //Close app
                System.out.println("Thanks for using\n\n");
                break;
            default:
                // The user input an unexpected choice.
                System.out.println("Invalid reponse, Try again.\n\n");
            }    
        }while(choice != 0);
    }
                  
    public static void testDatabase(){
        DBConnect db = new DBConnect();
        
        if (db.DBReadyForUse()) 
        {
            // Run some test queries
            System.out.println();
            System.out.println("The first user is: " + db.SqlSelectSingle("SELECT concat(fname, ' ', lname) FROM employee WHERE empl_id = '0001'"));
            
            System.out.println();
            db.SqlInsert("employee", "empl_id, fname, lname, access, phone, username, password", "'9999', 'Tester', 'McTest', '1', 'No Phone', 'T-Act', 'NotSecure123'");
            System.out.println("Newly created user is: " + db.SqlSelectSingle("SELECT concat(fname, ' ', lname) FROM employee WHERE empl_id = '9999'"));
            
            System.out.println();
            db.SqlUpdate("employee", "password = 'This_is_b3tt3r'", "empl_id = '9999'");
            System.out.println("The updated password is: " + db.SqlSelectSingle("SELECT password FROM employee WHERE empl_id = '9999'"));
            
            System.out.println();
            db.SqlDelete("employee", "empl_id = '9999'");
            if (db.SqlSelectSingle("SELECT concat(fname, ' ', lname) FROM employee WHERE empl_id = '9999'").equals(""))
                System.out.println("The test employee no longer exists");
            else
                System.out.println("This method failed");
        }
    }
    
    public static byte mainMenu() {
        //Main menu method
      Scanner selection = new Scanner(System.in);  // Create a Scanner object
      
      System.out.println("1 - Test Connection");
      System.out.println("2 - Log In");// Temporarily Optional for testing purposes
      System.out.println("3 - Checkout Equipment");//Go straight to Inventory Menu
      System.out.println("4 - Check In Equipment");//Go to account menu for selecting returns
      System.out.println("5 - Launch GUI Application");
      System.out.println("0 - Close app");
      byte answer = selection.nextByte();
      return answer;    
  }
    
    public static void launchGUI()
    {
        Login_Form form = new Login_Form();
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setVisible(true);
    }

    public static void logIn() throws SQLException{
        Scanner selection = new Scanner(System.in);
        System.out.println("Username:");
        String username = selection.nextLine();
        
        System.out.println("Password:");
        String pswd = selection.nextLine();
        Boolean log = dbms.EmployeeDB.authenticate(username, pswd);
        //Use EmplyeeDB to check credentials and if true forward to normal user menu
        byte answer = 0;
        
        do{
            if (log == true){//Log in success, display user menu
                System.out.println("1 - View Profile");
                System.out.println("2 - View Account");// Temporarily Optional for testing purposes
                System.out.println("3 - Checkout Equipment");//Go straight to Inventory Menu
                System.out.println("4 - Check In Equipment");//Go to account menu for selecting returns
                System.out.println("0 - Exit");
                answer = selection.nextByte();
            }
            else{//Login fails, go back to log in menu
                System.out.println("Incorrect credentials!");
                return;
            }

            switch (answer){
                case 1:
                    System.out.println("View Profile\n" + "*".repeat(12));
                    Account.userProfile(username);
                    break;
                case 2:
                    System.out.println("View Account\n" + "*".repeat(12));
                    Account.showAccount(username);
                    break;
                case 3:
                    System.out.println("Checkout Equipment\n" + "*".repeat(18));

                    break;
                case 4:
                    System.out.println("Check In Equipment\n" + "*".repeat(18));

                    break;
                case 0:
                    break;

            }
        }while(answer != 0);    
    }

    
    /*public static void checkOut(){
        Scanner selection = new Scanner(System.in);
        System.out.println("What is the equipment ID of the Equipment you need:");
        String EquipID = selection.nextLine();
        System.out.println(InventoryDB.Search(EquipID, db));        
    }*/
    
    public static void checkIn(){
        DBConnect db = new DBConnect();
        Scanner selection = new Scanner(System.in);
        System.out.println("Please enter the followed, pressing enter after each entry. Equipment ID, Equipment Name, Amount you are returning.");
        String EquipID = selection.nextLine();
        String EquipName = selection.nextLine();
        int Amount = selection.nextInt();
        System.out.println("This is the following that you entered: " + EquipID + " " + EquipName+ " " + Amount);
    }
}
