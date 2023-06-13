package np.com.esewa.learn.paymentservice.services;

import np.com.esewa.learn.paymentservice.entities.TransactionDetails;
import np.com.esewa.learn.paymentservice.enums.PaymentStatus;
import np.com.esewa.learn.paymentservice.exceptions.TransactionNotFoundException;
import np.com.esewa.learn.paymentservice.repositories.TransactionDetailsRepository;
import np.com.esewa.learn.paymentservice.resources.PaymentRequest;
import np.com.esewa.learn.paymentservice.resources.PaymentResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.logging.Logger;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/9/2023
 * @project paymentservice
 * implementation for payment service
 */

@Service
public class PaymentServiceImpl implements PaymentService{

    private final TransactionDetailsRepository transactionDetailsRepository;
    Logger log = Logger.getLogger("PaymentServiceImpl.class");

    public PaymentServiceImpl(TransactionDetailsRepository transactionDetailsRepository) {
        this.transactionDetailsRepository = transactionDetailsRepository;
    }

    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("\n PaymentServiceImpl | doPayment(paymentRequest) | do payment request ");

        TransactionDetails transactionDetails
                = TransactionDetails.builder()
                .paymentMode(paymentRequest.getPaymentMode())
                .paymentDate(Instant.now())
                .amount(paymentRequest.getAmount())
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .paymentStatus(PaymentStatus.SUCCESS)
                .build();
        transactionDetails = transactionDetailsRepository.save(transactionDetails);
        log.info(" payment transaction successful for "+transactionDetails.getOrderId()+" with transaction Id: "+transactionDetails.getId());
        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
        log.info("\n PaymentServiceImpl | getPaymentDetailsByOrderId(orderId) | for orderId: "+orderId);
        TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(orderId).orElseThrow(
                ()-> new TransactionNotFoundException("No payment transaction details for orderId "+orderId)
        );
        log.info(" payment details for Id: "+transactionDetails.getOrderId()+" returned. ");

        return PaymentResponse.builder()

            .paymentId(transactionDetails.getId())
            .paymentDate(transactionDetails.getPaymentDate())
            .paymentMode(transactionDetails.getPaymentMode())
            .amount(transactionDetails.getAmount())
            .orderId(orderId)
            .status(transactionDetails.getPaymentStatus())

        .build();
    }
}
