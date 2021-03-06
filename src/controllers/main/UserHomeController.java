/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.main;

import controllers.cart.CartController;
import controllers.order.OrderController;
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
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import data.UserInfo;
import java.sql.SQLException;
import data.ControllerUtils;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class UserHomeController implements Initializable {

    @FXML
    private Label lbOrder;
    @FXML
    private Label lbCart;
    @FXML
    private Label lbUname;
    @FXML
    private Button btLogOut;
    @FXML
    private RadioButton rbEGood;
    @FXML
    private RadioButton rbPGood;
    @FXML
    private ImageView avatar;
    @FXML
    private StackPane mainview;   
    @FXML
    private Button btHome;
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
    private TextField tfSearch;
    
    private ScrollPane orderPane = null;
    private ScrollPane cartPane = null;
    private ScrollPane productPane = null;   
    private ScrollPane profilePane = null;
    
    private ProductPaneController productController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbUname.setText(UserInfo.getUname());
        avatar.setImage(new Image("data/avatar.png"));
        // generate recommend pane
        try {
            FXMLLoader orderLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/order/order.fxml"));
            orderPane = orderLoader.load();
            OrderController orderController = orderLoader.getController();
            orderController.addAllOrders();
            mainview.getChildren().add(orderPane);
            
            FXMLLoader cartLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/cart/cart.fxml"));
            cartPane = cartLoader.load();
            CartController cartController = cartLoader.getController();
            mainview.getChildren().add(cartPane);
            
            FXMLLoader productLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/mainview/productPane.fxml"));
            productPane = productLoader.load();
            productController = productLoader.getController();
            productController.setCartController(cartController);
            productController.getMixed();   // get mixed items after having cartController or got NullPointerException because of cartController varriables in ProductPaneController
            mainview.getChildren().add(productPane);
            
            ControllerUtils.setControllers(cartController, orderController, productController); // save for convenient
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
    }    

    @FXML
    private void toOrder(MouseEvent event) throws IOException {
            orderPane.toFront();
    }

    @FXML
    private void toCart(MouseEvent event) throws IOException {
            cartPane.toFront();
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
            
            // close home screen
            Stage currrentStage = (Stage) btLogOut.getScene().getWindow();
            currrentStage.close();
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
    private void toProfile(MouseEvent event) throws IOException {
        try {
            Parent profile = FXMLLoader.load(getClass().getClassLoader().getResource("views/profile/profile.fxml"));
            //  
            Stage stage = new Stage();
            stage.setTitle("Profile screen");
            stage.setScene(new Scene(profile));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            System.out.println("Open profile screen sucessful");
        } catch (IOException e) {
            System.err.println("Error occurs while trying to open profile screen.");
        }
    }

    @FXML
    private void toSearch(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            productController.getSearchList(tfSearch.getText());
        }
    }
    
}
