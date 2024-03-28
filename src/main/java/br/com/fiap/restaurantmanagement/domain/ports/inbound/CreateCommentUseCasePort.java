package br.com.fiap.restaurantmanagement.domain.ports.inbound;

import br.com.fiap.restaurantmanagement.domain.entities.Comment;

/**
 * This interface represents the gateway to creating a review about the restaurant
 */
public interface CreateCommentUseCasePort {

    Comment execute(Comment comment);

}