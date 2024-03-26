package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateRestaurantCommentRequest;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.CreateCommentResponse;
import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateCommentUseCasePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CreateCommentUseCasePort createCommentUseCasePort;

    public CommentController(CreateCommentUseCasePort createCommentUseCasePort) {
        this.createCommentUseCasePort = createCommentUseCasePort;
    }

    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(@RequestBody CreateRestaurantCommentRequest createCommentRequest) {

        try {
            Comment comment = createCommentUseCasePort.execute(createCommentRequest.toDomain());
        }

        catch (RuntimeException ex ){
            return ResponseEntity.badRequest().body(
                    CreateCommentResponse.builder()
                            .mensagemRetorno(ex.getMessage())
                            .build());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                CreateCommentResponse.builder()
                        .mensagemRetorno("Comentário Cadastrado com sucesso!")
                        .build());
    }
}

