/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homework4and5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nabil
 */
public class DBConnector {
    
     // final = constant, cannot change
    private final String DB_URL = "jdbc:mysql://localhost";
    private final String USER = "pooa";
    private final String PASSWD = "pooa";
    
    
    // method to create database.
    public void createDB() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
            Statement stmt = conn.createStatement();
            
            //query to delete database so data isnt duplicated when program is running while testing.
            stmt.execute("DROP DATABASE IF EXISTS homework5;");
            //query to create database
            stmt.execute("CREATE DATABASE IF NOT EXISTS homework5;");
            System.out.println("DB Created");

            // query to create table homework5
            stmt.execute("USE homework5");
            stmt.execute("CREATE TABLE IF NOT EXISTS orders ("
                    //elemnts in table
                    + "Invoice VARCHAR (80),"
                    + "StockCode VARCHAR (80),"
                    + "Description VARCHAR (80),"
                    + "Quantity VARCHAR (80),"
                    + "InvoiceDate VARCHAR (80),"
                    + "Price VARCHAR (80),"
                    + "Customer VARCHAR (80),"
                    + "Country VARCHAR (80)"
                    + ");");
            System.out.println("table Created");
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
