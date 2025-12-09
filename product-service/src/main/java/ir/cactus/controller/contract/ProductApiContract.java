package ir.cactus.controller.contract;

import io.swagger.v3.oas.annotations.Operation;
import ir.cactus.domain.dto.ProductRequestDTO;
import ir.cactus.inventory.dto.InventoryServiceResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductApiContract {





    @Operation(summary = "create Product Function")
    @PostMapping(value = "/create")
     String createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO);

    @Operation(summary = "get Product Function")
    @GetMapping("/{id}")
    ProductRequestDTO getProductFromId(@PathVariable("id") Long id);


    @Operation(summary = "get Product stock")
    @GetMapping("/{id}/availability")
    InventoryServiceResponse getInventoryStockFromProductId(@PathVariable("id") Long id);

}
