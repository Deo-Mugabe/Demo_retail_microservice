package org.deomugabe.inventoryservice.service;

import org.deomugabe.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {


    List<InventoryResponse> isInStock(List<String> skuCode);


}
