package org.deomugabe.orderservice.service;

import org.deomugabe.orderservice.dto.OrderRequest;
import org.deomugabe.orderservice.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    void createOrder(OrderRequest orderRequest);

    List<OrderResponse> getOrders();
}
