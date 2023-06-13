package np.com.esewa.learn.eurekaregistrydiscoveryserverservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // Enables Eureka Server
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
