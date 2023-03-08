package com.example.testspring.Service;

import com.example.testspring.Entities.Basket;
import com.example.testspring.Entities.Category;
import com.example.testspring.Entities.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IShopService {
    //product
    Product addProduct(Product product) ;
    // void deleteProduct(Long id);

    List<Product> getAllProduct();

    void removeProduct(Long idProduct);

    void updateProduct(Product product);
    //category
    Category addCategory(Category category) ;
    List<Category> getAllCategory();

    void removeCategory(Long idCategory);

    void updateCategory(Category category);

    //affect product to category
    //   void affecterProductCategory(long idProduct, long idCategory);

    Product assignProductToCategory(long idProduct, long idCategory);
    //void affecterProductCategory(String nameCategory, String productName);

    ///////////////////BASKET
    Basket addBasket(Basket basket);

    Basket addProductsToBasket(Basket basket, List<Long>ids );

    Product deleteProductFromBasket(long idBasket, long idProduct);

    void removeBasket(Long idBasket);

    String UploadImage(String path, MultipartFile file) throws IOException;

    List<Product>findProductsByCategory(String nameCategory);

    String watchYourBudget(long idBasket);

    List<Product> findProductsByOrderByPriceProduct();
    /////stat
    Long getProductCount();

    Long getTotalProductQuantity();
    Double getAverageProductQuantity();
    Integer getMinProductQuantity();
    Integer getMaxProductQuantity();

    //rating
    ResponseEntity<String> rateProduct(Long idProduct, double rating);

    //rate out of 10
    void rateProduct2(Long idProduct, double rating);
}
