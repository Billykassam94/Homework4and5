/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homework4and5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nabil
 */
public class DBConnector{
    
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
    
    
    public void addData() throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        
        
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        // buffered reader to read csv file
        BufferedReader myReader1 = new BufferedReader(new FileReader("C:\\Users\\nabil\\Desktop\\Software development\\Programming Obj.-Oriented Approach\\Term 2\\Homework\\newData.csv"));

        // array list to store elements
        ArrayList<String> elements = new ArrayList<>();
        
        String line1 = null;

        myReader1.readLine();
        //loop to read all lines.
        while ((line1 = myReader1.readLine()) != null) {
            //splits each line into elements
            String[] row = line1.split(",");
//            System.out.println(row[1]);
            elements.add(row[1]);
        }

        myReader1.close();

        
        
    
        
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost", "pooa", "pooa");
            Statement stmt = conn.createStatement();
            stmt.execute("USE homework5");
            conn.setAutoCommit(false);

            String sql = "INSERT INTO orders(Invoice,StockCode,Description,Quantity,InvoiceDate,Price,Customer,Country) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);

            BufferedReader myReader = new BufferedReader(new FileReader("C:\\Users\\nabil\\Desktop\\Software development\\Programming Obj.-Oriented Approach\\Term 2\\Homework\\newData.csv"));

            String line = myReader.readLine();
            
            myReader.readLine();
            while (line  != null) {
                // string array values that splits line by commas.
                String[] values = line.split(",");

                String Invoice = values[0];
                String StockCode = values[1];
                String Description = values[2];
                String Quantity = values[3];
                String InvoiceDate = values[4];
                String Price = values[5];
                String Customer = values[6];
                String Country = values[7];

                statement.setString(1, Invoice);
                statement.setString(2, StockCode);
                statement.setString(3, Description);
                statement.setString(4, Quantity);
                statement.setString(5, InvoiceDate);
                statement.setString(6, Price);
                statement.setString(7, Customer);
                statement.setString(8, Country);
                statement.addBatch();

            }
            
            int count = 0;
            
            //assigns variable to the amount of elemnts by multiplying total rows by 8
            int batchSize = elements.size() * 8;
            
            //if statement to excute batch until total amount is complete
            if (count == batchSize) {
                statement.executeBatch();
                count++;
            }

            myReader.close();
            statement.executeBatch();
            conn.commit();
            conn.close();

            System.out.println("Data Imported");


        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }

    
         
           
        
    
    }
    
}
