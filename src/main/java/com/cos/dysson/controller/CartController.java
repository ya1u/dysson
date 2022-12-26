package com.cos.dysson.controller;

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

import javax.servlet.http.HttpSession;

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
	public String cartPage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail, HttpSession httpSession) {
		Users users = userService.findUser(id);
		Cart userCart = users.getCart();
		if(userCart!=null) {
			if (principalDetail.getUser().getId() == id) {
//				Users users = userService.findUser(id);
//				Cart userCart = users.getCart();

				List<CartItem> cartItemList = cartService.allUserCartView(userCart);

				int totalPrice = 0;
				for (CartItem cartitem : cartItemList) {
					totalPrice += cartitem.getCount() * cartitem.getProduct().getPrice();
				}

				model.addAttribute("totalPrice", totalPrice);
				model.addAttribute("totalCount", userCart.getCount());
				model.addAttribute("cartItems", cartItemList);
				httpSession.setAttribute("user", userService.findUser(id));

				return "/product/cart";

			} else {
				return "redirect:/";
			}
		} else {
			return "product/cart";
		}
	}

	// 장바구니에서 물건 삭제
	// 삭제하고 남은 상품의 총 개수
	@GetMapping("/user/cart/{userId}/{cartItemId}/delete")
	public String deleteCartItem(@PathVariable("userId") Integer id, @PathVariable("cartItemId") Integer itemId, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		if (principalDetail.getUser().getId() == id) {
			CartItem cartItem = cartService.findCartItemById(itemId);

			cartService.cartItemDelete(itemId);

			Cart userCart = cartService.findUserCart(id);

			List<CartItem> cartItemList = cartService.allUserCartView(userCart);

			int totalPrice = 0;
			for (CartItem cartitem : cartItemList) {
				totalPrice += cartitem.getCount() * cartitem.getProduct().getPrice();
			}

			int cnt = userCart.getCount()-cartItem.getCount();
			userCart.setCount(cnt);

			cartService.minusCount(id, cnt);

			model.addAttribute("totalPrice", totalPrice);
			model.addAttribute("totalCount", userCart.getCount());
			model.addAttribute("cartItems", cartItemList);
			model.addAttribute("user", userService.findUser(id));

			return "redirect:/cart/"+id;
		}
		// 로그인 id와 장바구니 삭제하려는 유저의 id가 같지 않는 경우
		else {
			return "redirect:/main";
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
