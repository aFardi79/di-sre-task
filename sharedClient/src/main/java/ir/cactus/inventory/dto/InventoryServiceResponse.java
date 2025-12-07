package ir.cactus.inventory.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryServiceResponse implements Serializable {
    private Long stock;
}
