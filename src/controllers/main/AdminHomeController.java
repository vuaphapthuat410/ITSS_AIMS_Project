/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.main;

import data.ControllerUtils;
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
    @FXML
    private Label productsManage;
    @FXML
    private Label accsManage;
    @FXML
    private Label promosManage;
    @FXML
    private Label ordersManage;
    
        
    private ScrollPane productPane = null;
    private AdminProductsManageController productController;
    private ScrollPane accsManagePane = null;
    private ScrollPane productsManagePane = null;
    private ScrollPane promosManagePane = null;
    
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbUname.setText(UserInfo.getUname());
        avatar.setImage(new Image("data/avatar.png"));
        // generate recommend pane
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/mainview/adminProductsManage.fxml"));
            productPane = loader.load();
            productController = loader.getController();
            productController.getMixed();
            mainview.getChildren().add(productPane);
            
            ControllerUtils.setControllers(productController); //save for convenient
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        productController.getMixed();
    }    
    
    @FXML
    private void toHome(ActionEvent event) {
        productPane.toFront();
        productController.getMixed();
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
        productPane.toFront();
        productController.getEbook();
    }

    @FXML
    private void toAlbum(MouseEvent event) {
        productPane.toFront();
        productController.getAlbum();
    }

    @FXML
    private void toFilm(MouseEvent event) {
        productPane.toFront();
        productController.getMovie();
    }

    @FXML
    private void toBook(MouseEvent event) {
        productPane.toFront();
         productController.getBook();
    }

    @FXML
    private void toLP(MouseEvent event) {
        productPane.toFront();
        productController.getLP();
    }

    @FXML
    private void toCD(MouseEvent event) {
        productPane.toFront();
        productController.getCD();
    }

    @FXML
    private void toDVD(MouseEvent event) {
        productPane.toFront();
        productController.getDVD();
    }

    @FXML
    private void toProductsManage(MouseEvent event) throws IOException {
        if(productsManagePane != null)
            productsManagePane.toFront();
        else {
            productsManagePane = FXMLLoader.load(getClass().getClassLoader().getResource("views/dashboard/productsManage.fxml"));
            mainview.getChildren().add(productsManagePane);
        }
    }

    @FXML
    private void toAccsManage(MouseEvent event) throws IOException {
        if(accsManagePane != null)
            accsManagePane.toFront();
        else {
            accsManagePane = FXMLLoader.load(getClass().getClassLoader().getResource("views/dashboard/accsManage.fxml"));
            mainview.getChildren().add(accsManagePane);
        }
    }

    @FXML
    private void toPromosManage(MouseEvent event) throws IOException {
        if(promosManagePane != null)
            promosManagePane.toFront();
        else {
            promosManagePane = FXMLLoader.load(getClass().getClassLoader().getResource("views/dashboard/promosManage.fxml"));
            mainview.getChildren().add(promosManagePane);
        }
    }

    @FXML
    private void toOrdersManage(MouseEvent event) {
    }
}
