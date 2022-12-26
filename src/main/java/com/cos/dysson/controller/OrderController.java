package com.cos.dysson.controller;

import com.cos.dysson.config.auth.PrincipalDetail;
import com.cos.dysson.model.*;
import com.cos.dysson.service.CartService;
import com.cos.dysson.service.OrderService;
import com.cos.dysson.service.SaleService;
import com.cos.dysson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SaleService saleService;

    // 장바구니 상품 전체 주문
    @Transactional
    @PostMapping("cart/checkout/{id}")
    public String cartCheckout(@PathVariable("id") Integer id, @AuthenticationPrincipal PrincipalDetail principalDetails, Model model) {
        // 로그인이 되어있는 유저의 id와 주문하는 id가 같아야 함
        List<CartItem> userCartItems = null;
        int totalPrice = 0;
        if (principalDetails.getUser().getId() == id) {
            Users user = userService.findUser(id);

            // 유저 카트 찾기
            Cart userCart = cartService.findUserCart(user.getId());

            // 유저 카트 안에 있는 상품들
            userCartItems = cartService.allUserCartView(userCart);

            // 최종 결제 금액
            totalPrice = 0;
            for (CartItem cartItem : userCartItems) {
                totalPrice += cartItem.getCount() * cartItem.getProduct().getPrice();
            }

            List<OrderItem> orderItemList = new ArrayList<>();

            for (CartItem cartItem : userCartItems) {

                Users seller = cartItem.getProduct().getSeller();

                // sale, saleItem 에 담기
                SaleItem saleItem = saleService.addSale(cartItem.getProduct().getId(), seller.getId(), cartItem);

                // order, orderItem 에 담기
                OrderItem orderItem = orderService.addCartOrder(cartItem.getProduct().getId(), user.getId(), cartItem, saleItem);

                orderItemList.add(orderItem);
            }

            orderService.addOrder(user, orderItemList);

            // 장바구니 상품 모두 삭제
            cartService.allCartItemDelete(id);
        }

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cartItems", userCartItems);
        model.addAttribute("user", userService.findUser(id));

        return "redirect:/cart/" + id;
    }

}
