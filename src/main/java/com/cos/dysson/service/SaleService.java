package com.cos.dysson.service;

import com.cos.dysson.model.CartItem;
import com.cos.dysson.model.Sale;
import com.cos.dysson.model.SaleItem;
import com.cos.dysson.model.Users;
import com.cos.dysson.repository.SaleItemRepository;
import com.cos.dysson.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

    @Autowired
    private UserService userService;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Transactional
    public SaleItem addSale (int itemId, int sellerId, CartItem cartItem) {
        Users seller = userService.findUser(sellerId);
        Sale sale = saleRepository.findBySellerId(sellerId);
        sale.setTotalCount(sale.getTotalCount() + cartItem.getCount());
        saleRepository.save(sale);
        SaleItem saleItem = SaleItem.createSaleItem(itemId, sale, seller, cartItem);
        saleItemRepository.save(saleItem);

        return saleItem;
    }

}
