package com.example.auctioncapstone2.Service;

import com.example.auctioncapstone2.Api.ApiException;
import com.example.auctioncapstone2.Model.Favorite;
import com.example.auctioncapstone2.Model.Product;
import com.example.auctioncapstone2.Repository.FavoriteRepository;
import com.example.auctioncapstone2.Repository.ProductRepository;
import com.example.auctioncapstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    public List<Favorite> getAllFavorites() {
        if (favoriteRepository.findAll().isEmpty()) {
            throw new ApiException("No favorites found in DB");
        }
        return favoriteRepository.findAll();
    }
    public void addNewFavorite(Favorite favorite) {
        favoriteRepository.save(favorite);
    }
    public void deleteFavorite(Integer id) {
        if (favoriteRepository.findFavoriteById(id)==null){
            throw new ApiException("Favorite not found like this ID");
        }
        favoriteRepository.deleteById(id);
    }
    public void updateFavorite(Integer id, Favorite favorite) {
        if (favoriteRepository.findFavoriteById(id)==null){
            throw new ApiException("Favorite not found like this ID");
        }
        Favorite oldFavorite = favoriteRepository.findFavoriteById(id);
        oldFavorite.setProductId(favorite.getProductId());
        oldFavorite.setUserId(favorite.getUserId());
        favoriteRepository.save(oldFavorite);
    }

    //10
    public List<Product> getSimilarFavoriteProductsForUser(Integer userId) {
        if (userRepository.findUserById(userId) == null) {
            throw new ApiException("User not found by this ID");
        }
        List<Favorite> favorites = favoriteRepository.findFavoriteByUserId(userId);
        if (favorites.isEmpty()) {
            throw new ApiException("No favorite found on your list");
        }
        List<Integer> favoriteProductIds = new ArrayList<>();
        for (Favorite favorite : favorites) {
            favoriteProductIds.add(favorite.getProductId());
        }
        if (favoriteProductIds.isEmpty()) {
            throw new ApiException("No favorite products found");
        }
        List<Product> favoriteProducts = productRepository.findAllById(favoriteProductIds);

        List<Product> similarProducts = new ArrayList<>();

        for (Product favoriteProduct : favoriteProducts) {
            List<Product> productsByCategory = productRepository.findProductByCategoryId(favoriteProduct.getCategoryId());
            for (Product product : productsByCategory) {
                if (!favoriteProductIds.contains(product.getId()) && !similarProducts.contains(product)) {
                    if (product.getStatus().equalsIgnoreCase("active")) {
                        similarProducts.add(product);
                    }
                }
            }
        }
        return similarProducts;
    }

}
