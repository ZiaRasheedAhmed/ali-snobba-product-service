package com.glc.alisnobbaproductservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glc.alisnobbaproductservice.Model.ProductModel;

public interface IProductRepository extends JpaRepository<ProductModel, Long> {
}
