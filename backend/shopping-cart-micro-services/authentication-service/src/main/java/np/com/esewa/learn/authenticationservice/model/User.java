package np.com.esewa.learn.authenticationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/16/2023
 * @project shopping-cart-micro-services
 * An entity user to login and access stuffs on shopping cart
 */

@Entity(name = "users")
public class User {
    @Id
    private String userName;
    private String password;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
