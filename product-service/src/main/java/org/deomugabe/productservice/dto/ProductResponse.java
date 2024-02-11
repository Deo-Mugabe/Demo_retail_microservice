package org.deomugabe.productservice.dto;

import lombok.Data;
import org.deomugabe.productservice.model.Product;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private String name;
    private String description;
    private BigDecimal price;

    public static ProductResponse from(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.name=product.getName();
        productResponse.description=product.getDescription();
        productResponse.price=product.getPrice();

        return productResponse;
    }
}

