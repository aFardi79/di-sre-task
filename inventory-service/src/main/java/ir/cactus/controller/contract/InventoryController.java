package ir.cactus.controller.contract;


import ir.cactus.domain.dto.InventoryServiceResponse;
import ir.cactus.service.impl.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory/")
@RequiredArgsConstructor
public class InventoryController implements APIContract{

    private final InventoryServiceImpl inventoryService;


    @Override
    public InventoryServiceResponse increaseInventoryStockByAddingProduct(Long id) {
        return inventoryService.increaseInventory(id);
    }

    @Override
    public InventoryServiceResponse getInventoryStockFromProductId(Long productId) {
        return inventoryService.getInventoryStockFromProductId(productId);
    }
}
