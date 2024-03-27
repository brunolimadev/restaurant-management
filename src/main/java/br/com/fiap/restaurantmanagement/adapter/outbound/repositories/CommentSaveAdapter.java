package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.CommentRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.RestaurantRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.UserRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantCommentModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.UserModel;
import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class CommentSaveAdapter implements SaveAdapterPort<Comment> {

    private final CommentRepository commentRepository;

    public CommentSaveAdapter(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public Comment save(Comment entity) {

        RestaurantCommentModel commentModel = new RestaurantCommentModel();
        commentModel.setUser(new UserModel(entity.getUserId()));
        commentModel.setRestaurant(new RestaurantModel(entity.getIdRestaurant()));
        commentModel.setCreatedAt(LocalDateTime.now());
        commentModel.setComment(entity.getComment());
        commentModel.setRating(entity.getRating());

        try {
            commentRepository.save(commentModel);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("Verifique os parâmetros de entrada.");
        }
        return commentModel.toDomain();
    }
}

