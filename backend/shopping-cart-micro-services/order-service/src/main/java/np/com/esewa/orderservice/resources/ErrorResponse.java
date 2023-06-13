package np.com.esewa.orderservice.resources;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/10/2023
 * @project orderservice
 * a class for sending as an error response
 */
@Builder
@Setter
@Getter
public class ErrorResponse implements Serializable {
    private String errorMessage;
    private String errorCode;
}
