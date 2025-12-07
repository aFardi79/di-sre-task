package ir.cactus.service.impl;


import ir.cactus.domain.dto.InventoryServiceResponse;
import ir.cactus.domain.model.Inventory;
import ir.cactus.repository.InventoryRepository;
import ir.cactus.service.api.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {


    private final InventoryRepository repository;

    @Override
    public InventoryServiceResponse increaseInventory(Long id){
        Inventory inventory = repository.findInventoryByProductId(id).get();
        if (Objects.nonNull(inventory)){
            inventory.setStock(inventory.getStock()+1);
       }else{
            inventory= new Inventory();
            inventory.setProductId(id);
            inventory.setStock(1L);
           inventory=repository.save(inventory);
        }
        return InventoryServiceResponse.builder()
                .stock(inventory.getStock()).build();
    }


    @Override
    public InventoryServiceResponse getInventoryStockFromProductId(Long id) {
        Inventory inventory=repository.findInventoryByProductId(id).get();
        return InventoryServiceResponse.builder().stock(inventory.getStock()).build();
    }
}
