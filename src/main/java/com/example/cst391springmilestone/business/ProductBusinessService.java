package com.example.cst391springmilestone.business;

import com.example.cst391springmilestone.data.ProductDataService;
import com.example.cst391springmilestone.models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBusinessService {

    @Autowired
    private ProductDataService service;

    public List<ProductModel> findAllProducts(){
        return service.findAllProducts();
    }

    public ProductModel findProductById(int productId){
        return service.findProductById(productId);
    }

    public boolean createProduct(ProductModel productModel){
        return service.createProduct(productModel);
    }

    public boolean updateProduct(ProductModel productModel, int productId){
        return service.updateProduct(productModel, productId);
    }

    public boolean deleteProduct(int productId){
        return service.deleteProduct(productId);
    }
}
