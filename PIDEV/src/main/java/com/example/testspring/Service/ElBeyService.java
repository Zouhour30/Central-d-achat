package com.example.testspring.Service;

import com.example.testspring.Entities.Category;
import com.example.testspring.Entities.Product;
import com.example.testspring.Repository.BasketReprository;
import com.example.testspring.Repository.CategoryRepository;
import com.example.testspring.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ElBeyService implements IElBeyService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    BasketReprository basketReprository;
    @Autowired
    CategoryRepository categoryRepository;




    @Override
    public Product addProduct(Product product) {
       return productRepository.save(product);
    }

   /* @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }*/

    @Override
    public List<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void removeProduct(Long idProduct) {
        productRepository.deleteById(idProduct);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    //Category
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public void removeCategory(Long idCategory) {
        categoryRepository.deleteById(idCategory);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

  /*  @Override
    public void affecterProductCategory(long idProduct, long idCategory) {
      Product product=productRepository.findById(idProduct).orElse(null);
      Category category=categoryRepository.findById(idCategory).orElse(null);
       category.getProducts().add(product);
       categoryRepository.save(category);

    }*/
    @Override
    public  Product assignProductToCategory(long idProduct, long idCategory){
        Product product=productRepository.findById(idProduct).orElse(null);
        Category category=categoryRepository.findById(idCategory).orElse(null);
       // category.getProducts().add(product);
       // productRepository.save(product);
        product.setCategory(category);
        productRepository.save(product);
        return product;

    }
    /*@Override
    public void affecterProductCategory(String nameCategory, String productName) {
        Product product=productRepository.findProductByProductName(productName);
        Category category=categoryRepository.findCategoriesByNameCategory(nameCategory);
        category.getProducts().add(product);
    }*/




}
