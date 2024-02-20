package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "restaurant")
public class RestaurantModel {

    @Id
    private UUID id;


}
