package np.com.esewa.learn.productservice.exceptions;

import np.com.esewa.learn.productservice.resources.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author SatyaRajAwasth1 on 5/9/2023
 * Exception handler for responding with custom exception
 */

@ControllerAdvice
public class ProductResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
//    format of error is of ErrorResponse
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {

        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(productNotFoundException.getMessage())
                .errorCode("PRODUCT_NOT_FOUND")
                .build(), HttpStatus.NOT_FOUND);
    }
}
