package ir.cactus.exception;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryExceptionResponse {

    private String message;
    private String time;
    private String trackId;
}
