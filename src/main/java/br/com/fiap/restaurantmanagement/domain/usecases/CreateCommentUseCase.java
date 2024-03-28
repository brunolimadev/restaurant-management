package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateCommentUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;

/**
 * This class represents the use case for making a comment about the restaurant
 */
public class CreateCommentUseCase implements CreateCommentUseCasePort {

    private final SaveAdapterPort<Comment> saveCommentAdapterPort;

    public CreateCommentUseCase(SaveAdapterPort<Comment> saveCommentAdapterPort) {
        this.saveCommentAdapterPort = saveCommentAdapterPort;
    }

    @Override
    public Comment execute(Comment comment) {
        return this.saveCommentAdapterPort.save(comment);
    }
}
