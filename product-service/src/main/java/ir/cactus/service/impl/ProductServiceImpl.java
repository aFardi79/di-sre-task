package ir.cactus.service.impl;


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
    public InventoryServiceResponse getInventoryStockFromProductId(Long id) {
        InventoryServiceResponse inventoryServiceResponse = inventoryClient.getInventoryStockFromProductId(id);
        return inventoryServiceResponse;
    }
}
