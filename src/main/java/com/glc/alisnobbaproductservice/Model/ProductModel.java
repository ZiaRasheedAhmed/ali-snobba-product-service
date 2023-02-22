package com.glc.alisnobbaproductservice.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="product-data")
@Getter
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String productName;
    @Setter
    @Column(columnDefinition = "text", nullable = false)
    private String shortDescription;
    @Setter
    @Column(columnDefinition = "text", nullable = false, length = 500)
    private String longDescription;
    @Setter
    private String productImage;
    @Setter
    private Long productPrice;

    public ProductModel(){}
    
    public ProductModel(String productName, String shortDescription, String longDescription,
            String productImage, Long productPrice2) {
        this.productName = productName;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.productImage = productImage;
        this.productPrice = productPrice2;
    }
}
