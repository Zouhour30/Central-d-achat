package pidev.elbey.Services;

import org.springframework.web.client.ResourceAccessException;
import pidev.elbey.Entities.*;

import pidev.elbey.Repositories.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class ShopService  implements IShopService{
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
    public Product assignProductToCategory(long idProduct, long idCategory) {
        Product product = productRepository.findById(idProduct).orElse(null);
        Category category = categoryRepository.findById(idCategory).orElse(null);
        // category.getProducts().add(product);
        // productRepository.save(product);
        product.setCategory(category);
        productRepository.save(product);
        return product;

    }
    /////////Basket

    @Override
    public Basket addBasket(Basket basket) {
        return basketReprository.save(basket);
    }

    @Override
    public Basket addProductsToBasket(Basket basket, List<Long> ids) {
        for (Long idp : ids) {
            Product p = productRepository.findById(idp).get();
            p.setBasket(basket);
            productRepository.save(p);
        }
        return basketReprository.save(basket);
    }

    @Override
    public Product deleteProductFromBasket(long idBasket, long idProduct) {

        Basket basket = basketReprository.findById(idBasket).get();
        Product product = productRepository.findById(idProduct).get();
        product.setBasket(null);

        return productRepository.save(product);

    }

    @Override
    public void removeBasket(Long idBasket) {
        basketReprository.deleteById(idBasket);
    }


    /*@Override
    public void affecterProductCategory(String nameCategory, String productName) {
        Product product=productRepository.findProductByProductName(productName);
        Category category=categoryRepository.findCategoriesByNameCategory(nameCategory);
        category.getProducts().add(product);
    }*/

    ///image without bloob


    @Override
    public String UploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String randomID = UUID.randomUUID().toString();
        String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
        String filePath = path + File.separator + fileName1;

        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return name;
    }

    @Override
    public List<Product> findProductsByCategory(String nameCategory) {
        List<Product> p = productRepository.findAll();
        List<Product> products = new ArrayList<>();
        for (Product i : p) {
            if (i.getCategory().getNameCategory().equals(nameCategory)
            ) {
                products.add(i);

            }
        }
        return products;
    }

    @Override
    public String watchYourBudget(long idBasket) {
        float sum = 0;
        Basket basket = basketReprository.findById(idBasket).get();
        for (Product p : basket.getProducts()) {
            sum = sum + p.getPriceProduct();
        }
        if (sum >= 100)
            return "you are in the dangerzone";
        else
            return "safe zone";


    }

    @Override
    public List<Product> findProductsByOrderByPriceProduct() {
        return productRepository.findProductsSortedByPriceAsc();
    }

    @Override
    public Long getProductCount() {
        return productRepository.count();
    }

    @Override
    public Long getTotalProductQuantity() {
        return productRepository.sumQuantity();
    }

    @Override
    public Double getAverageProductQuantity() {
        return productRepository.averageQuantity();
    }

    @Override
    public Integer getMinProductQuantity() {
        return productRepository.minQuantity();
    }

    @Override
    public Integer getMaxProductQuantity() {
        return productRepository.maxQuantity();
    }

    ////rating
    @Override
    public ResponseEntity<String> rateProduct(Long idProduct, double rating) {
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        Product product = optionalProduct.get();
        product.setRating(rating);
        productRepository.save(product);
        return ResponseEntity.ok("Product rated successfully");
    }

    @Override
    public void rateProduct2(Long idProduct, double rating) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResourceAccessException("Product not found with id " + idProduct));
        product.setRating(rating);
        productRepository.save(product);
        ResponseEntity.ok().build();
    }


    ////rating


    // @Scheduled(cron = "*/30 * * * * * ")
   /* void updatePriceBasket (){
        float totalprice=0;
        List<Basket>baskets=basketReprository.findAll();
        for(Basket b:baskets){
            for(Product p:b.getProducts()){
            /*{ totalprice=totalprice+p.getPriceProduct();}
            b.setTotalPrice(totalprice);
            basketReprository.save(b);*/
    //     System.out.println(p);
    //}}}
    // @Scheduled(cron = "*/30 * * * * * ")
    /*void bonjour(){
        System.out.println("hello World");
    }*/

}
