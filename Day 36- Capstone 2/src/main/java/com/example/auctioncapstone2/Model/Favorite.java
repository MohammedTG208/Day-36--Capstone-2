package com.example.auctioncapstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Positive(message = "enter valid product_id")
    @Column(columnDefinition = "int not null")
    private Integer productId;
    @Positive(message = "enter valid user_id")
    @Column(columnDefinition = "int not null")
    private Integer userId;

}
