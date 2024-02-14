package org.deomugabe.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.deomugabe.orderservice.dto.InventoryResponse;
import org.deomugabe.orderservice.dto.OrderLineItemsDto;
import org.deomugabe.orderservice.dto.OrderRequest;
import org.deomugabe.orderservice.dto.OrderResponse;
import org.deomugabe.orderservice.event.OrderPlacedEvent;
import org.deomugabe.orderservice.model.Order;
import org.deomugabe.orderservice.model.OrderLineItems;
import org.deomugabe.orderservice.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{


    private final OrderRepository orderRepository;
    private final WebClient webClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    @Override
    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems =orderRequest.getOrderLineItemsDto()
                .stream()
                .map(this::mapTo)
                .toList();
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        //call inventory service, and place order if product is in
        // stock
       InventoryResponse[] inventoryResponsesArray = webClient.get()
                        .uri("http://localhost:8093/api/inventory",
                                uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                        .retrieve()
                        .bodyToMono(InventoryResponse[].class)
                        .block();
        assert inventoryResponsesArray != null;
        boolean allProductsInStock = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isInStock);

       if(allProductsInStock){
           orderRepository.save(order);
           kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
       }else {
           throw new IllegalArgumentException("Product is out of stock");
       }

    }

    @Override
    public List<OrderResponse> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderResponse::from)
                .toList();
    }

    private OrderLineItems mapTo(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());

        return orderLineItems;
    }


}
