package np.com.esewa.learn.productservice.services.product;

import np.com.esewa.learn.productservice.entities.Product;
import np.com.esewa.learn.productservice.exceptions.ProductNotFoundException;
import np.com.esewa.learn.productservice.repositories.ProductRepository;
import np.com.esewa.learn.productservice.resources.ProductRequest;
import np.com.esewa.learn.productservice.resources.ProductResponse;
import np.com.esewa.learn.productservice.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * @author Thinkpad on 5/9/2023
 * A service implementation for product service
 */

@Service
public class ProductServiceImpl implements ProductService{

    Logger log = Logger.getLogger("ProductServiceImpl.class");
    private final ProductRepository productRepository;
    private CategoryService categoryService;
    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long addProduct(ProductRequest productRequest) {
        Product product
                = Product.builder()
                    .productName(productRequest.getName())
                    .price(productRequest.getPrice())
                    .quantity(productRequest.getQuantity())
                    .imageLink(productRequest.getImage())
                    .category(categoryService.getCategoryByName(productRequest.getCategory()))
                    .description(productRequest.getDescription())
                    .build();
        product = productRepository.save(product);
        log.info("ProductServiceImpl | addProduct | Saved product with id: "+product.getProductId());
        return product.getProductId();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(
                        product -> new ProductResponse(
                                product.getProductId(),
                                product.getProductName(),
                                product.getQuantity(),
                                product.getPrice(),
                                product.getCategory().getName(),
                                product.getImageLink(),
                                product.getDescription()
                        )
                ).toList();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("ProductServiceImpl | getProductById | Getting product for id: "+productId);
        Product product
                = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductNotFoundException(" Product with given Id not found")
                );

        return ProductResponse.builder()
        .productName(product.getProductName())
        .price(product.getPrice())
        .quantity(product.getQuantity())
        .productId(productId)
        .build();
    }

    @Override
    public List<ProductResponse> getProductsByCategory(String categoryName) {

        List<Product> productList = productRepository.getAllByCategory(categoryService.getCategoryByName(categoryName));
        if (!productList.isEmpty()){
        return productList.stream().map(
                product -> new ProductResponse(
                        product.getProductId(),
                        product.getProductName(),
                        product.getQuantity(),
                        product.getPrice(),
                        product.getCategory().getName(),
                        product.getImageLink(),
                        product.getDescription()
                )
        ).toList();
    }
        return getAllProducts();
    }

    @Override
    public void editProduct(long productId, ProductRequest productRequest) {
        log.info("ProductService | editProduct(id, productToBe) | Edit product for id: "+productId);

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()){
           Product productToBeEdited
                    = Product.builder()
                        .productId(productId)
                        .productName(productRequest.getName())
                        .price(productRequest.getPrice())
                        .quantity(productRequest.getQuantity())
                    .build();
            productRepository.save(productToBeEdited);
            log.info(" updated product with id: "+productId);
        }
        else {
            log.info(" product with id: "+productId);
            throw new ProductNotFoundException("Product Not found with id "+productId+" To update");
        }


    }

    @Override
    public void deleteProductById(long productId) {
        log.info("ProductService | deleteProductById(id) | delete product for id: "+productId);

        Product productToBeDeleted
                = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductNotFoundException(" Product with given Id not found")
                );
        productRepository.delete(productToBeDeleted);
        log.info(" deleted product with id: "+productId);
    }

    @Override
    public void addListOfProducts(List<ProductRequest> listOfProductRequests) {
        for (ProductRequest productRequest : listOfProductRequests) {
            addProduct(productRequest);
        }

    }
}
