package com.cos.dysson.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "sale")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(
        name = "SALE_SEQ_GENERATOR1"
        , sequenceName = "SALE_SEQ1"
        , initialValue = 1
        , allocationSize = 1
)
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SALE_SEQ_GENERATOR1")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sellerId")
    private Users seller;

    @OneToMany(mappedBy = "sale")
    private List<SaleItem> saleItems = new ArrayList<>();

    private int totalCount;

    public static Sale createSale(Users seller) {
        Sale sale = new Sale();
        sale.setSeller(seller);
        sale.setTotalCount(0);
        return sale;
    }
}
