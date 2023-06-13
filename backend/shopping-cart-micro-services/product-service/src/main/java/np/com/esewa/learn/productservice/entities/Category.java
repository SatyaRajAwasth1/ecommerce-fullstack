package np.com.esewa.learn.productservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author SatyaRajAwasth1
 * Written on: 6/5/2023
 * @project shopping-cart-micro-services
 * An entity for category of producsts
 */

@Getter
@Setter
@Entity(name = "categories")
public class Category {
    @Id
    private short id;
    private String name;
}
