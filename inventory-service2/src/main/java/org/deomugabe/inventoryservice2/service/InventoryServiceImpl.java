package org.deomugabe.inventoryservice2.service;

import org.deomugabe.inventoryservice2.dto.InventoryResponse;
import org.deomugabe.inventoryservice2.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;


    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String>  skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() >0)
                            .build())
                            .toList();
    }

   }
