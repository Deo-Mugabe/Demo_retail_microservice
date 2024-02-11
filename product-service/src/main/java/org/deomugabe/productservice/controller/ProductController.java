package org.deomugabe.productservice.controller;

import org.deomugabe.productservice.dto.ProductRequest;
import org.deomugabe.productservice.dto.ProductResponse;
import org.deomugabe.productservice.model.Product;
import org.deomugabe.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<ProductResponse> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> creatProduct(@RequestBody ProductRequest productRequest ){
        ProductResponse productResponse = productService.createProduct(productRequest);
        return new ResponseEntity<>(productResponse,HttpStatus.CREATED);
    }
}
