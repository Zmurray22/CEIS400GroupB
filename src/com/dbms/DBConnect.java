package com.dbms;

import java.sql.*;

public class DBConnect
{
    public DBConnect()
    {
        //try to connect to the database
        try {
            //drivermanager
            Class.forName("com.mysql.cj.jdbc.Driver");
            //String url = "jdbc:sqlserver://localhost;";


            //String url = "jdbc:mysql://us-cdbr-east-04.cleardb.com;databaseName=heroku_d1b728abb3f0bfd;user=b6d60dc47eedf6;password=14708690";
            //Database login info
            String url = "jdbc:mysql://us-cdbr-east-04.cleardb.com/heroku_d1b728abb3f0bfd";
            String username = "b6d60dc47eedf6";
            String password = "14708690";

            //Connection
            try (Connection conn = DriverManager.getConnection(url, username, password))
            {
            //statement
                System.out.println("Connection Successful!");

                String sql = "SELECT * FROM employee";

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                int count = 0;

                while (result.next()){
                    String emplID = result.getString("empl_id");
                    String fName = result.getString("fname");
                    String lName = result.getString("lname");
                    String access = result.getString("access");

                    String output = "User #%d: %s - %s - %s - %s";
                    System.out.println(String.format(output, ++count, emplID, fName, lName, access));
                }

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
}