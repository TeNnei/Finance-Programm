//package sample;
//
//import java.io.IOException;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import Animation.Shake;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//public class Regist {
//
//
//        @FXML private TextField loginUser;
//        @FXML private PasswordField passwordUser;
//        @FXML private Button LogininButton;
//        @FXML private Button Reg;
//
//        @FXML
//        void initialize() {
//
//            Reg.setOnAction(actionEvent -> {
//                Reg.getScene().getWindow().hide();
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/sample/Regist.fxml"));
//                try {
//                    loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Parent One = loader.getRoot();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(One));
//                stage.setMinWidth(900);
//               stage.setMinHeight(600);
//                stage.setMaxWidth(900);
//                stage.setMaxHeight(600);
//                stage.showAndWait();
//            });
//
//            LogininButton.setOnAction(actionEvent -> {
//                String In = loginUser.getText();
//                String Pas = passwordUser.getText();
//                checkIn(In, Pas);
//            });
//
//
//        }
//        public void checkIn(String userName, String password){
//            DatabaseHandler read = new DatabaseHandler();
//            User3 pr = new User3 ();
//            pr.setLogin(userName);
//            pr.setPassword(password);
//            ResultSet first = read.checkUser(pr);
//            int fg = 0;
//            try {
//            while (first.next()) {
//                fg++;
//            }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//            if(fg >= 1)
//            {
//                LogininButton.getScene().getWindow().hide();
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/sample/MainScene.fxml"));
//                try {
//                    loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Parent One = loader.getRoot();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(One));
//                stage.showAndWait();
//            }
//            else{
//                Shake userLogin = new Shake(loginUser);
//                Shake userPass = new Shake(passwordUser);
//                userLogin.play();
//                userPass.play();
//            }
//        }
//    }
//
