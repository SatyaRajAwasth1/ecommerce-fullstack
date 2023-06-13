package np.com.esewa.learn.productservice.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author SatyaRajAwasth1 on 5/9/2023
 * A request DTO for product or request payload for products api
 */

@Setter
@Getter
@Builder
@JsonIgnoreProperties
public class ProductRequest implements Serializable {
    private String name;
    private int quantity;
    private float price;
    private String category;
    private String image;
    private String description;
}
