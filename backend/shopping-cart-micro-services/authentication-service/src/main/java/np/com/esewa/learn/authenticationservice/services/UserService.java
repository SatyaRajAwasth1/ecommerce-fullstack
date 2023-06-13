package np.com.esewa.learn.authenticationservice.services;

import np.com.esewa.learn.authenticationservice.exceptions.UserNotFoundException;
import np.com.esewa.learn.authenticationservice.model.User;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/16/2023
 * @project shopping-cart-micro-services
 * An interface for user services
 */
public interface UserService {
    public void saveUser(User user);
    public User getUserByUserNameAndPassword(String name, String password) throws UserNotFoundException;
}
