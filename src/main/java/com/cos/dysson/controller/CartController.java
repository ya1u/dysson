package com.cos.dysson.controller;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.dysson.config.auth.PrincipalDetail;
import com.cos.dysson.model.Cart;
import com.cos.dysson.model.CartItem;
import com.cos.dysson.model.Product;
import com.cos.dysson.model.Users;
import com.cos.dysson.service.CartService;
import com.cos.dysson.service.ProductService;
import com.cos.dysson.service.UserService;

@Controller
public class CartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	// 장바구니
	@GetMapping("/cart/{id}")
	public String cartPage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		if (principalDetail.getUser().getId() == id) {
			Users users = userService.findUser(id);
			Cart userCart = users.getCart();
			System.out.println(users);
			System.out.println(userCart);

			List<CartItem> cartItemList = cartService.allUserCartView(userCart);

			int totalPrice = 0;
			for (CartItem cartitem : cartItemList) {
				totalPrice += cartitem.getCount() * cartitem.getProduct().getPrice();
		}

		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("totalCount", userCart.getCount());
		model.addAttribute("user", userService.findUser(id));
		model.addAttribute("user", userService.findUser(id));

		return "/product/cart";
		}

		else {
			return "redirect:/";
		}

	}

	@PostMapping("/users/cart/{id}/{productId}")
	public String addCartItem(@PathVariable("id") Integer id, @PathVariable("productId") Integer productId, int amount) {

		Users users = userService.findUser(id);
		Product product = productService.productView(productId);
		
		cartService.addCart(users, product, amount);
		
		return "redirect:/product/{productId}";
	}
	
}
