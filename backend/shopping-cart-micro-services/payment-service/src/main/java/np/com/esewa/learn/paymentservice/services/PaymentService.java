package np.com.esewa.learn.paymentservice.services;

import np.com.esewa.learn.paymentservice.resources.PaymentRequest;
import np.com.esewa.learn.paymentservice.resources.PaymentResponse;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/9/2023
 * @project paymentservice
 * A service interface for transactions and payment
 */
public interface PaymentService {
//    return paymentId after successful payment
    public long doPayment(PaymentRequest paymentRequest);
    PaymentResponse getPaymentDetailsByOrderId(String orderId);
}
