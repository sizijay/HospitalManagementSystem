/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
//import needed methods
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author SiziJayawardena
 */
//implement the Database class
public class Database {
    //implement the getStatement() method
    public static Statement getStatement(){
        try{
            //make a connection to the Database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/HospitalSystem","root","root@123");
            
            //get a new Statement
            Statement stat = con.createStatement();
            
            //return the statement
            return stat;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        } 
    }
    
}



