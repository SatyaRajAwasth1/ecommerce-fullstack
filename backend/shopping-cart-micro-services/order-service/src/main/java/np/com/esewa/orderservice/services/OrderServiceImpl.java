package np.com.esewa.orderservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import np.com.esewa.orderservice.documents.Order;
import np.com.esewa.orderservice.enums.OrderStatus;
import np.com.esewa.orderservice.enums.PaymentStatus;
import np.com.esewa.orderservice.exceptions.OrderNoFoundException;
import np.com.esewa.orderservice.repositories.OrderRepository;
import np.com.esewa.orderservice.resources.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/10/2023
 * @project orderservice
 * Service interface implementation for orders
 */

@Service
public class OrderServiceImpl implements OrderService{
    Logger log = Logger.getLogger("OrderServiceImpl.class");
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.restTemplate = new RestTemplate();
    }

    public String initializeOrder(OrderRequest orderRequest, String authorizationToken){

        return "orderId";
    }

    @Override
    public String placeOrder(OrderRequest orderRequest, String authorizationToken) {
        log.info("\n OrderServiceImpl | placeOrder(orderRequest) | creating order with status created for order details:  "+orderRequest.toString());

//            Send request with JWT Token while calling APIS through RestTemplate, so get Token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer "+authorizationToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        Order order
                = Order.builder()
                .orderDate(Instant.now())
                .amount(orderRequest.getTotalAmount())
                .quantity(orderRequest.getQuantity())
                .productId(orderRequest.getProductId())
                .orderStatus(OrderStatus.CREATED)
                .deliveryAddress(orderRequest.getDeliveryAddress()
                )
                .build();

        order = orderRepository.save(order);
        Order updatedOrder = orderRepository.findById(order.getId()).orElseThrow();
        log.info("\n order placed with order Id: "+order.getId()+" on "+order.getOrderDate());

//        Creating a payment request for order after creating order
        PaymentRequest paymentRequest
                = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();
        try {
            log.info("\n requesting payment for order Id: "+order.getId()+" calling PAYMENT-SERVICE");
//            Http Entity with header and paymentRequest in body
            HttpEntity<PaymentRequest> httpEntityForPayment = new HttpEntity<>(paymentRequest,headers);
            ResponseEntity<Long> paymentIdResponseEntity
                    = restTemplate.postForEntity("http://localhost:8082/api/payment", httpEntityForPayment, Long.class);
            long paymentId = paymentIdResponseEntity.getBody();
            log.info("\n payment request done with transaction" +
                    "/payment Id: "+paymentId+" called from PAYMENT-SERVICE");

            log.info("\n getting payment details for order Id: "+order.getId()+" calling PAYMENT-SERVICE");

            ResponseEntity<Object> response
                    = restTemplate.exchange(
                            "http://localhost:8082/api/payment/order/"+order.getId(), HttpMethod.GET, httpEntity, Object.class);
            Object responseObject = response.getBody();
            ObjectMapper objectMapper = JsonMapper.builder()
                    .addModule(new JavaTimeModule())
                    .build();
            PaymentResponse paymentResponse = objectMapper.convertValue(responseObject, PaymentResponse.class);
            log.info("\n payment details for order Id: "+order.getId()+paymentResponse);

            updatedOrder.setOrderStatus(OrderStatus.PLACED);
            log.info("Payment  succeed");
        }
        catch (Exception e){
            log.info("\n payment failed for order Id: "+order.getId());
            updatedOrder.setOrderStatus(OrderStatus.PAYMENT_FAILED);
        }

        log.warning("updating existing order with order id: "+order.getId()+" with order status placed or payment_failed");
        orderRepository.save(updatedOrder);
        log.info("\n order success for order Id: "+updatedOrder.getId());
        return updatedOrder.getId();
    }

    @Override
    public OrderResponse getOrderDetails(String orderId, String authorizationToken) {
        log.info("\n OrderServiceImpl | getOrderDetails(orderId) | with orderId: "+orderId);
        //            Send request with JWT Token while calling APIS through RestTemplate, so get Token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (!authorizationToken.startsWith("Bearer ")) {
            headers.set("Authorization", "Bearer " + authorizationToken);
        }
        else {
            headers.set("Authorization", authorizationToken);
        }
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        Order order
                = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNoFoundException("Order not found for the order Id:" + orderId )
                );

        log.info("OrderServiceImpl | getOrderDetails | Invoking Product service to fetch the product for id: {}"+ order.getProductId());
        ProductResponse productResponse
                = restTemplate.exchange(
                "http://localhost:8081/api/products/" + order.getProductId(), HttpMethod.GET, httpEntity,
                ProductResponse.class
        ).getBody();

        log.info("OrderServiceImpl | getOrderDetails | Getting payment information form the payment Service");
        PaymentResponse paymentResponse
                = restTemplate.exchange(
                "http://localhost:8082/api/payment/order/" + order.getId(), HttpMethod.GET,httpEntity,
                PaymentResponse.class
        ).getBody();

        OrderResponse.ProductDetails productDetails
                = OrderResponse.ProductDetails
                .builder()
                .productName(Objects.requireNonNull(productResponse).getProductName())
                .productId(productResponse.getProductId())
                .quantity(productResponse.getQuantity())
                .price(productResponse.getPrice())
                .build();

        OrderResponse.PaymentDetails paymentDetails
                = OrderResponse.PaymentDetails
                .builder()
                .paymentId(Objects.requireNonNull(paymentResponse).getPaymentId())
                .paymentStatus(PaymentStatus.valueOf(paymentResponse.getStatus()))
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();

        OrderResponse orderResponse
                = OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .orderDate(order.getOrderDate())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();

        log.info("OrderServiceImpl | getOrderDetails | orderResponse : " + orderResponse.toString());

        return orderResponse;
    }
}
