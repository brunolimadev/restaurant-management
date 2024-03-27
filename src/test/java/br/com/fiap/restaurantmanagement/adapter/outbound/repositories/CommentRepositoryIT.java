package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.CommentRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantCommentModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.UserModel;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@ActiveProfiles("test")
class CommentRepositoryIT {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void devePermirtirSalvarComentario() {
        //Arrange
        var comentario = gerarComentario();

        //Act
        var comentarioResult = commentRepository.save(comentario);

        //Assert
        assertThat(comentarioResult).isNotNull().isInstanceOf(RestaurantCommentModel.class);
        assertThat(comentarioResult.getComment()).isEqualTo(comentario.getComment());
    }


    private RestaurantCommentModel gerarComentario() {

        RestaurantCommentModel commentModel = new RestaurantCommentModel();
        commentModel.setId(1L);
        commentModel.setCreatedAt(LocalDateTime.now());
        commentModel.setUser(new UserModel(1L,"Usuario Teste","usuario@gmail.com","99999-9999"));
        commentModel.setRestaurant(new RestaurantModel(1L));
        commentModel.setComment("Comentário de teste");

        return commentModel;
    }

}