package com.example.cst391springmilestone.controllers;

import com.example.cst391springmilestone.business.ProductBusinessService;
import com.example.cst391springmilestone.models.ProductModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRestService {

    @Autowired
    public ProductBusinessService productBusinessService;

    @GetMapping("/")
    @ApiOperation(value = "Retrieve a list of all products")
    public ResponseEntity<?> findAllProducts(){
        try {
            List<ProductModel> products = productBusinessService.findAllProducts();
            if(products == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieves a product based on product ID")
    public ResponseEntity<?> findProductById(@PathVariable("id") Integer productId){
        try {
            ProductModel product = productBusinessService.findProductById(productId);
            if (product == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(product, HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Create a new product")
    public ResponseEntity<?> createProduct(@RequestBody ProductModel product){
        try {
            boolean status = productBusinessService.createProduct(product);
            if(status){
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "Update a product")
    public ResponseEntity<?> createProduct(@PathVariable("id") Integer productId, @RequestBody ProductModel product){
        try {
            boolean status = productBusinessService.updateProduct(product, productId);
            if(status){
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a product")
    public ResponseEntity<?> createProduct(@PathVariable("id") Integer productId){
        try {
            boolean status = productBusinessService.deleteProduct(productId);
            if(status){
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
