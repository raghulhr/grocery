package com.grocery.authservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_type")
public class UserType {
    @Id
    @Column(name = "user_type_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "user_type", nullable = false, length = 50)
    private String userType;

}