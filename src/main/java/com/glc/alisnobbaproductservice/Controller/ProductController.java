package com.glc.alisnobbaproductservice.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glc.alisnobbaproductservice.Model.ProductModel;
import com.glc.alisnobbaproductservice.Repository.IProductRepository;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private IProductRepository productRepository;

    @GetMapping("/all")
    public List<ProductModel> getAllProducts(){
        return productRepository.findAll();
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody ProductModel productModel){
        productRepository.save(productModel);
        return "Product Saved "+productModel.getProductName();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> findById(@PathVariable Long id){
        Optional<ProductModel> p = productRepository.findById(id);
        if(p.isPresent()){
            productRepository.findById(id);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
