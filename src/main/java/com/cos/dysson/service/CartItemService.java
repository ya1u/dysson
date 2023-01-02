package com.cos.dysson.service;

import com.cos.dysson.model.CartItem;
import com.cos.dysson.repository.CartItemRepositoory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepositoory cartItemRepositoory;

    @Transactional
    public void plusCartItem(int id) {
        CartItem cartItem = cartItemRepositoory.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("제품 찾기 실패");
        });
        int count = cartItem.getCount();
        cartItem.setCount(count+1);
    }
    @Transactional
    public void minusCartItem(int id) {
        CartItem cartItem = cartItemRepositoory.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("제품 찾기 실패");
        });
        int count = cartItem.getCount();
        int cnt = count-1;

        if(cnt >= 1) {
            cartItem.setCount(cnt);
        } else {
            cartItem.setCount(1);
        }
    }

}
