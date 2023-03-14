package net.devstudy.model;

import java.util.Collection;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.devstudy.Constants;
import net.devstudy.util.WebUtils;

public class ShoppingCart {
    public static ShoppingCart getCuShoppingCart(HttpServletRequest req) {
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART);

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            setCurrentCart(req, shoppingCart);
        }
        return shoppingCart;
    }

    public static boolean isCurrentShoppingCartCreated(HttpServletRequest req) {
        return req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART) != null;
    }

    private static void setCurrentCart(HttpServletRequest req, ShoppingCart shoppingCart) {
        req.getSession().setAttribute(Constants.CURRENT_SHOPPING_CART, shoppingCart);
    }

    public static void clearCurrentShoppingCart(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute(Constants.CURRENT_SHOPPING_CART);
        WebUtils.setCookie((String) Constants.Cookie.SHOPPING_CART.getName(), null, 0, resp);
    }

    public static Cookie findShoppingCart(HttpServletRequest req) {
        return WebUtils.findCookie(req, (String) Constants.Cookie.SHOPPING_CART.getName());
    }

    public static void updateCurrentShoppingCart(String cookieValue, HttpServletResponse resp) {
        WebUtils.setCookie((String) Constants.Cookie.SHOPPING_CART.getName(), cookieValue,
                (int) Constants.Cookie.SHOPPING_CART.getTtl(), resp);
    }

}
