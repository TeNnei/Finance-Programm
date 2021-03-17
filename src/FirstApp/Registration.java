//package sample;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//
//public class Registration {
//    @FXML
//    private ResourceBundle resources;
//    @FXML
//    private URL location;
//    @FXML
//    private TextField Name;
//    @FXML
//    private TextField SecondName;
//    @FXML
//    private TextField Passport;
//    @FXML
//    private TextField login;
//    @FXML
//    private PasswordField password;
//    @FXML
//    private Button Regist;
//    @FXML
//    private Button Back;
//
//
//    @FXML
//    void initialize() {
//
//        Back.setOnAction(actionEvent -> {
//            Back.getScene().getWindow().hide();
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/Log.fxml"));
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Parent One = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(One));
//            stage.show();
//        });
//
//        Regist.setOnAction(actionEvent -> {
//            registrationOrganization();
//        });
//    }
//
//    private void registrationOrganization() {
//        DatabaseHandler reg = new DatabaseHandler();
//        String name = Name.getText();
//        String secondname = SecondName.getText();
//        int passport = Integer.parseInt(Passport.getText());
//        String loginin = login.getText();
//        String password1 = password.getText();
//        User3 org = new User3(name, secondname, loginin, password1, passport);
//        reg.RegistUser(org);
//    }
//
//}
//
//
//
