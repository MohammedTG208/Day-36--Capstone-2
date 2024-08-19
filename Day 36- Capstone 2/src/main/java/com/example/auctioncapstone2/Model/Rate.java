package com.example.auctioncapstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "star<=5")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Positive(message = "enter valid value for user id")
    @Column(columnDefinition = "int not null")
    private Integer userId;
    @Positive(message = "Enter valid value for owner id")
    @Column(columnDefinition = "int not null")
    private Integer ownerId;
    @NotNull(message = "message can not be null")
    @Size(max = 200,message = "max length for massage 200")
    @Column(columnDefinition = "varchar(200) not null")
    private String message;
    @Max(value = 5,message = "max value for star 5")
    @Positive(message = "enter valid value for star")
    private Integer star;
}
