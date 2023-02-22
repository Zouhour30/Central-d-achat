package com.example.testspring.RestControllers;

import com.example.testspring.Entities.Category;
import com.example.testspring.Entities.ImageModel;
import com.example.testspring.Entities.Product;
import com.example.testspring.Repository.CategoryRepository;
import com.example.testspring.Repository.ProductRepository;
import com.example.testspring.Service.ElBeyService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.util.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ElBeyRestController {
    @Autowired
    ElBeyService elBeyService;

@PostMapping("/addProduct")
     public Product addProduct(Product product){
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
    public Category addCategory(Category category){
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
                        file.getContentType(),
                        file.getBytes()
                );
                imageModels.add(imageModel);
            }
            return imageModels;
        }


}
