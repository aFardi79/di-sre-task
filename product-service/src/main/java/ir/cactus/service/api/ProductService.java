package ir.cactus.service.api;

import ir.cactus.domain.dto.ProductRequestDTO;
import ir.cactus.inventory.dto.InventoryServiceResponse;

public interface ProductService {
    String createProduct(ProductRequestDTO productRequestDTO);
    ProductRequestDTO getProductFromId(Long id);
    InventoryServiceResponse getInventoryStockFromProductId(Long id);

}
