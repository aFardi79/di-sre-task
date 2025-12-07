package ir.cactus.exception;



import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalTime;
import java.util.Optional;


@RestControllerAdvice
public class ProductGlobalExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(ProductException.class)
    ResponseEntity<?>handleProductException(HttpServletRequest httpServletRequest){
        return ResponseEntity.of(Optional.of(ProductErrorResponse.builder().time(LocalTime.now().toString())
                .message("invalid product")
                .traceId(httpServletRequest.getMethod())));
    }

}
