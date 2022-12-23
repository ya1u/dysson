package com.cos.dysson.controller;

import com.cos.dysson.model.Cart;
import com.cos.dysson.model.Users;
import com.cos.dysson.service.CartItemService;
import com.cos.dysson.service.CartService;
import com.cos.dysson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @GetMapping ("api/cartItem/plus/{id}/{userId}")
    public String plus(@PathVariable int id, @PathVariable int userId) {
        cartItemService.plusCartItem(id);
        cartService.plusCartCount(userId);

        return "redirect:/cart/"+userId;
    }

    @GetMapping("api/cartItem/minus/{id}/{userId}")
    public String minus(@PathVariable int id, @PathVariable int userId) {
        cartItemService.minusCartItem(id);
        cartService.minusCartCount(userId);

        return "redirect:/cart/"+userId;
    }
}
