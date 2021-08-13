package ims;

import System_Forms.Login_Form;
import dbms.DBConnect;
import java.util.Scanner;
import javax.swing.JFrame;

public class main { 
    public static void main(String[] args) {
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
                checkOut();
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
    public static void launchGUI()
    {
        Login_Form form = new Login_Form();
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setVisible(true);
    }

    public static byte logIn(){
        Scanner selection = new Scanner(System.in);
        System.out.println("Username:");
        String name = selection.nextLine();
        
        System.out.println("Password:");
        String pswd = selection.nextLine();
        Boolean log = dbms.EmployeeDB.authenticate(name, pswd);
        //Use EmplyeeDB to check credentials and if true forward to normal user menu
        
        if (log == true){
            System.out.println("1 - View Profile");
            System.out.println("2 - View Account");// Temporarily Optional for testing purposes
            System.out.println("3 - Checkout Equipment");//Go straight to Inventory Menu
            System.out.println("4 - Check In Equipment");//Go to account menu for selecting returns
            System.out.println("0 - Exit");
            byte answer = selection.nextByte();
            return answer;
        }
        else{
            System.out.println("Incorrect credentials!");
            return 5;
        }
    }
    
    public static void checkOut(){
        
    }
    
    public static void checkIn(){
        
    }
}
