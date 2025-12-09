package ir.cactus.service.impl;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import ir.cactus.domain.dto.ProductRequestDTO;
import ir.cactus.domain.model.Product;
import ir.cactus.exception.ProductException;
import ir.cactus.inventory.contract.InventoryClient;
import ir.cactus.inventory.dto.InventoryServiceResponse;
import ir.cactus.mapper.ProductMapper;
import ir.cactus.repository.ProductRepository;
import ir.cactus.service.api.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final InventoryClient inventoryClient;

    @Override
    @CircuitBreaker(name = "inventoryCB", fallbackMethod = "inventoryFallbacks")
    @TimeLimiter(name = "inventoryTL")
    @Bulkhead(name = "inventoryBH")
    public String createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productRepository.save(mapper.toEntity(productRequestDTO));
        log.info("saving completed");
        inventoryClient.increaseInventoryFromProduct(product.getId());
        return "se";
    }

    @Override
    public ProductRequestDTO getProductFromId(Long id) {
        log.info("getProduct With Id");
        return mapper.toDto(productRepository.findById(id)
                .orElseThrow(() -> new ProductException("invalid Id")));
    }

    @Override
    @CircuitBreaker(name = "inventoryCB", fallbackMethod = "inventoryFallbacks")
    @TimeLimiter(name = "inventoryTL")
    @Bulkhead(name = "inventoryBH")
    public InventoryServiceResponse getInventoryStockFromProductId(Long id) {
        InventoryServiceResponse inventoryServiceResponse = inventoryClient.getInventoryStockFromProductId(id);
        return inventoryServiceResponse;
    }

    public InventoryServiceResponse inventoryFallbacks(String productId, Throwable t) {
        log.error("Inventory service failure detected: {}, payload = {}",
                t.getMessage(), productId);
        throw new ProductException("inventory is not responding");
    }
}
