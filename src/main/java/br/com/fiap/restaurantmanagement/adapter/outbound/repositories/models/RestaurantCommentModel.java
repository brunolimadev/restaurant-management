package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
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

}
