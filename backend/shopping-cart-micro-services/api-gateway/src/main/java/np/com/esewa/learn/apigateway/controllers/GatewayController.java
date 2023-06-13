package np.com.esewa.learn.apigateway.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SatyaRajAwasth1
 * Written on: 6/11/2023
 * @project shopping-cart-micro-services
 * A gateway for testing sample ok or not
 */
@RestController
@CrossOrigin
public class GatewayController {

    @GetMapping("/api/")
    public ResponseEntity<Map<String, String>> sayOk() {
        System.out.println("called sayOk");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Ok message from gateways controller");
        return ResponseEntity.ok(response);
    }
}
