package com.cos.dysson.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "saleItem")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(
        name = "SALEITEM_SEQ_GENERATOR1"
        , sequenceName = "SALEITEM_SEQ1"
        , initialValue = 1
        , allocationSize = 1
)
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SALEITEM_SEQ_GENERATOR1")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "saleId")
    private Sale sale;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sellerId")
    private Users seller;

    private int itemId;
    private String itemName;
    private int itemPrice;
    private int itemCount;
    private int itemTotalPrice;

    @OneToOne(mappedBy = "saleItem")
    private OrderItem orderItem;

    private int isCancel;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDate.now();
    }

    public static SaleItem createSaleItem(int itemId, Sale sale, Users seller, CartItem cartItem) {
        SaleItem saleItem = new SaleItem();
        saleItem.setItemId(itemId);
        saleItem.setSale(sale);
        saleItem.setSeller(seller);
        saleItem.setItemName(cartItem.getProduct().getName());
        saleItem.setItemPrice(cartItem.getProduct().getPrice());
        saleItem.setItemCount(cartItem.getCount());
        saleItem.setItemTotalPrice(cartItem.getProduct().getPrice()*cartItem.getCount());
        return saleItem;
    }

    public static SaleItem createSaleItem(int itemId, Sale sale, Users seller, Product product, int count) {
        SaleItem saleItem = new SaleItem();
        saleItem.setItemId(itemId);
        saleItem.setSale(sale);
        saleItem.setSeller(seller);
        saleItem.setItemName(product.getName());
        saleItem.setItemPrice(product.getPrice());
        saleItem.setItemCount(count);
        saleItem.setItemTotalPrice(product.getPrice()*count);
        return saleItem;
    }
}
