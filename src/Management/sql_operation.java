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
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;
import static Management.FXMLDocumentController.*;
import static Management.Re_reservationController.*;
/**
 *
 * @author skylinkcomputer
 */
public class sql_operation {
    static String tariff_per_room,bed_type,room_type,rent,r_no;
     PreparedStatement pst=null;
       Connection con=null;
          Statement st=null;
          ResultSet rs=null;
          
          
public void room_list(){
    System.out.println("roomno"+room_no);
    try{
  
         Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
        st=con.createStatement();
         String sql="select * from room_list where Room_No='"+room_no+"'";
         pst=con.prepareStatement(sql);
         rs=pst.executeQuery();
         if(rs.next())
         {
             r_no=rs.getString("Room_no");
             room_type=rs.getString("Room_type");
              bed_type=rs.getString("Bed_Type");
              tariff_per_room=rs.getString("Tariff_Per_Room");
              System.out.println("roomno"+bed_type);
           
         }
       int r1=Integer.parseInt(tariff_per_room)*Integer.parseInt(total_days);
       rent=String.valueOf(r1);
    }
    catch(Exception ex){
        
    }
}
public void delete(){
           try{
             Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
        st=con.createStatement();    
  String delete="delete from "+tablename+" where Phone='"+phone_no+"' and Check_in='"+checkinDate+"'";
    pst=con.prepareStatement(delete);
    pst.execute();
    System.out.println(phone_no+"Record has been delete");
     JOptionPane.showMessageDialog(null,"Delete Successfull");
     }
     catch(Exception ex){
         System.out.println(ex);
     }
   
}
public void checkout(){
    String Room_no=null;
            String Room_Type=null;
            String Bed_Type=null;
            String rent=null;
            String d=null;
    try{
           Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
        st=con.createStatement();
        String SQL = "SELECT * from current_visitors where Phone='"+phone_no+"'";
          pst=con.prepareStatement(SQL);
         rs=pst.executeQuery();
        while(rs.next())
        {
            Room_no=rs.getString("Room_no");
             Room_Type=rs.getString("Room_Type");
             Bed_Type=rs.getString("Bed_Type");
             rent=rs.getString("Rent");
             d=rs.getString("day");
        }
        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
         int r=Integer.valueOf(rent)/Integer.valueOf(d);
         rent=String.valueOf(r);
         String sql1 = "Insert into room_list (Room_No,Room_type,Bed_Type,Tariff_Per_Room) value(?,?,?,?)";
            pst=con.prepareStatement(sql1);
            pst.setString(1,Room_no);
            pst.setString(2,Room_Type);
            pst.setString(3,Bed_Type);
            pst.setString(4,rent);
            pst.execute();  
           System.out.println("Room update successfull");
          String sql="insert into leave_visitor select * from  current_visitors where Phone='"+phone_no+"'";
          pst=con.prepareStatement(sql);
          pst.execute();
         
         
                   System.out.println("Record has been transfer into leave visitor which phone number is="+phone_no);        
                   String delete="delete from current_visitors where Phone='"+phone_no+"'";
               pst=con.prepareStatement(delete);
               pst.execute();
               
               FXMLDocumentController ob=new FXMLDocumentController();
               
     
     
     }
    catch(Exception ex){
         System.out.println(ex);
  }
}
public void updatecell(){
     int r1=Integer.parseInt(tariff_per_room)*Integer.parseInt(total_days);
       rent=String.valueOf(r1);
    try{
          Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
        st=con.createStatement();
         String sql="insert into current_visitors  select * from  leave_visitor where Phone='"+phone_no+"'";
         pst=con.prepareStatement(sql);
         pst.execute();
         String sql1="UPDATE current_visitors SET Rent='"+rent+"', Room_no='"+r_no+"', Room_Type='"+room_type+"', Bed_Type='"+bed_type+"', Check_in='"+checkin_date+"', Check_out='"+checkout_date+"', day='"+total_days+"' WHERE Phone='"+phone_no+"'";
         pst=con.prepareStatement(sql1);
         pst.execute();
         String delete1="delete from leave_visitor where Phone='"+phone_no+"'";
             pst=con.prepareStatement(delete1);
             pst.execute();
         String delete="delete from room_list where Room_No='"+r_no+"'";
             pst=con.prepareStatement(delete);
             pst.execute();
             System.out.println("testttttttt"+r_no);
             
    }
    catch(Exception ex){
        System.out.println(ex);
    }
}
}
