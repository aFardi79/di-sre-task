import ir.cactus.domain.dto.ProductRequestDTO;
import ir.cactus.exception.ProductException;
import ir.cactus.repository.ProductRepository;
import ir.cactus.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@SpringBootTest
@ActiveProfiles("test")
@RequiredArgsConstructor
public class ProductServiceTest {


    private final ProductServiceImpl productService;
    private final ProductRepository productRepository;


    private static MockWebServer mockWebServer;


    @BeforeAll
    static void startServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(8081); // assuming InventoryClient points to localhost:8081 in test profile
    }

    @AfterAll
    static void shutdownServer() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void setup() {
        productRepository.deleteAll();
    }

    @Test
    void createProduct_inventoryDown_triggersFallback() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        ProductRequestDTO request = new ProductRequestDTO();
        request.setProductName("iphone");
        request.setProductPrice(12000L);

        ProductException exception = Assertions.assertThrows(ProductException.class,
                () -> productService.createProduct(request));

        Assertions.assertEquals("Inventory service not responding", exception.getMessage());

        // Verify product is still saved in DB
        Assertions.assertEquals(1, productRepository.count());
    }



}
