package np.com.esewa.learn.authenticationservice.services;

import np.com.esewa.learn.authenticationservice.exceptions.UserNotFoundException;
import np.com.esewa.learn.authenticationservice.model.User;
import np.com.esewa.learn.authenticationservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/16/2023
 * @project shopping-cart-micro-services
 * Service implementation class for user service
 */

@Service
public class UserServiceImpl implements UserService{
    static final Logger log = Logger.getLogger("UserServiceImpl.class");
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        log.info("UserServiceImpl: saveUser(user) -> Saving user with userName: "+user.getUserName());
        user = userRepository.save(user);
        log.info("Saved user with userName: "+user.getUserName());
    }

    @Override
    public User getUserByUserNameAndPassword(String name, String password) throws UserNotFoundException {
        log.info("UserServiceImpl: getUserByUserNameAndPassword(name, password) -> Saving user with userName: "+name);
        Optional<User> optionalUser = userRepository.findUserByUserNameAndPassword(name,password);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }

       throw new UserNotFoundException("User not found for provided username and password");
    }
}
