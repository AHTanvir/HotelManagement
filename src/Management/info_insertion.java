/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.sql.*;
import javax.swing.JOptionPane;
import static Management.FXMLDocumentController.*;


/**
 *
 * @author skylinkcomputer
 */
public class info_insertion {
    PreparedStatement pst=null;
       Connection con=null;
          Statement st=null;
          ResultSet rs=null;
    public void info_insertion(){
           try{
                       Class.forName("com.mysql.jdbc.Driver");
                       con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
                       st=con.createStatement();
                       String sql="Insert into current_visitors (Name,Age,Relationship,ID_NO,City,Country,Nationality,Address,No_of_visitor,Purpose,Phone,Rent,Room_no,Room_Type,Bed_Type,Cheek_in,Cheek_out,day) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                      // pst=con.prepareStatement(sql);
                       //pst.setString(1,tf1.getText());
                       //pst.setString(2,tf2.getText());
                       //pst.setString(3,tf3.getText() );
                      // pst.setString(4,tf4.getText());
                     //  pst.setString(5,tf5.getText());
                      // pst.setString(6,tf6.getText());
                       //pst.setString(7,tf7.getText());
                       //pst.setString(8,tf8.getText());
                       //pst.setString(9,tf9.getText());
                     //  pst.setString(10,tf10.getText());
                       //pst.setString(11,tf11.getText());
                       pst.setString(12,"");
                       pst.setString(13,"");
                       pst.setString(14,"");
                       pst.setString(15,"");
                       pst.setString(16,checkin_date);
                       pst.setString(17,checkout_date);
                        pst.setString(18,total_days);
                       pst.execute();
                       System.out.println("Current visitor table has been update");
                       //String a=booking_form.a;
                       //String delete="delete from room_list where Room_No='"+a+"'";
                       //pst=con.prepareStatement(delete);
                       pst.execute();
                     
                    JOptionPane.showMessageDialog(null,"Book Confirm");
                     // System.out.println(a+"Room has been delete");
                      
                   }
                   catch(Exception ex){
                       System.out.println(ex);
                       JOptionPane.showMessageDialog(null,"This phone number already exist");
                   }
    }
}
