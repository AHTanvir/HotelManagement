/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.net.URL;
import java.sql.*;
import java.util.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.Callback;
import static Management.FXMLDocumentController.tablename;



/**
 *
 * @author skylinkcomputer
 */
public class FXMLDocumentController2 implements Initializable {
      PreparedStatement pst=null;
       Connection con=null;
          Statement st=null;
          ResultSet rs=null;
 
      static String checkin_date,checkout_date;
       static String date1;
      static String date2,day1,total_days;
      static int table=0;
     
      ObservableList<ObservableList> data;
     
      int ii=11;
      int ij=0;
     
      public void test(TableView ta, TextField tf_search, ComboBox cbox){
        System.out.println(cbox.getValue());
          Connection c ;
          data = FXCollections.observableArrayList();
          
          try{
             
             
           Class.forName("com.mysql.jdbc.Driver");
        c=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
       
            String SQL = "SELECT * from "+tablename+" where "+cbox.getValue()+"='"+tf_search.getText()+"'";
            //ResultSet
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
                System.out.println("Column ["+i+"] ");
            }

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }
            ta.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
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
