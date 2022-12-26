package com.cos.dysson.repository;

import com.cos.dysson.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    Sale findBySellerId(int sellerId);
}
