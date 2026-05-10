package ebay.flightbooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleFlightNotFound(
            FlightNotFoundException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(NoSeatsAvailableException.class)
    public ResponseEntity<Map<String, String>> handleNoSeatsAvailable(
            NoSeatsAvailableException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> response = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                response.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(response);
    }
}