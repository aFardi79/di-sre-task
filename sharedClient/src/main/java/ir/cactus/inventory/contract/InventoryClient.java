package ir.cactus.inventory.contract;


import ir.cactus.inventory.InventoryFallBack;
import ir.cactus.inventory.dto.InventoryServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "inventory-service",fallback = InventoryFallBack.class)
public interface InventoryClient {


    @GetMapping("/api/v1/inventory/getStock/{productId}")
    InventoryServiceResponse getInventoryStockFromProductId(@PathVariable("productId") Long productId);

    @PostMapping("/api/v1/inventory/increase/{productId}")
    InventoryServiceResponse increaseInventoryFromProduct(@PathVariable("productId") Long productId);
}
