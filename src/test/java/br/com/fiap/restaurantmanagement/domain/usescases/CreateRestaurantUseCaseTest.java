package br.com.fiap.restaurantmanagement.domain.usescases;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.*;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.*;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.*;
import br.com.fiap.restaurantmanagement.domain.entities.Address;
import br.com.fiap.restaurantmanagement.domain.entities.OpeningHours;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.entities.Table;
import br.com.fiap.restaurantmanagement.domain.enumerators.DaysOfWeek;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveRestaurantAdapterPort;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateRestaurantUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateRestaurantUseCaseTest {

    private CreateRestaurantUseCase createRestaurantUseCase;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private FoodTypeRepository foodTypeRepository;

    @Mock
    private OpeningHourRepository openingHourRepository;

    @Mock
    private TableRepository tableRepository;

    private AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        SaveRestaurantAdapterPort saveRestaurantAdapterPort = new SaveRestaurantAdapter(
                restaurantRepository,
                new SaveFoodTypeAdapter(foodTypeRepository),
                new SaveAddressAdapter(addressRepository),
                new SaveTableAdapter(tableRepository),
                new SaveOpeningHourAdapter(openingHourRepository)
        );
        createRestaurantUseCase = new CreateRestaurantUseCase(saveRestaurantAdapterPort);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldCreateRestaurant() {
        // arrange
        var restaurant = createRestaurant();
        var restaurantModel = createRestaurantModel();

        when(addressRepository.saveAll(any(List.class))).thenReturn(List.of(createAddressModel()));
        when(tableRepository.saveAll(any(List.class))).thenReturn(List.of(createTableModel()));
        when(openingHourRepository.saveAll(any(List.class))).thenReturn(List.of(createOpeningHourModel()));
        when(foodTypeRepository.save(any(FoodTypeModel.class))).thenReturn(FoodTypeModel.fromDomain(TypesOfFood.BRAZILIAN, null));
        when(restaurantRepository.save(any(RestaurantModel.class))).thenReturn(restaurantModel);

        // act
        var restaurantSaved = createRestaurantUseCase.execute(restaurant);

        // assert
        assertThat(restaurantSaved).isNotNull();
        assertThat(restaurantSaved.getName()).isEqualTo(restaurant.getName());
        verify(restaurantRepository, times(1)).save(restaurantModel);
    }

    private Restaurant createRestaurant() {

        var restaurant = new Restaurant(
                "Restaurant 1",
                createAddresses(),
                TypesOfFood.BRAZILIAN,
                createOpeningHours(),
                createTables()
        );

        return restaurant;
    }

    private RestaurantModel createRestaurantModel() {
        var restaurant = new RestaurantModel();

        restaurant.setFoodType(FoodTypeModel.fromDomain(TypesOfFood.BRAZILIAN, null));
        restaurant.setName("Restaurant 1");

        return restaurant;
    }

    private List<Address> createAddresses() {
        var address = new Address(
                "Rua 1",
                "123",
                "Bairro 1",
                "Cidade 1",
                "Estado 1",
                "Pais 1",
                "CEP 1",
                "Brazil"
        );

        return List.of(address);
    }

    private AddressModel createAddressModel() {
        var address = new AddressModel();

        address.setStreet("Rua 1");
        address.setNumber("123");
        address.setNeighborhood("Bairro 1");
        address.setCity("Cidade 1");
        address.setState("Estado 1");
        address.setCountry("Pais 1");
        address.setZipCode("CEP 1");

        return address;
    }

    private List<Table> createTables() {
        var table1 = new Table("Table 1", 4);
        var table2 = new Table("Table 2", 4);
        var table3 = new Table("Table 3", 4);

        return List.of(table1, table2, table3);
    }

    private TableModel createTableModel() {
        var table = new TableModel();

        table.setDescription("Table 1");
        table.setNumberOfSeats(4);

        return table;
    }

    private List<OpeningHours> createOpeningHours() {
        var openingHour1 = new OpeningHours(DaysOfWeek.FRIDAY, "18:00", "23:00");
        var openingHour2 = new OpeningHours(DaysOfWeek.SATURDAY, "14:00", "23:59");
        var openingHour3 = new OpeningHours(DaysOfWeek.SUNDAY, "12:00", "16:00");

        return List.of(openingHour1, openingHour2, openingHour3);
    }

    private OpeningHourModel createOpeningHourModel() {
        var openingHour = new OpeningHourModel();

        openingHour.setDayOfWeek("FRIDAY");
        openingHour.setOpeningTime("18:00");
        openingHour.setClosingTime("23:00");

        return openingHour;
    }


}
