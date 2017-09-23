/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;


import java.sql.*;
import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import static Management.sql_operation.r_no;

/**
 *
 * @author skylinkcomputer
 */
public class Login extends Application {
    static String u_name=null,p_word;
  Connection con=null;
     Statement st=null;
      ResultSet rs=null;
    PreparedStatement pst=null;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
       stage.setResizable(false);
       
   
       try{
           Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
        st=con.createStatement();
         String sql="select * from login";
          pst=con.prepareStatement(sql);
          rs=pst.executeQuery();
         if(rs.next())
         {
             r_no=rs.getString("username");
         
           
         }
       }
       catch(Exception e){
           System.out.println(e);
            Dialog<Pair<String, String>> dialog = new Dialog<>();
dialog.setTitle("Sing Up");
dialog.setHeaderText("There are no database exist.");
ButtonType loginButtonType = new ButtonType("Create", ButtonData.OK_DONE);
dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

GridPane grid = new GridPane();
grid.setHgap(10);
grid.setVgap(10);
grid.setPadding(new Insets(20, 150, 10, 10));
TextField username = new TextField();
username.setPromptText("Username");
PasswordField password = new PasswordField();
password.setPromptText("Password");
grid.add(new Label("Username:"), 0, 0);
grid.add(username, 1, 0);
grid.add(new Label("Password:"), 0, 1);
grid.add(password, 1, 1);
Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
loginButton.setDisable(true);
username.textProperty().addListener((observable, oldValue, newValue) -> {
    loginButton.setDisable(newValue.trim().isEmpty());
});
dialog.getDialogPane().setContent(grid);

dialog.setResultConverter(dialogButton -> {
    if (dialogButton==loginButtonType) {
        System.out.println("sdfghjjhgfdxdfghj");
         return new Pair<>(username.getText(), password.getText());
    }
    return null;
});
Optional<Pair<String, String>> result = dialog.showAndWait();

result.ifPresent(usernamePassword -> {
    u_name=usernamePassword.getValue().toString();
    p_word=usernamePassword.getKey();
    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
    Sqldb ob=new Sqldb();
    ob.sqldb();
       try{
           Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
        st=con.createStatement();
       String sql1 = "Insert into login (name,username,password) value(?,?,?)";
            pst=con.prepareStatement(sql1);
            pst.setString(1,"user");
            pst.setString(2,usernamePassword.getKey());
            pst.setString(3,usernamePassword.getValue());
            pst.execute();
       } 
    catch(Exception ee){
       System.out.println(ee);
     }
});
        
       }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}