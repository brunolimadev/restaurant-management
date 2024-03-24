package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantCommentRequest {

    private String restaurantId;

    private String userId;

    private String useName;

    private String comment;

    public Comment toDomain(){

        Comment comment = new Comment(this.useName,this.comment);

        return comment;
    }
}
