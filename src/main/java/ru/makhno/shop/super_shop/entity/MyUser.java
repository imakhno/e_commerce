package ru.makhno.shop.super_shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String phone;
    private String username;

    private byte[] profilePicture; // Хранение изображения в формате BYTEA
    private String city;
    private String street;
    private String postal_code;
    private String house;
    private String apartment;


    @ManyToMany(fetch = FetchType.EAGER) // Загружаем роли сразу
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_authority"))
    private List<Authority> authorityList;

    public MyUser(String name, String surname, String password, String username, String phone) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.username = username;
        this.phone = phone;
    }
}
