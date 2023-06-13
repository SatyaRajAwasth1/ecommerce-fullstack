package np.com.esewa.learn.productservice;

import np.com.esewa.learn.productservice.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductserviceApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void category_Repository_Gives_Category_By_Name(){
        var categoryReturned = categoryRepository.findCategoryByName("men's clothing");
        assertEquals("men's clothing", categoryReturned.getName() );
    }

    @Test
    void contextLoads() {
    }

}
