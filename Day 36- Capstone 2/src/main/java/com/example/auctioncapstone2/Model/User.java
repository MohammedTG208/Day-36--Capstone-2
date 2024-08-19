package com.example.auctioncapstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "the name can not be null")
    @Size(max = 35,message = "max length for name is 35")
    @Column(columnDefinition = "varchar(35) not null")
    private String name;
    @Email(message = "Enter valid email")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "enter valid password")
    private String password;
    @Column(columnDefinition = "decimal(10,2) not null")
    private double balance;
    @Pattern(regexp = "(user|admin)+$",message = "user or admin only")
    @Column(columnDefinition = "enum('admin','user')")
    private String role;

}
