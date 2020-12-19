/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.main;

import data.UserInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class AdminHomeController implements Initializable {

    @FXML
    private Label lbUname;
    @FXML
    private Button btLogOut;
    @FXML
    private Button btHome;
    @FXML
    private RadioButton rbEGood;
    @FXML
    private RadioButton rbPGood;
    @FXML
    private StackPane mainview;
    @FXML
    private ImageView avatar;
    
    private ScrollPane productPane = null;
    
    private ProductPaneController productController;
    @FXML
    private Label lbEbook;
    @FXML
    private Label lbAlbum;
    @FXML
    private Label lbFilm;
    @FXML
    private Label lbBook;
    @FXML
    private Label lbLP;
    @FXML
    private Label lbCD;
    @FXML
    private Label lbDVD;
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbUname.setText(UserInfo.getUname());
        avatar.setImage(new Image("data/avatar.png"));
        // generate recommend pane
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/mainview/productPane.fxml"));
            productPane = loader.load();
            productController = loader.getController();
            mainview.getChildren().add(productPane);
        } catch (IOException ex) {
        }
    }    
    
    @FXML
    private void toHome(ActionEvent event) {
    }
    
    @FXML
    private void logOut(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login Screen");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.NONE);
            stage.show();
            
            // Hide home screen
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void toEbook(MouseEvent event) {
    }

    @FXML
    private void toAlbum(MouseEvent event) {
    }

    @FXML
    private void toFilm(MouseEvent event) {
    }

    @FXML
    private void toBook(MouseEvent event) {
         productController.getBook();
    }

    @FXML
    private void toLP(MouseEvent event) {
        productController.getLP();
    }

    @FXML
    private void toCD(MouseEvent event) {
        productController.getCD();
    }

    @FXML
    private void toDVD(MouseEvent event) {
        productController.getDVD();
    }
}
