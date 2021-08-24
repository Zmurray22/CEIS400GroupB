package dbms;

import java.sql.*;

public class DBConnect
{

    private Connection Conn; // This will be our class variable to store the database connection
    
    public Connection getConn() {
        return Conn;
    }
    
    public DBConnect() {
        //try to connect to the database
        Conn = null; // We'll first set the connection to null so we can verify it's connection later
        try {
            //drivermanager
            Class.forName("com.mysql.cj.jdbc.Driver");

            //String url = "jdbc:mysql://us-cdbr-east-04.cleardb.com;databaseName=heroku_d1b728abb3f0bfd;user=b6d60dc47eedf6;password=14708690";
            //Database login info
            String url = "jdbc:mysql://us-cdbr-east-04.cleardb.com/heroku_d1b728abb3f0bfd";
            String username = "b6d60dc47eedf6";
            String password = "14708690";

            //Connection
            try
            {
                Conn = DriverManager.getConnection(url, username, password);
                
                //statement
                //System.out.println("Connection Successful!");
                int count = 0;
                /*ResultSet result = SqlSelectAll("SELECT * FROM employee"); // Use our class method to run the query for us.
                //if you want to run the console without the employee database showing up, comment out the result set function to the result.close, and prior to the catch SQLExecution ex
                while (result.next()){
                    String emplID = result.getString("empl_id");
                    String fName = result.getString("fname");
                    String lName = result.getString("lname");
                    String access = result.getString("access");
                    String output = "User #%d: %s - %s - %s - %s";
                    System.out.println(String.format(output, ++count, emplID, fName, lName, access));
                }
                result.close();*/ // Always make sure to close your result sets

            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Error");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Could not connect to database");
        }
    }
    
    // Below are the methods to manage the database functions
    public void Dispose() {
        try {
            Conn.close(); // We're closing the connection to ensure that there are no orphaned connections to our database
        }
        catch (SQLException err) { 
            err.printStackTrace();
            System.out.println("Error: " + err.getMessage());
        }
    }
    public void SqlInsert(String table, String columns, String values) {
        // Example use DBConnect.SqlInsert("employee", "empl_id, fname, lname, access, phone, username, password", "'0001', 'John', 'Doe', '1', '123-456-7890', 'jdoe', 'MySuperSecretPwd'");
        try {
            // Run the SQL insert statment based on the table data provided
            Statement SqlStmt = Conn.createStatement();
            SqlStmt.executeUpdate("INSERT INTO " + table + " (" + columns + ") VALUES (" + Clean(values) + ")");
            System.out.println("Insert completed successfully!");
        }
        catch (Exception err) {
            err.printStackTrace();
            System.out.println("An error occured when inserting data into " + table + "." +
                               "\nPlease review the error below for the cause of the exception.\nError: " + err.getMessage());
        }
    }
    public void SqlDelete(String table, String where) {
        // Example use DBConnect.SqlDelete("employee", "empl_id = '0002'");
        try {
            // Delete all the data from the table where the values equal what's in the where statement
            Statement SqlStmt = Conn.createStatement();
            SqlStmt.executeUpdate("DELETE FROM " + table + " WHERE " + Clean(where));
            System.out.println("Delete compeleted successfully!");
        }
        catch (Exception err) {
            err.printStackTrace();
            System.out.println("An error occured when deleting data from " + table + "." +
                               "\nPlease review the error below for the cause of the exception.\nError: " + err.getMessage());
        }
    }
    public void SqlUpdate(String table, String set, String where) {
        // Example use DBConnect.SqlUpdate("employee", "fname = 'Jon'", "emlp_id = '0002'");
        try {
            // Update the table based on the values provided
            Statement SqlStmt = Conn.createStatement();
            SqlStmt.executeUpdate("UPDATE " + table + " SET " + Clean(set) + " WHERE " + Clean(where));
            System.out.println("Update completed successfully!");
        }
        catch (Exception err) {
            err.printStackTrace();
            System.out.println("An error occured when updating data inside " + table + "." +
                               "\nPlease review the error below for the cause of the exception.\nError: " + err.getMessage());
        }
    }
    
    public ResultSet SqlSelectAll(String query) {
        // Example use DBConnect.SqlSelectAll("SELECT * FROM employee");
        ResultSet rs = null; // We'll make the default ResultSet null so our other can code can validate whether there is a query returned
        try {
            // Retrieve the ResultSet from the given query
            Statement sqlStmt = Conn.createStatement();
            rs = sqlStmt.executeQuery(Clean(query));
        }
        catch (Exception err) {
            err.printStackTrace();
            System.out.println("An error occured when executing the query '" + query + "'.\nPlease review the error below for the cause of the exception." +
                               "\nError: " + err.getMessage());
        }
        return rs;
    }
    public String SqlSelectSingle(String query) {
        // Example use DBConnect.SqlSelectSingle("SELECT fname + ' ' + lname FROM employee WHERE empl_id = '0002'");
        String val = ""; // We will return a blank String if the query fails.
        try {
            Statement sqlStmt = Conn.createStatement();
            ResultSet rs = sqlStmt.executeQuery(Clean(query));
            if (rs.next()) // We only want the first value of the first column if there is data returned
                val = rs.getString(1); // We want to convert the returned database value to a string even if it's not. We can convert the value back later.
            rs.close(); // Remember to close your ResultSets
            sqlStmt.close(); // And your SQL commands
        }
        catch (Exception err) {
            err.printStackTrace();
            System.out.println("An error occured when executing the query '" + query + "'.\nPlease review the error below for the cause of the exception." +
                               "\nError: " + err.getMessage());
        }
        return val;
    }
    public void SqlCreateFromTemplate(String templateName, String newTableName){
            String query = "CREATE TABLE IF NOT EXISTS " + newTableName.toLowerCase() + " LIKE " + templateName.toLowerCase() + ";";
            String query2 = "INSERT " + newTableName + " SELECT * FROM " + templateName.toLowerCase() + ";";
        try{
            Statement sqlStmt = Conn.createStatement();
            sqlStmt.executeUpdate(Clean(query));
            sqlStmt.executeUpdate(Clean(query2));
        }
        catch (Exception err){
            err.printStackTrace();
            System.out.println("An error occured when executing the query '" + query + "'.\nPlease review the error below for the cause of the exception." +
                               "\nError: " + err.getMessage());
        }
    } 
    public Boolean DBReadyForUse() {
        if (Conn != null)
            return true;
        return false; // Note: we do not need an else statment becuase this code will not run anyways if the above if statement is false
    }
    
    // Below are the private methods to the class that will be used to validate the user's input
    public static String Clean(String original) {
        return original.replace(";", ""); // Note: we use String.replace(String, String) instead of chars because an error is thrown when replacing a char with a blank.
    }
}