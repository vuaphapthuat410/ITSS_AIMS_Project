/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.dashboard.promos;

import connectors.PromoDbUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Promo;
import utils.promoUtils;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class AdminPromoManageController implements Initializable {

    @FXML
    private GridPane promosList;
    ArrayList<Promo> promos = new ArrayList<>();
    @FXML
    private Button btNew;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
            promos = PromoDbUtil.getAllPromo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for(Promo promo : promos) {
            addPromoElement(promo);
        }
    }    
    
    public void addPromoElement(Promo promo) {
        Integer index = promosList.getChildren().size();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/dashboard/promos/promoPaneElement.fxml"));
            AnchorPane promoElement = loader.load();
            PromoPaneElementController promoPaneElementController= loader.getController();
            promoPaneElementController.setPromo(promo);
            promosList.addRow(index, promoElement);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void clear() {
        promosList.getChildren().clear();
    }

    @FXML
    private void createPromo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/dashboard/promos/createPromo.fxml"));
        promoUtils.setMode(0);
        Parent node = loader.load();
        CreatePromoController createPromoController = loader.getController();
        
        Stage stage = new Stage();
        stage.setTitle("Add promo");
        stage.setScene(new Scene(node));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
