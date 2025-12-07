package ir.cactus.exception;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductErrorResponse {

    private String message;
    private String time;
    private String traceId;
}
