package com.example.auctioncapstone2.Repository;

import com.example.auctioncapstone2.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(Integer id);
    @Query("select product from Product product where product.user_id=?1")
    List<Product> getUserProduct(Integer userid);

    @Query("select pro from Product pro where pro.status=?1")
    List<Product> getProductStatus(String status);

    List<Product> findProductByCategoryId(Integer category_id);

    List<Product> findProductByOwnerId(Integer owner_id);
    @Query("select pro from Product pro where pro.end_date=?1")
    List<Product> findProductByEnd_date(LocalDate localDate);

    @Query("select pro from Product pro where pro.user_id=?2 and pro.ownerId=?1")
    Product findProductByOwnerIdAndUserId(Integer owner_id, Integer user_id);


}
