package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.AddressRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.AddressModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.FoodTypeModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class RestaurantSearchAdapterTest {

    private RestaurantSearchAdapter restaurantSearchAdapter;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private EntityManager entityManager;

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

        restaurantSearchAdapter = new RestaurantSearchAdapter(
                entityManager,
                addressRepository
        );

    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldFindARestaurant() throws FoodTypeNotFoundException {

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
        var result = restaurantSearchAdapter.search(location, name, foodType);


        // assert
        verify(entityManager, times(1)).getCriteriaBuilder();
        verify(criteriaBuilder, times(1)).createQuery(AddressModel.class);
        verify(criteriaQuery, times(1)).from(AddressModel.class);
        verify(addressRoot, times(1)).join("restaurant");
        verify(restaurantJoin, times(1)).join("foodType");
        verify(entityManager, times(1)).createQuery(criteriaQuery);
        verify(query, times(1)).getResultList();

    }

}
