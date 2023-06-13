package np.com.esewa.learn.authenticationservice.tokenutils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import np.com.esewa.learn.authenticationservice.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/16/2023
 * @project shopping-cart-micro-services
 * implementation class for generating JWT
 */
@Component
public class JwtGeneratorImpl implements JwtGenerator {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${app.jwttoken.message}")
    private String message;
    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken = "";

        jwtToken = Jwts.builder().setSubject(
                user.getUserName())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"esewa").compact();

        Map<String, String> generatedJWTToken = new HashMap<>();
        generatedJWTToken.put("token", jwtToken);
        generatedJWTToken.put("message", message);

        return generatedJWTToken;
    }
}
