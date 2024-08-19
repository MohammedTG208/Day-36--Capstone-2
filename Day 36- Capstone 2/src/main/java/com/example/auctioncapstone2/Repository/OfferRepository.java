package com.example.auctioncapstone2.Repository;

import com.example.auctioncapstone2.Model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    Offer findOfferById(Integer id);
    @Query("select offer from Offer offer where offer.productId=?1")
    List<Offer> getAllOffersByProductId(Integer id);
}
