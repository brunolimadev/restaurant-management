package br.com.fiap.restaurantmanagement.domain.ports.inbound;

import br.com.fiap.restaurantmanagement.domain.entities.Comment;

public interface CreateCommentUseCasePort {

    public Comment execute(Comment comment);


}
