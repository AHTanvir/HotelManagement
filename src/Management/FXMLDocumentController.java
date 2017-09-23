/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import static Management.Re_reservationController.checkinDate;
import static Management.sql_operation.*;



/**
 *
 * @author skylinkcomputer
 */
public class FXMLDocumentController implements Initializable {
      PreparedStatement pst=null;
       Connection con=null;
          Statement st=null;
          ResultSet rs=null;
   @FXML
   ComboBox cbox,cbox2,cbox3;
   @FXML         
     DatePicker date_selector1=null,date_selector2=null;
    @FXML
    Label label,label1;
    @FXML
    Button inbtn=null,btn_login=null;
    @FXML
    Button btn_search,btn_checkout,btn_restore,btn_backup,btn_logout;
    @FXML
  Button btn_leave,btn_current,btn_checkin,btn_conbook,btn_insert,btn_delete;
    @FXML
      Button btn_back=null;
        @FXML
 TextField tf_search,c_tf,tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9,tf10,tf11,tf12;
        @FXML
        TextField r_tf1,r_tf2,r_tf3,d_tf1,d_tf2,c_tf1,u_name,in_tf1,in_tf4,text;
        @FXML
        PasswordField P_word;
       @FXML
     Button btn_book=null;
    @FXML 
    TableView tableview,tableview2;
    @FXML
    private AnchorPane adminpane,adminpane2;
    @FXML
       private AnchorPane inroom;
    @FXML
   private AnchorPane checkin;
    @FXML
    private SplitPane spadmin;
     @FXML
      AnchorPane login;
      @FXML
     Stage stage=new Stage();
       Stage stage1=new Stage();
      static String checkin_date,checkout_date;
       static String date1,phone_no;
      static String date2,day1,total_days="1";
      static int table=0;
      static String tablename="current_visitors",room_no;
      ObservableList<ObservableList> data;
      TableView tableview1;
   TableView    ta=tableview;
     static  int iii=0,t=0,count=0;
      int ij=0;
     LocalDate date = null;
      
     
      public void pane2load(){
         //This method used to load table current visitor when mouse enter
          System.out.println(iii+"mouse enter"+t);
          if(iii==0 ||iii==t)
          {
              iii++;
           ta=tableview; 
           bld();
        
           
           cbox.getItems().addAll("Name","ID","Phone");
          cbox.setPromptText("Name");
         //cbox.setEditable(true);
          }
         
      }
      //This method used to load table leave visitor when mouse enter
      public void anchorpane(){
          if(ij==0)
          {
             ij=1;
              ta=tableview2;
              bld();
              
          }
      }
      public void mouseclickontableview(){
          
   if(tableview.getSelectionModel().getSelectedIndex()>=0)
    {
          String line=tableview.getSelectionModel().getSelectedItem().toString();
          String []value=line.split(",");
            for(String t : value) {
            System.out.println("> "+t);
             }
              checkinDate=value[15];
                checkinDate=checkinDate.substring(checkinDate.length()-(checkinDate.length()-1)); 
             phone_no=value[10];
                phone_no=phone_no.substring(phone_no.length()-(phone_no.length()-1));
          System.out.println(phone_no);
    }
      }
       Stage instage=new Stage();

        Stage stage2=new Stage();
    public void Buttonisclick(ActionEvent event) throws FileNotFoundException, IOException{
        //this metod used to responed checkout button
        Stage instage=new Stage();
        //this method used to respond search button action
      if(event.getSource()==btn_search)
        {
            FXMLDocumentController2 ob=new FXMLDocumentController2();
            
            System.out.println("Search");
          String str=tf_search.getText();
           System.out.println(cbox.getValue());
           if(cbox.getValue()=="ID")
           {
               cbox.setValue("ID_NO");
           }
            if(cbox.getValue()==null)
           {
               cbox.setValue("Name");
           }
           ob.test(ta,tf_search,cbox);
           
        }
      
      //this method used to respond Insert Room button actionz
     
      
        if(event.getSource()==inbtn)
        {
             count=0;
             Parent root1 = FXMLLoader.load(getClass().getResource("InsertRoom.fxml"));
                instage.setScene(new Scene(root1));
                instage.initModality(Modality.APPLICATION_MODAL);
                instage.initOwner(inbtn.getScene().getWindow());
                instage.setResizable(false);
                instage.show();
                 
             
          /* FXMLLoader lo=new FXMLLoader(getClass().getResource("InsertRoom.fxml"));
           AnchorPane inroom=(AnchorPane)lo.load();
           try{
               adminpane.getChildren().clear();
              adminpane.getChildren().add(inroom);
           }
         catch(Exception e){
             e.printStackTrace();
         }*/
        }
        if(event.getSource()==btn_insert)
        {
             try{
               Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
        st=con.createStatement();
       String sql1 = "Insert into room_list (Room_No,Room_type,Bed_Type,Tariff_Per_Room) value(?,?,?,?)";
            pst=con.prepareStatement(sql1);
            pst.setString(1,in_tf1.getText());
            pst.setString(2,cbox2.getValue().toString());
            pst.setString(3,cbox3.getValue().toString());
            pst.setString(4,in_tf4.getText());
            pst.execute();
             JOptionPane.showMessageDialog(null,"Room has been insert");
             instage=(Stage)btn_insert.getScene().getWindow();
               instage.close();
            
       }
       catch(Exception ex){
           System.out.println(ex);
           
       }
          
        
        }
      
        if(event.getSource()==btn_logout)
        {
            Stage stage=(Stage)btn_logout.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
         JOptionPane.showMessageDialog(null,"Logout successful");
        }
        //this method used to responed login button action
        if(event.getSource()==btn_login)
        {
              try{
           Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
        st=con.createStatement();
        String sql="select * from login where username=? and Password=?";
          pst=con.prepareStatement(sql);
          pst.setString(1,u_name.getText());
          pst.setString(2, P_word.getText());
          rs=pst.executeQuery();
         
          if(rs.next())
          {
              JOptionPane.showMessageDialog(null,"Ur information correct");
               Stage stage=(Stage)btn_login.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Admin Panel");
        stage.setScene(scene);
        stage.show();
        tablename="current_visitors";
               
          }
          else{
              JOptionPane.showMessageDialog(null,"Ur information incorrect");
          }
      }
     catch(Exception ex){
         System.out.println(ex);
     }
           
    
        }
      
        
    }
    //this method used to load database table respectivily
    public void bld(){
        if(tablename=="current_visitors" || tablename=="leave_visitor")
        {
          tf_search.setText(tablename);
        }
      if(tablename=="room_list"){
            datepicker1();
            datepicker2();
        }
            
            Connection c ;
          data = FXCollections.observableArrayList();
          
                ta.getColumns().clear();
          try{
              
           Class.forName("com.mysql.jdbc.Driver");
        c=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
            String SQL = "SELECT * from "+tablename+"";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){      
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                              
                       return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
               ta.getColumns().addAll(col); 
               
            }
            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
               
                data.add(row);

            }

         System.out.print(data);
            ta.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
        
          System.out.println("c_reee enter"+iii);
    }
    
    public void button2action(ActionEvent e) throws IOException{
            /* Stage stage=(Stage)btn_checkin.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("book.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();
    */
        //this method used to responed currnt Visitor button action
        if(e.getSource()==btn_current){
             
            table=0;
             ta=tableview;
            tablename="current_visitors";
              bld();
        }
        if(e.getSource()==btn_leave)
        {
            table=1;
             ta=tableview;
            tablename="leave_visitor";
            bld();
        }
        //this method used to responed Insert button action
        

    }
   public void popupwindos(ActionEvent e) throws IOException{
       Stage stage3=new Stage();
       Stage stage=new Stage();
       Stage stage2=new Stage();
      
       //this method used to responed check available Room button action
       if(e.getSource()==btn_checkin)
       { 
                Parent root = FXMLLoader.load(getClass().getResource("Check_in.fxml"));
                stage3.setScene(new Scene(root));
                stage3.initModality(Modality.APPLICATION_MODAL);
                stage3.initOwner(btn_checkin.getScene().getWindow());
                stage3.setResizable(false);
                stage3.show();
                table=1;
            ta=tableview2;
           tablename="room_list";
          ij=1;
        
       }
      
          
       //this method used to responed Check in button action
         
       if(e.getSource()==btn_book)
       {
          if(room_no!=null)
          {
              stage3=(Stage)btn_book.getScene().getWindow();
               stage3.close();
            Parent root1 = FXMLLoader.load(getClass().getResource("book.fxml"));
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(btn_book.getScene().getWindow());
                stage.setResizable(false);
                stage.showAndWait();
          
               
       
         
          }  
                         
       }
      //this method used to responed bookbutton action
      if(e.getSource()==btn_conbook)
              {
                  
                  String c=tf11.getText();
                  String c2=tf1.getText();
                  String c3=tf5.getText();
                  System.out.println("test phone"+c);
                  if((!"".equals(c) && !"".equals(c3)) && !"".equals(c2))
                  {
                      try{
                          Class.forName("com.mysql.jdbc.Driver");
                          con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
                          st=con.createStatement();
                          String sql="Insert into current_visitors (Name,Age,Relationship,ID_NO,City,Country,Nationality,Address,No_of_visitor,Purpose,Phone,Rent,Room_no,Room_Type,Bed_Type,Check_in,Check_out,day) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                          pst=con.prepareStatement(sql);
                          pst.setString(1,tf1.getText());
                          pst.setString(2,tf2.getText());
                          pst.setString(3,tf3.getText() );
                          pst.setString(4,tf4.getText());
                          pst.setString(5,tf5.getText());
                          pst.setString(6,tf6.getText());
                          pst.setString(7,tf7.getText());
                          pst.setString(8,tf8.getText());
                          pst.setString(9,tf9.getText());
                          pst.setString(10,tf10.getText());
                          pst.setString(11,tf11.getText());
                          pst.setString(12,rent);
                          pst.setString(13,room_no);
                          pst.setString(14,room_type);
                          pst.setString(15,bed_type);
                          pst.setString(16,checkin_date);
                          pst.setString(17,checkout_date);
                          pst.setString(18,total_days);
                          pst.execute();
                          System.out.println("Current visitor table has been update");
                          
                       

                          String delete="delete from room_list where Room_No='"+room_no+"'";
                          pst=con.prepareStatement(delete);
                          pst.execute();
                          iii=0;
                          tablename="current_visitors";
                          stage=(Stage)btn_conbook.getScene().getWindow();
                             stage.close();
                      
                         JOptionPane.showMessageDialog(null,"Book Confirm");
                          // System.out.println(a+"Room has been delete");
                          
                      }
                      catch(Exception ex){
                          System.out.println(ex);
                          JOptionPane.showMessageDialog(null,"This phone number already exist");
                      }
                      
                      
                 
                  }
                  else{
                      JOptionPane.showMessageDialog(null,"Check the tet field");
                  }
                
                      System.out.println(iii);   
              }
        if(e.getSource()==btn_back){
            stage=(Stage)btn_back.getScene().getWindow();
                     stage.close();
                 Parent root = FXMLLoader.load(getClass().getResource("Check_in.fxml"));
                stage2.setScene(new Scene(root));
                stage2.initModality(Modality.APPLICATION_MODAL);
                stage2.initOwner(btn_back.getScene().getWindow());
                stage2.setResizable(false);
                stage2.show();
                     tablename="room_list";
                   ta=tableview2;
                   
              
        }
   }
   
  public void mouseclickOnTable(){
        GregorianCalendar c1=new GregorianCalendar();
      
         
     if((date_selector1.getValue()==null)||(date_selector2.getValue()==null))
     {
         JOptionPane.showMessageDialog(null,"Please selects check in and check out date");
     }
      else{
         checkin_date=date_selector1.getValue().toString();
         checkout_date=date_selector2.getValue().toString();
         long pdate =date_selector1.getValue().toEpochDay();
         long pdate2=date_selector2.getValue().toEpochDay();
         Long  days =ChronoUnit.DAYS.between(date_selector1.getValue(),date_selector2.getValue());
         total_days=String.valueOf(days);
         if("0".equals(total_days))
           {
               total_days="1";
           }
         System.out.println(days);
          //this method used to select a row when mouse is click 
    if(tableview2.getSelectionModel().getSelectedIndex()>=0)
    {
      String line=tableview2.getSelectionModel().getSelectedItem().toString();
      String []value=line.split(",");
       for(String t : value) {
            System.out.println("> "+t);
        }
       
        room_no =value[0];
        room_no=room_no.substring(room_no.length()-(room_no.length()-1));
          System.out.println(room_no);
          sql_operation ob=new sql_operation();
          ob.room_list();
    }
          c_tf1.setText(total_days);
     }
     
    
 }

 public void btndelete(){
if( phone_no!=null)
{
Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
alert.setHeaderText("Are you sure want to delete this information");
//alert.setContentText("Choose your option.");
ButtonType buttonTypeCancel = new ButtonType("NO");
ButtonType buttonTypeyes= new ButtonType("Yes");
alert.getButtonTypes().setAll(buttonTypeyes,buttonTypeCancel);

Optional<ButtonType> result = alert.showAndWait();
if (result.get() ==buttonTypeyes){
    // ... user chose OK
    sql_operation ob=new sql_operation();
        ob.delete();
        bld();
}
 phone_no=null;
}
else{
    JOptionPane.showMessageDialog(null,"Plesae selesct an information");
}
    } 
 public void test(){
          tf12.setText(rent);
          r_tf1.setText(room_no);
          r_tf2.setText(room_type);
          r_tf3.setText(bed_type);
          d_tf1.setText(checkin_date);
          d_tf2.setText(checkout_date);
          System.out.println("ennnnnnnnnn");
 }
 public void check_Out(){
     
     if(tablename=="current_visitors" && phone_no!=null){
        Alert alert = new Alert(AlertType.CONFIRMATION);
                 alert.setTitle("Confirmation");
                alert.setHeaderText("Are you sure");
//alert.setContentText("Choose your option.");
           ButtonType buttonTypeCancel = new ButtonType("NO");
            ButtonType buttonTypeyes= new ButtonType("Yes");
          alert.getButtonTypes().setAll(buttonTypeyes,buttonTypeCancel);

          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() ==buttonTypeyes){
                  sql_operation ob=new sql_operation();
              ob.checkout();
               tablename="current_visitors";
                   ta=tableview;
                   bld();
                   
        
             }
          phone_no=null;
     }
     else{
         JOptionPane.showMessageDialog(null,"Please select an information from current visitor");
     }
 }
 public void testcase() throws IOException
 {
     Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Re-reservation");
alert.setHeaderText("");
alert.setContentText("Are you sure.");
ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
ButtonType buttonTypeyes= new ButtonType("yes");
alert.getButtonTypes().setAll(buttonTypeyes,buttonTypeCancel);

Optional<ButtonType> result = alert.showAndWait();
if (result.get() ==buttonTypeyes){
   Parent root1 = FXMLLoader.load(getClass().getResource("re_reservation.fxml"));
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(btn_current.getScene().getWindow());
                stage.setResizable(false);
                stage.showAndWait();
    
    System.out.println(" continue");
} 

 }
 public void mousedrageontable2() throws IOException{
    
    if("leave_visitor".equals(tablename)){
      
     if(tableview.getSelectionModel().getSelectedIndex()>=0)
    {
          String line=tableview.getSelectionModel().getSelectedItem().toString();
          String []value=line.split(",");
            for(String t : value) {
            System.out.println("> "+t);
             }
                checkinDate=value[15];
                checkinDate=checkinDate.substring(checkinDate.length()-(checkinDate.length()-1));
             phone_no=value[10];
                phone_no=phone_no.substring(phone_no.length()-(phone_no.length()-1));
          System.out.println("checkindate"+checkinDate);
    }
   Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Re-reservation");
alert.setHeaderText("Are you sure");
alert.setContentText("Choose your option.");
ButtonType buttonTypeCancel = new ButtonType("No", ButtonData.CANCEL_CLOSE);
ButtonType buttonTypeyes= new ButtonType("yes");
alert.getButtonTypes().setAll(buttonTypeyes,buttonTypeCancel);

Optional<ButtonType> result = alert.showAndWait();
if (result.get() ==buttonTypeyes){
   
     Stage sss=new Stage();
     Parent root1 = FXMLLoader.load(getClass().getResource("re_reservation.fxml"));
                sss.setScene(new Scene(root1));
                sss.initModality(Modality.APPLICATION_MODAL);
                sss.initOwner(btn_leave.getScene().getWindow());
                sss.showAndWait();
    System.out.println(" continue"); 
 }
 }
 }
public void backupANDrestore(ActionEvent e){
    backupandrestore ob=new backupandrestore();
    if(e.getSource()==btn_backup){
             Alert alert = new Alert(AlertType.CONFIRMATION);
             alert.setTitle("Confirmation");
             alert.setHeaderText("Are you sure about Backup");
//alert.setContentText("Choose your option.");
             ButtonType buttonTypeCancel = new ButtonType("NO");
             ButtonType buttonTypeyes= new ButtonType("Yes");
             alert.getButtonTypes().setAll(buttonTypeyes,buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() ==buttonTypeyes){
                 ob.backup();
                 JOptionPane.showMessageDialog(null,"Backup successful");
                
             }
    }
   if(e.getSource()==btn_restore){
       Alert alert = new Alert(AlertType.CONFIRMATION);
             alert.setTitle("Confirmation");
             alert.setHeaderText("Are you sure about Restore");
//alert.setContentText("Choose your option.");
             ButtonType buttonTypeCancel = new ButtonType("NO");
             ButtonType buttonTypeyes= new ButtonType("Yes");
             alert.getButtonTypes().setAll(buttonTypeyes,buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() ==buttonTypeyes){
                 ob.restore();
                 tablename="current_visitors";
                   ta=tableview;
                   bld();
               JOptionPane.showMessageDialog(null,"Restore successful");
        
             }
       
   }
}
public void fortextField(){
    tf_search.setText("");
}
public void combox(){
    if(count==0)
    {
    cbox2.getItems().addAll("AC","Normal");
    cbox3.getItems().addAll("Single","Double");
    count++;
    }
}
public void datepicker1(){
    date_selector1.setValue(LocalDate.now());
    //Calendar cal = Calendar.getInstance();
    
        // date_selector1.setValue(cal.getTime());
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datepicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                           // super.updateItem(item, empty);
                           
                            if (item.isBefore(
                                   LocalDate.now())
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                    
                            }   
                    }
                };
            }
        };
   date_selector1.setDayCellFactory(dayCellFactory);
   
}
public void datepicker2(){
   
        final Callback<DatePicker, DateCell> dayCellFactory1 = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           
                            if (item.isBefore(
                                    date_selector1.getValue().plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }
                            long p = ChronoUnit.DAYS.between(
                                    date_selector1.getValue(), item
                            );
                            setTooltip(new Tooltip(
                                "You're about to stay for " + p + " days")
                            );
                    }
                };
            }
        };
         date_selector2.setDayCellFactory(dayCellFactory1);
        date_selector2.setValue(date_selector1.getValue().plusDays(1));
}
    @Override
public void initialize(URL url, ResourceBundle rb) {
   
/*tableview2.setOnMouseClicked (new EventHandler<javafx.scene.input.MouseEvent>(){
    public void handle(ActionEvent e){
        System.out.println("click");
    } 

    @Override
    public void handle(javafx.scene.input.MouseEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 });
      */
    
    }
    
    
}
