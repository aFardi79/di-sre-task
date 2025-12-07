package ir.cactus.controller.contract;

import ir.cactus.domain.dto.InventoryServiceResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface APIContract {



    @PostMapping("/increase/{id}")
    InventoryServiceResponse increaseInventoryStockByAddingProduct(@PathVariable("id") Long id);

    @GetMapping("/getStock/{productId}")
    InventoryServiceResponse getInventoryStockFromProductId(@PathVariable("productId") Long productId);

}
