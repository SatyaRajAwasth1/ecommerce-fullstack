package np.com.esewa.orderservice.exceptions;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/10/2023
 * @project orderservice
 * custom exception for order not found
 */
public class OrderNoFoundException extends RuntimeException{
    public OrderNoFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
