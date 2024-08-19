package com.example.auctioncapstone2.Service;

import com.example.auctioncapstone2.Api.ApiException;
import com.example.auctioncapstone2.Model.Owner;
import com.example.auctioncapstone2.Model.Product;
import com.example.auctioncapstone2.Repository.OwnerRepository;
import com.example.auctioncapstone2.Repository.ProductRepository;
import com.example.auctioncapstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Owner> getAllOwners(Integer adminId) {
        if (userRepository.findUserById(adminId) == null) {
            throw new ApiException("you are not admin");
        }else {
            if (ownerRepository.findAll().isEmpty()) {
                throw new ApiException("No owner found DB is Empty");
            }
            return ownerRepository.findAll();
        }
    }

    public void addNewOwner(Owner owner){
        ownerRepository.save(owner);
    }
    public void deleteOwner(Integer id){
        if (ownerRepository.findOwnerById(id)==null){
            throw new ApiException("Owner not found by this ID");
        }
        ownerRepository.deleteById(id);
    }

    public void updateOwner(Owner owner,Integer id){
        if (ownerRepository.findOwnerById(id)==null){
            throw new ApiException("Owner not found by this ID");
        }
        Owner oldOwner = ownerRepository.findOwnerById(id);
        oldOwner.setName(owner.getName());
        oldOwner.setPassword(owner.getPassword());
        oldOwner.setEmail(owner.getEmail());
        ownerRepository.save(oldOwner);
    }
    //9
    public List<Product> getAllOwnerProduct(Integer ownerId){
        if (ownerRepository.findOwnerById(ownerId)==null){
            throw new ApiException("Owner not found by this ID");
        }else {
            if (productRepository.findProductByOwnerId(ownerId).isEmpty()){
                throw new ApiException("You don't have any product yet");
            }else {
                return productRepository.findProductByOwnerId(ownerId);
            }
        }
    }


}
