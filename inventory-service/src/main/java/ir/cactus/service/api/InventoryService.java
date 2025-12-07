package ir.cactus.service.api;

import ir.cactus.domain.dto.InventoryServiceResponse;

public interface InventoryService {

    InventoryServiceResponse increaseInventory(Long id);

    InventoryServiceResponse getInventoryStockFromProductId(Long id);
}
