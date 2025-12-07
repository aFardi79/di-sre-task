package ir.cactus.controller.contract;

import ir.cactus.domain.dto.ProductRequestDTO;
import ir.cactus.inventory.dto.InventoryServiceResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductApiContract {



    @PostMapping(value = "/create")
     String createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO);


    @GetMapping("/{id}")
    ProductRequestDTO getProductFromId(@PathVariable("id") Long id);

    @GetMapping("/{id}/availability")
    InventoryServiceResponse getInventoryStockFromProductId(@PathVariable("id") Long id);

}
