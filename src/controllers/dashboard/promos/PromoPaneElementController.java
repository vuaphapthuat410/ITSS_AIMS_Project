/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.dashboard.promos;

import connectors.PromoDbUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Promo;
import models.PromoItem;
import utils.promoUtils;

/**
 * FXML Controller class
 *
 * @author vuaphapthuat410
 */
public class PromoPaneElementController implements Initializable {

    @FXML
    private Label price;
    @FXML
    private Button btRemove;
    @FXML
    private Label lbRate;
    @FXML
    private Button btEdit;
    @FXML
    private Label lbStart;
    @FXML
    private Label lbEnd;
    @FXML
    private Label lbName;
    @FXML
    private Label lbDescription;
    
    private Promo promo;
    private AdminPromoManageController parentController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void remove(ActionEvent event) { // have not check for remove promo in database
        GridPane grid = (GridPane) btRemove.getParent().getParent();
        grid.getChildren().remove(btRemove.getParent());
        
        try {
            PromoDbUtil.deletePromo(promo.getId());
        } catch (SQLException | ClassNotFoundException ex) {
           ex.printStackTrace();
        }
    }

    @FXML
    private void edit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/dashboard/promos/createPromo.fxml"));
        promoUtils.setMode(1);
        promoUtils.setPromo(promo);
        
        Parent root = loader.load();
        
        Stage stage = new Stage();
        stage.setTitle("Edit Promo");
        stage.setScene(new Scene(root));
        stage.show();
    }

    
    public void setPromo(Promo promo) {
        this.promo = promo;
        
        lbName.setText(promo.getName());
        lbStart.setText(promo.getStart_time());
        lbEnd.setText(promo.getEnd_time());
        lbDescription.setText(promo.getDescription());
    }
}
