package ir.cactus.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.Optional;


@RestControllerAdvice
public class InventoryExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InventoryException.class)
    public ResponseEntity<?> handleInventoryException(HttpServletRequest request,InventoryException ex){
        return ResponseEntity.of(Optional.of(InventoryExceptionResponse.builder()
                .time(LocalDate.now().toString())
                .message(ex.getMessage())
                .trackId(request.getMethod())));
    }
}
