package np.com.esewa.learn.productservice.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author SatyaRajAwasth1 on 5/9/2023
 * A response DTO for product or response payload for products api
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties
public class ProductResponse implements Serializable {
    private long productId;
    private String productName;
    private int quantity;
    private float price;
    private String category;
//    product image link
    private String image;
    private String description;
}
