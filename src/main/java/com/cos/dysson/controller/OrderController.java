package com.cos.dysson.controller;

import com.cos.dysson.config.auth.PrincipalDetail;
import com.cos.dysson.model.*;
import com.cos.dysson.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private ProductService productService;

    @GetMapping("/order/complete/{id}")
    public String orderCompletePage(@PathVariable("id") Integer id,@AuthenticationPrincipal PrincipalDetail principal, HttpSession httpSession) {
        httpSession.setAttribute("user", userService.findUser(principal.getUser().getId()));
        return "/order/complete";
    }

    @GetMapping("/order/cart/{id}")
    public String orderCart(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail, HttpSession httpSession) {
        Users users = userService.findUser(id);
        Cart userCart = users.getCart();
        if (userCart != null) {
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

                return "/product/orderForm";

            }
        }
        return "/";
    }

    @GetMapping("/order/product/{id}/{productId}/{amount}")
    public String orderProduct(@PathVariable Integer id, @PathVariable Integer productId, @PathVariable Integer amount, Model model, PrincipalDetail principalDetail) {
//        if (principalDetail.getUser().getId() == id) {
            Product product = productService.??????????????????(productId);

            model.addAttribute("totalPrice", (product.getPrice() * amount));
            model.addAttribute("Count", amount);
            model.addAttribute("product", product);

            return "/product/orderForm";
//        }
//        return "/";
    }

    // ???????????? ?????? ?????? ??????
    @Transactional
    @PostMapping("/cart/checkout/{id}")
    public String cartCheckout(@PathVariable("id") Integer id, @AuthenticationPrincipal PrincipalDetail principalDetails, Model model) {
        // ???????????? ???????????? ????????? id??? ???????????? id??? ????????? ???
        if (principalDetails.getUser().getId() == id) {
            Users user = userService.findUser(id);

            // ?????? ?????? ??????
            Cart userCart = cartService.findUserCart(user.getId());

            // ?????? ?????? ?????? ?????? ?????????
            List<CartItem> userCartItems = cartService.allUserCartView(userCart);

            // ?????? ?????? ??????
            int totalPrice = 0;
            for (CartItem cartItem : userCartItems) {
                totalPrice += cartItem.getCount() * cartItem.getProduct().getPrice();
            }

            List<OrderItem> orderItemList = new ArrayList<>();

            for (CartItem cartItem : userCartItems) {

                Users seller = cartItem.getProduct().getSeller();

                // order, orderItem ??? ??????
                OrderItem orderItem = orderService.addCartOrder(cartItem.getProduct().getId(), user.getId(), cartItem);

                orderItemList.add(orderItem);
            }

            orderService.addOrder(user, orderItemList);

            // ???????????? ?????? ?????? ??????
            cartService.allCartItemDelete(id);

            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("cartItems", userCartItems);
            model.addAttribute("user", userService.findUser(id));

            return "/order/complete";
        } else {
            return "redirect:/";
        }
    }

    // ?????? ?????? ??????
    @Transactional
    @PostMapping("/product/checkout/{id}/{productId}/{count}")
    public String productCheckout(@PathVariable("id") Integer id, @PathVariable("productId") Integer productId, @PathVariable("count") Integer count, @AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        // ???????????? ???????????? ????????? id??? ???????????? id??? ????????? ???
        if (principalDetail.getUser().getId() == id) {
            Users user = userService.findUser(id);
            Product product = productService.productView(productId);

            // ?????? ????????????
            int totalPrice = product.getPrice() * count;

            orderService.addOneItemOrder(user.getId(), product, count);

            return "/order/complete";
        } else {
            return "redirect:/";
        }
    }

    // ?????? ???????????? ??????
    @Transactional
    @GetMapping("/order/cancel/{id}/{orderId}")
    public String orderCancel(@PathVariable("id") Integer id ,@PathVariable("orderId") Integer orderId, HttpServletRequest request) {
        orderService.orderCancel(orderId);

        return "redirect:" + request.getHeader("Referer");
    }

    // ADMIN ???????????? ??????
    @GetMapping ("/admin/order/isDelivery/{orderId}")
    public String plus(@PathVariable int orderId) {
        orderService.isDeliverySet(orderId);

        return "redirect:/admin";
    }
}
