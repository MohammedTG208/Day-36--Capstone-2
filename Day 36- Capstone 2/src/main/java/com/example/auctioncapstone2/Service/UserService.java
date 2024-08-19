package com.example.auctioncapstone2.Service;

import com.example.auctioncapstone2.Api.ApiException;
import com.example.auctioncapstone2.Model.Category;
import com.example.auctioncapstone2.Model.Favorite;
import com.example.auctioncapstone2.Model.Product;
import com.example.auctioncapstone2.Model.User;
import com.example.auctioncapstone2.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OfferRepository offerRepository;

    public List<User> getAllUsers(Integer adminID) {
        if (userRepository.findAll().isEmpty()){
            throw new ApiException("No users found in DB");
        }else {
            if (userRepository.findUserById(adminID).getRole().equalsIgnoreCase("admin")) {
                return userRepository.findAll();
            }else {
                throw new ApiException("You are not an admin to display all users");
            }
        }

    }
    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        if (userRepository.findUserById(id)==null){
            throw new ApiException("User not found by this ID to deleted");
        }
        userRepository.deleteById(id);
    }
    public void UpdateUser(User user,Integer id) {
        if (userRepository.findUserById(id)==null){
            throw new ApiException("User not found by this ID to updated");
        }
        User oldUser = userRepository.findUserById(id);
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setBalance(user.getBalance());
        userRepository.save(oldUser);
    }
    //4
    public List<Product> getUserProducts(Integer userId) {
        if (userRepository.findUserById(userId)==null){
            throw new ApiException("User not found by this ID");
        }else {
            if (productRepository.getUserProduct(userId).isEmpty()){
                throw new ApiException("No no user have this product");
            }else {
                return productRepository.getUserProduct(userId);
            }
        }
    }
    //5
    public void deleteOffer(Integer adminId, Integer offerId) {
        if (userRepository.findUserById(adminId)==null){
            throw new ApiException("User not found by this ID");
        }else {
            if(userRepository.findUserById(adminId).getRole().equalsIgnoreCase("admin")){
                if (offerRepository.findOfferById(offerId)!=null) {
                    if (productRepository.findProductById(offerRepository.findOfferById(offerId).getProductId()).getStatus().equalsIgnoreCase("active")) {
                        throw new ApiException("You can not delete this offer because is Active");
                    }else {
                        offerRepository.deleteById(offerId);
                    }
                }else {
                    throw new ApiException("Offer not found like this ID");
                }
            }else {
                throw new ApiException("You are not an admin");
            }
        }
    }
    //6
    public void deleteProduct(Integer adminId, Integer productId) {
        if (userRepository.findUserById(adminId)==null){
            throw new ApiException("User not found by this ID");
        }else {
            if(userRepository.findUserById(adminId).getRole().equalsIgnoreCase("admin")){
                if (productRepository.findProductById(productId)!=null) {
                    if (productRepository.findProductById(productId).getStatus().equalsIgnoreCase("active")) {
                        productRepository.deleteById(productId);
                    }else {
                        throw new ApiException("You can not delete this product because its active");
                    }
                }else {
                    throw new ApiException("Product not found like this ID");
                }
            }
        }
    }
    //7
    public List<Product> getProductsStatus(String status) {
        return productRepository.getProductStatus(status);
    }



}
