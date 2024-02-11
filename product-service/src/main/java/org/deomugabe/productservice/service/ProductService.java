package org.deomugabe.productservice.service;

import org.deomugabe.productservice.dto.ProductRequest;
import org.deomugabe.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);


    List<ProductResponse> getAllProducts();
}
