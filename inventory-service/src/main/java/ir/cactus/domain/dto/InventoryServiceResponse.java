package ir.cactus.domain.dto;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class InventoryServiceResponse implements Serializable {
    private Long productId;
    private Long stock;
}
