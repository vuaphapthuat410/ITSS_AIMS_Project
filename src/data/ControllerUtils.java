/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import controllers.cart.CartController;
import controllers.main.AdminProductsManageController;
import controllers.main.ProductPaneController;
import controllers.order.OrderController;

/**
 *
 * @author vuaphapthuat410
 */
public class ControllerUtils {
    private static CartController cartController;
    private static OrderController orderController;
    private static ProductPaneController productPaneController;
    
    private static AdminProductsManageController adminProductsManageController;
    // For User use
    public static void setControllers(CartController cart, OrderController order, ProductPaneController product) {
        if(UserInfo.isAdmin()) return;
        
        cartController = cart;
        orderController = order;
        productPaneController = product;
    }
    
    public static void refresh() {
        if(UserInfo.isAdmin()) return;
        
        cartController.clear();
        orderController.update();
        productPaneController.getMixed();
    }
    // For Admin use
    public static void setControllers(AdminProductsManageController product) {
        if(UserInfo.isAdmin()) adminProductsManageController = product;
    }
    
    public static void refreshProductOnlyForAdmin() {
        if(UserInfo.isAdmin()) adminProductsManageController.getMixed();
    }
}
