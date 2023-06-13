package np.com.esewa.orderservice.controllers;

import np.com.esewa.orderservice.resources.OrderRequest;
import np.com.esewa.orderservice.resources.OrderResponse;
import np.com.esewa.orderservice.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/10/2023
 * @project orderservice
 * Controller class for order
 */

@RestController
@RequestMapping("/api/order")
public class OrderController {
    Logger log = Logger.getLogger("OrderController.class");
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place-order")
    ResponseEntity<String> placeOrder(@RequestHeader(value="Authorization") String authorizationHeader, @RequestBody OrderRequest orderRequest){
        log.info("OrderController | placeOrder | orderRequest: {}"+ orderRequest.toString());
        String orderId = orderService.placeOrder(orderRequest, authorizationHeader);

        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable String orderId, @RequestHeader(value="Authorization") String authorizationHeader) {

        log.info("OrderController | getOrderDetails is called");

        OrderResponse orderResponse = orderService.getOrderDetails(orderId, authorizationHeader);

        log.info("OrderController | getOrderDetails | orderResponse : " + orderResponse.toString());

        return new ResponseEntity<>(orderResponse,
                HttpStatus.OK);
    }
}
