package np.com.esewa.learn.paymentservice.controllers;

import np.com.esewa.learn.paymentservice.enums.PaymentMode;
import np.com.esewa.learn.paymentservice.resources.PaymentRequest;
import np.com.esewa.learn.paymentservice.resources.PaymentResponse;
import np.com.esewa.learn.paymentservice.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/9/2023
 * @project paymentservice
 * controller for mapping handling relating payment
 */

@Controller
@RequestMapping("/api/payment")
public class PaymentController {
    Logger log = Logger.getLogger("PaymentController.class");
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest) {

        log.info("\n PaymentController | doPayment | paymentRequest : " + paymentRequest.toString());

        return new ResponseEntity<>(
                paymentService.doPayment(paymentRequest),
                HttpStatus.OK
        );
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable String orderId) {

        log.info("\n PaymentController | getPaymentDetails | orderId : " + orderId);

        return new ResponseEntity<>(
                paymentService.getPaymentDetailsByOrderId(orderId),
                HttpStatus.OK
        );
    }

    @GetMapping("/modes")
    public ResponseEntity<PaymentMode[]> getAllPaymentModes(){
        System.out.println(Arrays.toString(PaymentMode.values()));
        PaymentMode[] paymentModes = PaymentMode.values();
        return  new ResponseEntity<>(paymentModes, HttpStatus.OK);
    }

}
