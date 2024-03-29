package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateCommentUseCasePort;
import br.com.fiap.restaurantmanagement.utils.CommentHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase
public class CommentControllerTest {

    @Mock
    private CreateCommentUseCasePort createCommentUseCasePort;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldPermitCreateComment() throws Exception {
        var comment = CommentHelper.createComment();
        Mockito.when(createCommentUseCasePort.execute(any(Comment.class))).thenReturn(comment);

        mockMvc.perform(MockMvcRequestBuilders.post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(CommentHelper.createCommentRequest())))
                .andExpect(status().isCreated());

    }

    public static String asJsonString(final Object restaurant) {
        try{
            return new ObjectMapper().writeValueAsString(restaurant);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
