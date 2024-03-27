package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models;


import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.entities.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the restaurant comment model
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "restaurant_comment")
public class RestaurantCommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantModel restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "rating")
    private Long rating;

    public static List<RestaurantCommentModel> fromDomain(Restaurant restaurant) {

        List<RestaurantCommentModel> commentModels = new ArrayList<>();

        restaurant.getComments().forEach(c -> {

            RestaurantCommentModel restaurantCommentModel = new RestaurantCommentModel();
            restaurantCommentModel.setComment(c.getComment());
            restaurantCommentModel.setCreatedAt(LocalDateTime.now());
            commentModels.add(restaurantCommentModel);
        });
        return commentModels;
    }

    public Comment toDomain() {
        return new Comment(this.user.getId(),this.comment,this.restaurant.getId(), this.getCreatedAt(), this.rating);
    }
}
