package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "restaurant_like")
public class RestaurantLikeModel {

    @EmbeddedId
    private LikeModel id;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
