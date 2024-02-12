package org.deomugabe.inventoryservice2.service;

import org.deomugabe.inventoryservice2.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {


    List<InventoryResponse> isInStock(List<String> skuCode);


}
