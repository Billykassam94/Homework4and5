/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package homework4and5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author nabil
 */
public class Homework4and5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException {
 
        
        DBConnector dbconn = new DBConnector();
        
        
        //method to create database and table
        dbconn.createDB();
        
        dbconn.addData();

    

        
    }
}
  

