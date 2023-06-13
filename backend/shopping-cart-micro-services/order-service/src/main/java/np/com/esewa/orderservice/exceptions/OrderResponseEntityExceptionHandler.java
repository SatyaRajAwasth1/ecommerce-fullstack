package np.com.esewa.orderservice.exceptions;

import np.com.esewa.orderservice.resources.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/10/2023
 * @project orderservice
 * a exception handler for orders error response
 */

@ControllerAdvice
public class OrderResponseEntityExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(OrderNoFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNoFoundException orderNoFoundException) {

        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(orderNoFoundException.getMessage())
                .errorCode("ORDER_DETAILS_NOT_FOUND")
                .build(), HttpStatus.NOT_FOUND);
    }
}
