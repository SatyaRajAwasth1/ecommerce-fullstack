package np.com.esewa.learn.paymentservice.resources;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/9/2023
 * @project paymentservice
 * Custom response entity class for errors
 */
@Builder
@Setter
@Getter
public class ErrorResponse implements Serializable {
    private String errorMessage;
    private String errorCode;
}
