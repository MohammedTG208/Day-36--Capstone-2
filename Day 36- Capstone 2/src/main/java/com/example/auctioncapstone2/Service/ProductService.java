package com.example.auctioncapstone2.Service;

import com.example.auctioncapstone2.Api.ApiException;
import com.example.auctioncapstone2.Model.Category;
import com.example.auctioncapstone2.Model.Product;
import com.example.auctioncapstone2.Repository.CategoryRepository;
import com.example.auctioncapstone2.Repository.OwnerRepository;
import com.example.auctioncapstone2.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OwnerRepository ownerRepository;

    public List<Product> getAllProducts() {
        if (productRepository.findAll().isEmpty()){
            throw new ApiException("No products found in the database");
        }
        checkDate();
        return productRepository.findAll();
    }
    //3
    public void addNewProduct(Product product) {
        checkDate();
        for (Product p :  productRepository.findProductByOwnerId(product.getOwnerId())) {
            if (p.getStatus().equalsIgnoreCase("active")) {
                throw new ApiException("You already have an active product.");
            }
        }
        productRepository.save(product);
    }
    public void updateProduct(Product product,Integer id) {
        if (productRepository.findProductById(id)==null){
            throw new ApiException("Product not found by this id ");
        }
        Product oldProduct = productRepository.findProductById(id);
        oldProduct.setTitle(product.getTitle());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setCategoryId(product.getCategoryId());
        oldProduct.setOwnerId(product.getOwnerId());
        oldProduct.setEnd_date(product.getEnd_date());
        oldProduct.setStatus(product.getStatus());
        productRepository.save(oldProduct);
    }
    public void deleteProduct(Integer id) {
        if (productRepository.findProductById(id)==null){
            throw new ApiException("Product not found by this id ");
        }
        checkDate();
        productRepository.deleteById(id);
    }
    //2
    public void ChangeProductStatusForOwner(Integer ownerId, Integer productId, String status) {
        if (productRepository.findProductById(productId)==null){
            throw new ApiException("Product not found by this id ");
        }else {
            if (productRepository.findProductById(productId).getStatus().equalsIgnoreCase("cancel")){
               throw new ApiException("Product status already cancel");
            }else {
                checkDate();
                if (productRepository.findProductById(productId).getStatus().equalsIgnoreCase("buy")){
                    throw new ApiException("Product status already buy");
                }else {
                    if (productRepository.findProductById(productId).getOwnerId() == ownerId) {
                       Product pro= productRepository.findProductById(productId);
                       pro.setStatus(status);
                       productRepository.save(pro);
                    } else {
                        throw new ApiException("you are not owner for this Product");
                    }
                }
            }
        }
    }

    public void checkDate() {
        LocalDate dateTime = LocalDate.now();
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getEnd_date().isBefore(dateTime)) {
                if (product.getUserPrice() > 0) {
                    product.setStatus("buy");
                } else {
                    product.setStatus("canceled");
                }
                productRepository.save(product);
            }
        }
    }
    //8
    public List<Product> getProductsByCategory(String category) {
        if (categoryRepository.findCategoryByName(category)==null){
            throw new ApiException("Category not found by this name");
        }else {
            Category category1=categoryRepository.findCategoryByName(category);
            return productRepository.findProductByCategoryId(category1.getId());
        }
    }

    //14
    public List<Product> lastDateToAddOffer(){
        LocalDate dateTime=LocalDate.now();
        return productRepository.findProductByEnd_date(dateTime);
    }

    //15
    public List getProductsByStatus(String status) {
        List displayAllInfo=new ArrayList<>();
        for (int i=0;i<productRepository.findAll().size();i++){
            if (productRepository.findProductById(productRepository.findAll().get(i).getId()).getStatus().equalsIgnoreCase(status)){
                displayAllInfo.add("Owner: "+ownerRepository.findOwnerById(productRepository.findAll().get(i).getOwnerId()).getName());
                displayAllInfo.add(ownerRepository.findOwnerById(productRepository.findAll().get(i).getOwnerId()).getEmail());
                displayAllInfo.add(ownerRepository.findOwnerById(productRepository.findAll().get(i).getOwnerId()).getPhone());
                displayAllInfo.add("Product: "+productRepository.findProductByOwnerId(ownerRepository.findOwnerById(i+1).getId()));
            }
        }
        return displayAllInfo;
    }

}
