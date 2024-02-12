package org.deomugabe.orderservice.controller;

import org.deomugabe.orderservice.dto.OrderRequest;
import org.deomugabe.orderservice.dto.OrderResponse;
import org.deomugabe.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
        return "Order Placed successfully";
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(){
        List<OrderResponse> orderResponse = orderService.getOrders();
        return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }

}
