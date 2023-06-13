package np.com.esewa.learn.paymentservice.exceptions;

import np.com.esewa.learn.paymentservice.resources.ErrorResponse;
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
public class TransactionResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
//    format of error is of ErrorResponse
    public ResponseEntity<ErrorResponse> handleTransactionDetailsNotFoundException(TransactionNotFoundException transactionNotFoundException) {

        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(transactionNotFoundException.getMessage())
                .errorCode("PAYMENT_DETAILS_NOT_FOUND")
                .build(), HttpStatus.NOT_FOUND);
    }
}
