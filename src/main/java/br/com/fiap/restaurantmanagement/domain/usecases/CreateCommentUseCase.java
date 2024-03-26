package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateCommentUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;

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
