package ir.cactus.inventory;

import ir.cactus.inventory.contract.InventoryClient;
import ir.cactus.inventory.dto.InventoryServiceResponse;
import org.springframework.stereotype.Component;


@Component
public class InventoryFallBack implements InventoryClient {

    @Override
    public InventoryServiceResponse getInventoryStockFromProductId(Long productId) {
        return new InventoryServiceResponse(0L);
    }

    @Override
    public InventoryServiceResponse increaseInventoryFromProduct(Long productId) {
        return new InventoryServiceResponse(0L);
    }
}
