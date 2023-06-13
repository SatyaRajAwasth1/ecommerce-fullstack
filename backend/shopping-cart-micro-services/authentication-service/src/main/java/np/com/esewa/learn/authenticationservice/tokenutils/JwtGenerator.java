package np.com.esewa.learn.authenticationservice.tokenutils;

import np.com.esewa.learn.authenticationservice.model.User;

import java.util.Map;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/16/2023
 * @project shopping-cart-micro-services
 * @description @
 */
public interface JwtGenerator {
    Map<String, String> generateToken(User user);
}
