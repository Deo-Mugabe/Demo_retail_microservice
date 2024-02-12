package org.deomugabe.orderservice.dto;

import lombok.Data;
import org.deomugabe.orderservice.model.Order;
import org.deomugabe.orderservice.model.OrderLineItems;

import java.util.List;

@Data
public class OrderResponse {
    private String orderNumber;
    private List<OrderLineItems> orderLineItemsList;

    public static OrderResponse from(Order order){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.orderNumber = order.getOrderNumber();
        orderResponse.orderLineItemsList = order.getOrderLineItemsList();

        return orderResponse;
    }
}
