package np.com.esewa.orderservice.services;

import np.com.esewa.orderservice.resources.OrderRequest;
import np.com.esewa.orderservice.resources.OrderResponse;
import org.springframework.stereotype.Service;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/10/2023
 * @project orderservice
 * Service interface for orders
 */

@Service
public interface OrderService {
    String placeOrder(OrderRequest orderRequest, String authorizationToken);

    OrderResponse getOrderDetails(String orderId, String authorizationToken);
}
