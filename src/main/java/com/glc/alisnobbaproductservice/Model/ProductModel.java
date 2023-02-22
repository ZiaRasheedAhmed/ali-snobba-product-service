package com.glc.alisnobbaproductservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Entity
@Table(name="product-data")
@Getter
@Setter
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
            String productImage, Long productPrice) {
        this.productName = productName;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.productImage = productImage;
        this.productPrice = productPrice;
    }

    
    public ProductModel(Long id) {
        this.id = id;
    }
}