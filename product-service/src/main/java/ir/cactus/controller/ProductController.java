package ir.cactus.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import ir.cactus.controller.contract.ProductApiContract;
import ir.cactus.domain.dto.ProductRequestDTO;
import ir.cactus.inventory.dto.InventoryServiceResponse;
import ir.cactus.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "ProductService")
public class ProductController implements ProductApiContract {


    private final ProductServiceImpl productService;

    @Override
    public String createProduct(ProductRequestDTO productRequestDTO) {
        return productService.createProduct(productRequestDTO);
    }

    @Override
    public ProductRequestDTO getProductFromId(Long id) {
        return productService.getProductFromId(id);
    }

    @Override
    public InventoryServiceResponse getInventoryStockFromProductId(Long id) {
        return productService.getInventoryStockFromProductId(id);
    }
}
