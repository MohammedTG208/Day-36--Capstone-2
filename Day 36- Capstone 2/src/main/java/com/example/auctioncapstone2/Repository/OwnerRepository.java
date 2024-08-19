package com.example.auctioncapstone2.Repository;

import com.example.auctioncapstone2.Model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Owner findOwnerById(Integer id);
}
