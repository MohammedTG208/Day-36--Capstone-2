package com.example.auctioncapstone2.Controller;

import com.example.auctioncapstone2.Model.Product;
import com.example.auctioncapstone2.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getall")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            productService.addNewProduct(product);
            return ResponseEntity.status(201).body("Product added successfully");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(200).body("Product deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id,@Valid @RequestBody Product product,Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            productService.updateProduct(product,id);
            return ResponseEntity.status(201).body("Product updated successfully");
        }
    }

    @PutMapping("/update/status/{ownerId}/{productId}/{status}")
    public ResponseEntity updateProductStatusForOwner(@PathVariable Integer ownerId,@PathVariable Integer productId, @PathVariable String status) {
        productService.ChangeProductStatusForOwner(ownerId,productId,status);
        return ResponseEntity.status(201).body("Product status updated successfully");
    }

    @GetMapping("/get/product/last/date")
    public ResponseEntity getProductLastDate() {
        return ResponseEntity.status(200).body(productService.lastDateToAddOffer());
    }

    @GetMapping("/get/pro/by/{status}")
    public ResponseEntity getProductByStatus(@PathVariable String status) {
        return ResponseEntity.status(200).body(productService.getProductsByStatus(status));
    }
}
