/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author AH TANVIR
 */
public class Sqldb {
     Connection con=null;
     Statement st=null;
      ResultSet rs=null;
      String cc="0";
    PreparedStatement pst=null;
  public void sqldb(){
         try{
         Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
        st=con.createStatement();
        String sql="CREATE DATABASE IF NOT EXISTS hotel_management";
        pst=con.prepareStatement(sql);
        pst.execute();
        System.out.println(" db is create");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
        String table="CREATE TABLE IF NOT EXISTS room_list (" + "Room_No int(30)," + "Room_type varchar(39)," + "Bed_Type varchar(30),"+"Tariff_Per_Room varchar(30),"+"PRIMARY KEY(room_No))";
    pst=con.prepareStatement(table);
        pst.execute();
        System.out.println("roomlist table is create");
        String table1="CREATE TABLE IF NOT EXISTS login (" + "name varchar(30)," + "username varchar(39)," + "password varchar(30),"+"PRIMARY KEY(username))";
        pst=con.prepareStatement(table1);
       pst.execute();
        System.out.println("login table is create");
         String table2="CREATE TABLE IF NOT EXISTS current_visitors (" + "Name varchar(30)," + "Age varchar(30)," + "Relationship varchar(30)," + "ID_NO varchar(100),"+"City varchar(30),"+"Country varchar(30),"+"Nationality varchar(30),"+"Address varchar(255),"+"No_of_visitor varchar(30),"+"Purpose varchar(30),"+"Phone varchar(55),"+"Rent varchar(30),"+"Room_no varchar(30),"+"Room_Type varchar(30),"+"Bed_Type varchar(30),"+"Check_in varchar(100),"+"Check_out varchar(100),"+"day varchar(55),"+"PRIMARY KEY(Phone))";
      pst=con.prepareStatement(table2);
      pst.execute();
          System.out.println("Current visitor table is create");
       String table3="CREATE TABLE IF NOT EXISTS leave_visitor (" + "Name varchar(30)," + "Age varchar(30)," + "Relationship varchar(30)," + "ID_NO varchar(100),"+"City varchar(30),"+"Country varchar(30),"+"Nationality varchar(30),"+"Address varchar(255),"+"No_of_visitor varchar(30),"+"Purpose varchar(30),"+"Phone varchar(55),"+"Rent varchar(30),"+"Room_no varchar(30),"+"Room_Type varchar(30),"+"Bed_Type varchar(30),"+"Check_in varchar(100),"+"Check_out varchar(100),"+"day varchar(55),"+"PRIMARY KEY(Phone))";
      pst=con.prepareStatement(table3);
      pst.execute();
          System.out.println("leave visitor table is create");
     }
 catch(Exception ex){
     System.out.println("sqldb error"+ex);
 }      
    }
}
