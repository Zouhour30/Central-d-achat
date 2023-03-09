package pidev.elbey.RestControllers;

import pidev.elbey.Entities.*;
import pidev.elbey.Services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.MediaType;

import java.io.IOException;


import java.util.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Elbey")
public class ShopRestController {
    @Autowired
    ShopService elBeyService;

@PostMapping("/addProduct")
     public Product addProduct(@RequestBody Product product){
      return elBeyService.addProduct(product);
}
/*@DeleteMapping("/delProduct/{id}")
public void deleteProduct(@PathVariable Long id) {

         elBeyService.deleteProduct(id);

}*/
    @GetMapping("/gettAllProducts")
    public List<Product> getAllProduct() {
        return elBeyService.getAllProduct();
    }


    @DeleteMapping("/deleteProduct/{idProduct}")
    public void removeProduct(@PathVariable Long idProduct){
        elBeyService.removeProduct(idProduct);

    }
    @PutMapping("/updateProduct")
    public void updateProduct(@RequestBody Product product) {
        elBeyService.updateProduct(product);
    }

    //Category
    @PostMapping("/addCategory")
    public Category addCategory(@RequestBody Category category){
        return elBeyService.addCategory(category);
    }
    @GetMapping("/gettAllCategories")
    public List<Category> getAllCategory(){
        return elBeyService.getAllCategory();
    }

    @DeleteMapping("/deleteCategory/{idCategory}")
    public void removeCategory(@PathVariable Long idCategory){
        elBeyService.removeCategory(idCategory);
    }
    @PutMapping("/updateCategory")
    public void updateCategory(Category category){
        elBeyService.updateCategory(category);
    }

    @PutMapping ("/assignProductToCategory/{idCategory}/{idProduct}")
    public Product assignProductToCategory(@PathVariable("idCategory") long idCategory, @PathVariable("idProduct") long idProduct){
   return  elBeyService.assignProductToCategory(idCategory,idProduct);
    }
    //image with blob
    @RequestMapping(value = "/addWithImage", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addWithImage (@RequestPart("product") Product product,
                                    @RequestPart("imageFile")MultipartFile[] file){
        try{
            // Set<ImageModel> images= uploadImage
            Set<ImageModel> images=uploadImage(file);
            product.setImages(images);
            return   elBeyService.addProduct(product);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
        public Set<ImageModel>  uploadImage(MultipartFile[] multipartFiles)throws IOException {
            Set<ImageModel> imageModels=new HashSet<>();
            for(MultipartFile file:multipartFiles){
                ImageModel imageModel=new ImageModel(
                        file.getOriginalFilename(),
                        file.getContentType()

                );
                imageModels.add(imageModel);
            }
            return imageModels;
        }
    @PostMapping("/addBasket")
    public Basket addBasket(Basket basket){
        return elBeyService.addBasket(basket);
    }

  @PutMapping ("/addProductsToBasket")
    public  Basket addProductsToBasket(@RequestBody Basket basket, @RequestParam List<Long> ids){
        return elBeyService.addProductsToBasket(basket, ids);
    }
    @PutMapping ("/deleteProductFromBakset/{idBasket}/{idProduct}")
    public  Product deleteProductFromBasket(@PathVariable long idBasket, @PathVariable long idProduct){
        return elBeyService.deleteProductFromBasket(idBasket,idProduct);
    }
@DeleteMapping("/deleteBasket/idBasket")
public void removeBasket(@PathVariable Long idBasket)
{
    elBeyService.removeBasket(idBasket);
}

//image without blob


@GetMapping("/getproductbycategory/{nameCategory}")
   public List<Product> getProductsByCategory(@PathVariable  String nameCategory){
    return elBeyService.findProductsByCategory(nameCategory);
}
@GetMapping("/budgetController/{idBasket}")
    public String watchYourBudget(@ PathVariable long idBasket){
    return elBeyService.watchYourBudget(idBasket);

    }

    @GetMapping("/getproductsByOrderASC")
    public List<Product> findProductsSortedByPriceAsc(){
    return elBeyService.findProductsByOrderByPriceProduct();
    }

    @GetMapping("/count")
    public Long getProductCount() {
        return elBeyService.getProductCount();}

    @GetMapping("/totalQuantity")
    public Long getTotalProductQuantity() {
        return elBeyService.getTotalProductQuantity();
    }
    @GetMapping("/averageQuantity")
    public Double getAverageProductQuantity() {
        return elBeyService.getAverageProductQuantity();
    }
    @GetMapping("/minQuantity")
    public Integer getMinProductQuantity() {
        return elBeyService.getMinProductQuantity();
    }
    @GetMapping("/maxQuantity")
    public Integer getMaxProductQuantity() {
        return elBeyService.getMaxProductQuantity();
    }

    ///rating

    @PostMapping("/{productId}/rate")
    public ResponseEntity<String> rateProduct(@PathVariable Long productId, @RequestParam("rating") double rating) {
    return elBeyService.rateProduct(productId,rating);
}
//rate2

    @PostMapping("/rate2/{productId}")
    public void rateProduct2(@PathVariable Long productId, @RequestParam Integer rating) {
        elBeyService.rateProduct(productId, rating);

    }
}
