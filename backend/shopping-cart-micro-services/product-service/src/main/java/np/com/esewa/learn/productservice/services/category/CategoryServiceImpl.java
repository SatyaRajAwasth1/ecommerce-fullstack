package np.com.esewa.learn.productservice.services.category;

import np.com.esewa.learn.productservice.entities.Category;
import np.com.esewa.learn.productservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SatyaRajAwasth1
 * Written on: 6/5/2023
 * @project shopping-cart-micro-services
 * Service implementation for product categories
 */

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String[] getAllCategories() {
        return categoryRepository.findAll()
        .stream().map( Category::getName )
        .toArray(String[]::new);
    }

    @Override
    public void addCategory(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        System.out.println(categoryRepository.findCategoryByName(categoryName));
        return categoryRepository.findCategoryByName(categoryName);
    }


}
