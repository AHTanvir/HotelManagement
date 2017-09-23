/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static Management.FXMLDocumentController.tablename;

/**
 *
 * @author anwar
 */
public class backupandrestore {
    public void backup(){
           Connection con=null;
     Statement st=null;
      ResultSet rs=null;
    PreparedStatement pst=null;
    String N=null;
    String D=null;
    String A=null;
 
 String path="E:\\"+tablename+".CSV";
        File f=new File(path);  
      
       try{
            
     BufferedWriter  fw=new BufferedWriter(new FileWriter(f));
     PrintWriter pw=new PrintWriter(fw);
            Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");

         st=con.createStatement();
        //* pw.println("Name,  Age,  Relationship,  ID_NO,  City,  Country,  Nationality,  Address,  No_of_visitor,  Purpose,  Phone,  Rent,  Room_no,  Room_Type,  Bed_Type,  Cheek_in,  Cheek_out");
        String sql = "select * from "+tablename+"";
        rs=st.executeQuery(sql);
       while(rs.next())
        {
            
            
                String Name=rs.getString("Name");
                String Age=rs.getString("Age"); 
                String Relationship=rs.getString("Relationship");
                String ID_NO=rs.getString("ID_NO");
                String City=rs.getString("City");
                String Country=rs.getString("Country");
                String Nationality=rs.getString("Nationality");
                String Address=rs.getString("Address");
                String No_of_visitor=rs.getString("No_of_visitor");
                String Purpose=rs.getString("Purpose");
                String Phone=rs.getString("Phone");
                String Rent=rs.getString("Rent");
                String Room_no=rs.getString("Room_no");
                String Room_Type=rs.getString("Room_Type");
                String Bed_Type=rs.getString("Bed_Type");
                String Check_in=rs.getString("Check_in");
                String Check_out=rs.getString("Check_out");
                String day=rs.getString("day");
                
                
                
        
       pw.println(Name+"," +Age+ ","+Relationship+","+ID_NO+","+City+","+Country+ ","+Nationality+","+Address+","+No_of_visitor+","+Purpose+","+Phone+","+Rent+","+Room_no+","+Room_Type+","+Bed_Type+","+Check_in+","+Check_out+","+day);
       
       
     System.out.println("PDF has beencreate");
     
        }
        pw.close();

        }
     catch(Exception e){
         System.out.println(e);
     }
      
  
    }
   public void restore(){
       Connection con=null;
     Statement st=null;
      ResultSet rs=null;
    PreparedStatement pst=null;
    String N=null;
    String D=null;
    String A=null;
 
 String path="E:\\"+tablename+".csv";
        File f=new File(path);
      System.out.println("Pleae wait....................................................................................................");
        try{
          Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
        st=con.createStatement();
  
         BufferedReader br=new BufferedReader(new FileReader(f));
         String line;
 
         while((line=br.readLine())!=null)
         {
         
             String []value=line.split(",");
             System.out.println(value[0]);
           String sql1 = "Insert INTO "+tablename+" (Name,Age,Relationship,ID_NO,City,Country,Nationality ,Address,No_of_visitor,Purpose,Phone,Rent,Room_no,Room_Type,Bed_Type,Check_in,Check_out,day) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst=con.prepareStatement(sql1);
            pst.setString(1,value[0]);
            pst.setString(2,value[1]);
            pst.setString(3,value[2]);
            pst.setString(4,value[3]);
            pst.setString(5,value[4]);
            pst.setString(6,value[5]);
            pst.setString(7,value[6]);
            pst.setString(8,value[7]);
            pst.setString(9,value[8]);
            pst.setString(10,value[9]);
            pst.setString(11,value[10]);
            pst.setString(12,value[11]);
            pst.setString(13,value[12]);
            pst.setString(14,value[13]);
            pst.setString(15,value[14]);
            pst.setString(16,value[15]);
            pst.setString(17,value[16]);
          pst.setString(18,value[17]);
                    
            pst.execute();
                
             }        
             
         br.close();
         System.out.println("Your file has been uploaded");
     }
     catch(Exception ex){
         System.out.println(ex);
     }
        
   }
   
    
}
