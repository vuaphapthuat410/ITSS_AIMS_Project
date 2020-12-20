/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import controllers.cart.CartController;
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
    
    public static void setControllers(CartController cart, OrderController order, ProductPaneController product) {
        cartController = cart;
        orderController = order;
        productPaneController = product;
    }
    
    public static void refresh() {
        cartController.clear();
        orderController.update();
        productPaneController.getMixed();
    }
}
