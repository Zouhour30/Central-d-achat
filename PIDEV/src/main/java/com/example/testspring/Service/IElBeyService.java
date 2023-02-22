package com.example.testspring.Service;

import com.example.testspring.Entities.Basket;
import com.example.testspring.Entities.Category;
import com.example.testspring.Entities.Product;

import java.util.List;

public interface IElBeyService {

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

}
