package com.cos.dysson.controller;

import com.cos.dysson.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping ("api/cartItem/plus/{id}/{userId}")
    public String plus(@PathVariable int id, @PathVariable int userId) {
        cartItemService.plusCartItem(id);
        return "redirect:/cart/"+userId;
    }

    @GetMapping("api/cartItem/minus/{id}/{userId}")
    public String minus(@PathVariable int id, @PathVariable int userId) {
        cartItemService.minusCartItem(id);
        return "redirect:/cart/"+userId;
    }
}
