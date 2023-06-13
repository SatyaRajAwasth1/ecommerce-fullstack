package np.com.esewa.learn.productservice.resources;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/9/2023
 * @project productservice
 * DTO for error response
 */

@Setter
@Getter
@Builder
public class ErrorResponse implements Serializable {
    private String errorMessage;
    private String errorCode;
}
