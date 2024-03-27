package br.com.fiap.restaurantmanagement.utils;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateRestaurantCommentRequest;
import br.com.fiap.restaurantmanagement.domain.entities.Comment;

import java.time.LocalDateTime;

public class CommentHelper {

    public static Comment createComment(){
        return new Comment(1L,"Comment Exemple",1L, LocalDateTime.now());
    }

    public static CreateRestaurantCommentRequest createCommentRequest() {
        var createCommentRequest = new CreateRestaurantCommentRequest();

        createCommentRequest.setComment("Comment exemple.");
        createCommentRequest.setRestaurantId(1L);
        createCommentRequest.setUserId(1L);

        return createCommentRequest;
    }

    public static String createCommentRequestJson(){
        return """
                {
                  "restaurantId": 1,
                  "userId": 1,
                  "comment": "Comentário teste"
                   \s
                }""";
    }
}
