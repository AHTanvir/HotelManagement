/*
QERWIUpo * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import static Management.FXMLDocumentController.*;
import static Management.FXMLDocumentController.room_no;

/**
 * FXML Controller class
 *
 * @author skylinkcomputer
 */
public class Re_reservationController implements Initializable {
    @FXML
    Button btn_rebook;
    @FXML
    TableView tableview3;
    @FXML         
     DatePicker date_selector3=null,date_selector4=null;
     PreparedStatement pst=null;
       Connection con=null;
          Statement st=null;
          ResultSet rs=null;
       static String roomNo=null,bedType=null,roomType=null,rent=null,checkinDate=null,checkOutdate=null,day=null,old_rent=null;
    ObservableList<ObservableList> data;
    public void btnRebook(ActionEvent e) throws IOException{
       if((date_selector3.getValue()==null)||(date_selector4.getValue()==null))
        {
            JOptionPane.showMessageDialog(null,"Please selects check in and check out date");
        }
       else{
            checkin_date=date_selector3.getValue().toString();
           checkout_date=date_selector4.getValue().toString();
           long pdate =date_selector3.getValue().toEpochDay();
           long pdate2=date_selector4.getValue().toEpochDay();
           int days=1;
                   days =(int)Math.abs(pdate - pdate2);
           total_days=String.valueOf(days);
           if("0".equals(total_days))
           {
               total_days="1";
           }
           System.out.println(days);
        if(e.getSource()==btn_rebook)
        {
            sql_operation ob=new sql_operation();
            ob.updatecell();
             phone_no=null;
             room_no=null;
             
            Stage stage=new Stage();
          stage=(Stage)btn_rebook.getScene().getWindow();
                      stage.close();
                  JOptionPane.showMessageDialog(null,"Book confirm");
                        iii=0;
                        // phone_no=null;
                    //room_no=null;
            System.out.println("Actionevrnuy"+phone_no);
        }
        
        }
        
           
           
        
     }
    public void tableclick(){
             if(tableview3.getSelectionModel().getSelectedIndex()>=0)
    {
          String line=tableview3.getSelectionModel().getSelectedItem().toString();
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
             
    }
    public void datepicker3(){
    date_selector3.setValue(LocalDate.now());
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
   date_selector3.setDayCellFactory(dayCellFactory);
   
}
public void datepicker4(){
   
        final Callback<DatePicker, DateCell> dayCellFactory1 = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           
                            if (item.isBefore(
                                    date_selector3.getValue().plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }
                            long p = ChronoUnit.DAYS.between(
                                    date_selector3.getValue(), item
                            );
                            setTooltip(new Tooltip(
                                "You're about to stay for " + p + " days")
                            );
                    }
                };
            }
        };
         date_selector4.setDayCellFactory(dayCellFactory1);
        date_selector4.setValue(date_selector3.getValue().plusDays(1));
}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Connection c ;
          data = FXCollections.observableArrayList();
          try{
           Class.forName("com.mysql.jdbc.Driver");
        c=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
            String SQL = "SELECT * from room_list";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){      
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                              
                       return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

               tableview3.getColumns().addAll(col); 
               
            }
            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
               
                data.add(row);

            }

         
            tableview3.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
        
 try{
                Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
         String sql1="select * from leave_visitor where phone='"+phone_no+"' and Check_in='"+checkinDate+"'";
         pst=con.prepareStatement(sql1);
         rs=pst.executeQuery();
         if(rs.next())
         {
           
             roomNo=rs.getString("Room_no"); 
             roomType =rs.getString("Room_Type");
               bedType=rs.getString("Bed_Type");
                  day=rs.getString("day");
                  old_rent=rs.getString("Rent");
                  checkinDate=rs.getString("Check_in");
                  checkOutdate=rs.getString("Check_out");
          
         }
         System.out.println("Its work............................................................................");
 }
catch(Exception e){
   System.out.println(e);
}
 

    }    
}
