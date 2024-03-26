package br.com.fiap.restaurantmanagement.domain.usescases;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.RestaurantSearchAdapter;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.AddressModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.FoodTypeModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SearchAdapterPort;
import br.com.fiap.restaurantmanagement.domain.usecases.SearchRestaurantUseCase;
import br.com.fiap.restaurantmanagement.utils.RestaurantHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class SearchRestaurantUseCaseTest {

    private SearchRestaurantUseCase searchRestaurantUseCase;

    @Mock
    EntityManager entityManager;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<AddressModel> criteriaQuery;

    @Mock
    Root<AddressModel> addressRoot;

    @Mock
    Join<AddressModel, RestaurantModel> restaurantJoin;

    @Mock
    Join<RestaurantModel, FoodTypeModel> foodTypeJoin;

    @Mock
    TypedQuery<AddressModel> query;

    private AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        SearchAdapterPort<List<Restaurant>> searchAdapterPort = new RestaurantSearchAdapter(entityManager);
        searchRestaurantUseCase = new SearchRestaurantUseCase(searchAdapterPort);
    }

    @AfterEach
    void close() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldSearchRestaurant() throws FoodTypeNotFoundException {

        // arrange
        Optional<String> location = Optional.of("São Paulo");
        Optional<String> name = Optional.of("Japa");
        Optional<String> foodType = Optional.of("Japanese");

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(AddressModel.class)).thenReturn(criteriaQuery);

        when(criteriaQuery.from(AddressModel.class)).thenReturn(addressRoot);

        doReturn(restaurantJoin).when(addressRoot).join("restaurant");
        doReturn(foodTypeJoin).when(restaurantJoin).join("foodType");

        when(entityManager.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(List.of(RestaurantHelper.createAddressModel()));

        // act
        List<Restaurant> result = searchRestaurantUseCase.execute(location, name, foodType);

        // assert
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);

    }

    @Test
    void shouldThrowAExcpetion(){

        // arrange
        Optional<String> location = Optional.of("São Paulo");
        Optional<String> name = Optional.of("Japa");
        Optional<String> foodType = Optional.of("Variado");

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(AddressModel.class)).thenReturn(criteriaQuery);

        when(criteriaQuery.from(AddressModel.class)).thenReturn(addressRoot);

        doReturn(restaurantJoin).when(addressRoot).join("restaurant");
        doReturn(foodTypeJoin).when(restaurantJoin).join("foodType");

        when(entityManager.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(List.of(RestaurantHelper.createAddressModel()));

        // assert
        assertThatThrownBy(() ->  searchRestaurantUseCase.execute(location, name, foodType))
                .isInstanceOf(FoodTypeNotFoundException.class)
                .hasMessage("Invalid type of food");
    }
}
