package org.deomugabe.orderservice.service;

import org.deomugabe.orderservice.dto.OrderRequest;

public interface OrderService {
    void createOrder(OrderRequest orderRequest);

}
