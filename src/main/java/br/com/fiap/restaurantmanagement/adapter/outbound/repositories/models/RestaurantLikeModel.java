package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "restaurant_like")
public class RestaurantLikeModel {

    @EmbeddedId
    private LikeModel id;
}
