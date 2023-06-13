package np.com.esewa.learn.paymentservice.exceptions;

/**
 * @author SatyaRajAwasth1 on 5/9/2023
 * @project payment service
 * Custom Exception class for Transaction Not Found
 */
public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }

}
