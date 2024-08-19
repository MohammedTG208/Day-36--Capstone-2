package com.example.auctioncapstone2.Service;

import com.example.auctioncapstone2.Api.ApiException;
import com.example.auctioncapstone2.Model.Owner;
import com.example.auctioncapstone2.Model.Rate;
import com.example.auctioncapstone2.Repository.OwnerRepository;
import com.example.auctioncapstone2.Repository.ProductRepository;
import com.example.auctioncapstone2.Repository.RateRepository;
import com.example.auctioncapstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {
    private final RateRepository rateRepository;
    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    public List<Rate> getAllRates() {
        if (rateRepository.findAll().isEmpty()) {
            throw new ApiException("No rates found DB empty");
        }
        return rateRepository.findAll();
    }

    public void addNewRate(Rate rate) {
        if (productRepository.findProductByOwnerIdAndUserId(rate.getOwnerId(), rate.getUserId())==null) {
            throw new ApiException("you did not buy this product to rate it");
        }else {
            if (rateRepository.findRateByOwnerIdAndUserId(rate.getOwnerId(), rate.getUserId())!=null) {
                throw new ApiException("You are already add rate to this owner");
            }else {
                rateRepository.save(rate);
            }
        }
    }

    public void updateRate(Rate rate,Integer id) {
        if (rateRepository.findRateById(id)==null){
            throw new ApiException("Rate not found by this id");
        }
        Rate oldRate = rateRepository.findRateById(id);
        oldRate.setStar(rate.getStar());
        oldRate.setMessage(rate.getMessage());
        oldRate.setUserId(rate.getUserId());
        oldRate.setOwnerId(rate.getOwnerId());
        rateRepository.save(oldRate);
    }
    public void deleteRate(Integer userId,Integer id) {
        if (rateRepository.findRateById(id)==null){
            throw new ApiException("Rate not found by this id");
        }else{
            if (userRepository.findUserById(userId)==null){
                throw new ApiException("User not found by this id");
            }else {
                if (userRepository.findUserById(userId).getRole().equals("admin")){
                    rateRepository.deleteById(id);
                }else {
                    throw new ApiException("User is not admin");
                }
            }
        }
    }
    //11
    public List getOwnerRatesForAdmin(Integer adminId, Integer ownerId) {
        if (ownerRepository.findOwnerById(ownerId)==null){
            throw new ApiException("Owner not found by this id");
        }else {
            if (userRepository.findUserById(adminId)==null){
                throw new ApiException("User not found by this id");
            }else {
                List displayRate=new ArrayList<>();
                if (userRepository.findUserById(adminId).getRole().equalsIgnoreCase("admin")){
                    displayRate.add(ownerRepository.findOwnerById(ownerId));
                    displayRate.add(rateRepository.findRateByOwnerId(ownerId));
                }else {
                    throw new ApiException("User is not admin");
                }
                return displayRate;
            }
        }
    }
    //12
    public String getOwnerRatesForUser(Integer ownerId) {
        if (ownerRepository.findOwnerById(ownerId)==null){
            throw new ApiException("Owner not found by this id");
        }else {
           if (rateRepository.findRateByOwnerId(ownerId).isEmpty()){
               throw new ApiException("Rate not found by this id");
           }else {
               return String.format(
                       "Owner Name: %s, Email: %s, Phone: %s, Rate: %.2f",
                       ownerRepository.findOwnerById(ownerId).getName(),
                       ownerRepository.findOwnerById(ownerId).getEmail(),
                       ownerRepository.findOwnerById(ownerId).getPhone(),
                       rateRepository.findAverageRateByOwnerId(ownerId)
               );
           }
        }
    }

    //13
    public List<String> getBestOwnerRate() {
        List<String> bestOwners=new ArrayList<>();
        for (Owner owner : ownerRepository.findAll()) {
            if (rateRepository.findAverageRateByOwnerId(owner.getId()) >= 4) {
                String ownerDetails = String.format(
                        "Owner Name: %s, Email: %s, Phone: %s, Rate: %.2f",
                        owner.getName(),
                        owner.getEmail(),
                        owner.getPhone(),
                        rateRepository.findAverageRateByOwnerId(owner.getId())
                );
                bestOwners.add(ownerDetails);
            }
        }
        return bestOwners;
    }

}
