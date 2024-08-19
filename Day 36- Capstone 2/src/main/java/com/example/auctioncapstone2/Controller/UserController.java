package com.example.auctioncapstone2.Controller;

import com.example.auctioncapstone2.Model.User;
import com.example.auctioncapstone2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getall/{adminId}")
    public ResponseEntity getAllUser(@PathVariable Integer adminId){
        return ResponseEntity.ok(userService.getAllUsers(adminId));
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            userService.addNewUser(user);
            return ResponseEntity.status(201).body("user added successfully");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.UpdateUser(user, id);
        return ResponseEntity.status(200).body("user updated successfully");
    }

    @GetMapping("/get/product/for/user/{userid}")
    public ResponseEntity getUserProduct(@PathVariable Integer userid){
        return ResponseEntity.status(200).body(userService.getUserProducts(userid));
    }

    @DeleteMapping("/delete/offer/admin/{adminId}/{offerId}")
    public ResponseEntity deleteOffer(@PathVariable Integer adminId, @PathVariable Integer offerId){
        userService.deleteOffer(adminId, offerId);
        return ResponseEntity.status(200).body("offer deleted successfully");
    }

    @DeleteMapping("/delete/product/{adminId}/{productid}")
    public ResponseEntity deleteProduct(@PathVariable Integer adminId, @PathVariable Integer productid){
        userService.deleteProduct(adminId, productid);
        return ResponseEntity.status(200).body("product deleted successfully");
    }

    @GetMapping("/get/pro/status/{status}")
    public ResponseEntity getUserStatus(@PathVariable String status){
        return ResponseEntity.status(200).body(userService.getProductsStatus(status));
    }
}
