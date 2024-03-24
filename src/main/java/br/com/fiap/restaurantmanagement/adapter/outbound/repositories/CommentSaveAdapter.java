package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import java.util.List;

import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import org.springframework.stereotype.Component;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.CommentRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantCommentModel;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;

@Component
public class CommentSaveAdapter implements SaveAdapterPort<Comment> {

	private final CommentRepository commentRepository;

	public CommentSaveAdapter(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}


	@Override
	public Comment save(Comment entity) {
		return null;
	}
}

