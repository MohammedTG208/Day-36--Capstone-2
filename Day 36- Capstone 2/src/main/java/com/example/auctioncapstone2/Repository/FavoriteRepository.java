package com.example.auctioncapstone2.Repository;

import com.example.auctioncapstone2.Model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    Favorite findFavoriteById(Integer id);

    List<Favorite> findFavoriteByUserId(Integer userId);
}
