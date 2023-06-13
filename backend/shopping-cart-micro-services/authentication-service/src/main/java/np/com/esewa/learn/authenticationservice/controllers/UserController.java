package np.com.esewa.learn.authenticationservice.controllers;

import np.com.esewa.learn.authenticationservice.dtos.RegisterSuccessResponse;
import np.com.esewa.learn.authenticationservice.exceptions.UserNotFoundException;
import np.com.esewa.learn.authenticationservice.model.User;
import np.com.esewa.learn.authenticationservice.services.UserService;
import np.com.esewa.learn.authenticationservice.tokenutils.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/15/2023
 * @project shopping-cart-micro-services
 * Controller for login and basic functionalities
 */

@RestController
@RequestMapping("api/auth")
//@CrossOrigin // no required as cross-origin is enabled on the api gateway globally
public class UserController {
    private final UserService userService;
    private final JwtGenerator jwtGenerator;
    @Autowired
    public UserController(UserService userService, JwtGenerator jwtGenerator){
        this.userService=userService;
        this.jwtGenerator=jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try{
            userService.saveUser(user);
            return new ResponseEntity<>(new RegisterSuccessResponse("registration success, user username and password to login"), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            if(user.getUserName() == null || user.getPassword() == null) {
                throw new UserNotFoundException("Email or Password is Empty");
            }
            User userData = userService.getUserByUserNameAndPassword(user.getUserName(), user.getPassword());
            if(userData == null){
                throw new UserNotFoundException("Email or Password is Invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}

