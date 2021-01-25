/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import models.Promo;

/**
 *
 * @author vuaphapthuat410
 */
public class promoUtils {
    private static Integer mode = 0;
    private static Promo promo;

    public static Integer getMode() {
        return mode;
    }

    public static void setMode(Integer mode) {
        promoUtils.mode = mode;
    }

    public static Promo getPromo() {
        return promo;
    }

    public static void setPromo(Promo promo) {
        promoUtils.promo = promo;
    }
    
}
