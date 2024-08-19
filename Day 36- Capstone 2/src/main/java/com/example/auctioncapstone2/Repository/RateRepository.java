package com.example.auctioncapstone2.Repository;

import com.example.auctioncapstone2.Model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {
    Rate findRateById(Integer id);

    List<Rate> findRateByOwnerId(Integer ownerId);

    @Query("SELECT AVG(rate.star) FROM Rate rate where rate.ownerId=?1")
    double findAverageRateByOwnerId(Integer ownerId);

    Rate findRateByOwnerIdAndUserId(Integer ownerId, Integer userId);
}
