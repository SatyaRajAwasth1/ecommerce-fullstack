package np.com.esewa.learn.productservice.controllers;

import np.com.esewa.learn.productservice.entities.Category;
import np.com.esewa.learn.productservice.services.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author SatyaRajAwasth1
 * Written on: 6/5/2023
 * @project shopping-cart-micro-services
 * Controller for product category
 */

@RestController
@RequestMapping("/api/products")
//@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<String[]> getAllProductCategories(){
        System.out.println("getting all categories");
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/categories/add")
    public ResponseEntity<String> addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return new ResponseEntity<>("Category "+category+" added", HttpStatus.CREATED );
    }

}
