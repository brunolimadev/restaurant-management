package br.com.fiap.restaurantmanagement.utils;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateRestaurantAddressRequest;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateRestaurantOpeningHoursRequest;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateRestaurantRequest;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateRestaurantTableRequest;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.*;
import br.com.fiap.restaurantmanagement.domain.entities.Address;
import br.com.fiap.restaurantmanagement.domain.entities.OpeningHours;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.entities.Table;
import br.com.fiap.restaurantmanagement.domain.enumerators.DaysOfWeek;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;

import java.util.List;

public class RestaurantHelper {

    public static Restaurant createRestaurant() {

        var restaurant = new Restaurant(
                "Restaurant 1",
                createAddresses(),
                TypesOfFood.BRAZILIAN,
                createOpeningHours(),
                createTables()
        );

        return restaurant;
    }

    public static RestaurantModel createRestaurantModel() {
        var restaurant = new RestaurantModel();

        restaurant.setFoodType(FoodTypeModel.fromDomain(TypesOfFood.BRAZILIAN, null));
        restaurant.setName("Restaurant 1");

        return restaurant;
    }

    public static List<Address> createAddresses() {
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

    public static AddressModel createAddressModel() {
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

    public static List<Table> createTables() {
        var table1 = new Table("Table 1", 4);
        var table2 = new Table("Table 2", 4);
        var table3 = new Table("Table 3", 4);

        return List.of(table1, table2, table3);
    }

    public static TableModel createTableModel() {
        var table = new TableModel();

        table.setDescription("Table 1");
        table.setNumberOfSeats(4);

        return table;
    }

    public static List<OpeningHours> createOpeningHours() {
        var openingHour1 = new OpeningHours(DaysOfWeek.FRIDAY, "18:00", "23:00");
        var openingHour2 = new OpeningHours(DaysOfWeek.SATURDAY, "14:00", "23:59");
        var openingHour3 = new OpeningHours(DaysOfWeek.SUNDAY, "12:00", "16:00");

        return List.of(openingHour1, openingHour2, openingHour3);
    }

    public static OpeningHourModel createOpeningHourModel() {
        var openingHour = new OpeningHourModel();

        openingHour.setDayOfWeek("FRIDAY");
        openingHour.setOpeningTime("18:00");
        openingHour.setClosingTime("23:00");

        return openingHour;
    }

    public static CreateRestaurantRequest createRestaurantRequest() {
        var createRestaurantRequest = new CreateRestaurantRequest();

        createRestaurantRequest.setName("Restaurant 1");
        createRestaurantRequest.setFoodType("BRAZILIAN");
        createRestaurantRequest.setAddress(List.of(createCreateRestaurantAddressRequest()));
        createRestaurantRequest.setTables(List.of(createCreateRestaurantTableRequest()));
        createRestaurantRequest.setOpeningHours(List.of(createCreateRestaurantOpeningHoursRequest()));

        return createRestaurantRequest;
    }

    public static CreateRestaurantAddressRequest createCreateRestaurantAddressRequest() {
        var createRestaurantAddressRequest = new CreateRestaurantAddressRequest();

        createRestaurantAddressRequest.setStreet("Rua 1");
        createRestaurantAddressRequest.setNumber("123");
        createRestaurantAddressRequest.setNeighborhood("Bairro 1");
        createRestaurantAddressRequest.setCity("Cidade 1");
        createRestaurantAddressRequest.setState("Estado 1");
        createRestaurantAddressRequest.setCountry("Pais 1");
        createRestaurantAddressRequest.setZipCode("CEP 1");

        return createRestaurantAddressRequest;
    }

    public static CreateRestaurantTableRequest createCreateRestaurantTableRequest() {
        var createRestaurantTableRequest = new CreateRestaurantTableRequest();

        createRestaurantTableRequest.setDescription("Table 1");
        createRestaurantTableRequest.setNumberOfSeats(4);

        return createRestaurantTableRequest;
    }

    public static CreateRestaurantOpeningHoursRequest createCreateRestaurantOpeningHoursRequest() {
        var createRestaurantOpeningHoursRequest = new CreateRestaurantOpeningHoursRequest();

        createRestaurantOpeningHoursRequest.setDayOfWeek(DaysOfWeek.FRIDAY);
        createRestaurantOpeningHoursRequest.setOpeningTime("18:00");
        createRestaurantOpeningHoursRequest.setClosingTime("23:00");

        return createRestaurantOpeningHoursRequest;
    }

    public static String createRestaurantRequestJson(){
        return "{\n" +
                "    \"name\": \"Dona Chica\",\n" +
                "    \"foodType\": \"BRAZILIAN\",\n" +
                "    \"address\": [{\n" +
                "        \"street\": \"Av. Paulista\",\n" +
                "        \"number\": \"1010\",\n" +
                "        \"complement\": \"\",\n" +
                "        \"neighborhood\": \"centro\",\n" +
                "        \"city\": \"São Paulo\",\n" +
                "        \"state\": \"SP\",\n" +
                "        \"zipCode\": \"10001-005\",\n" +
                "        \"country\": \"Brazil\"\n" +
                "    }],\n" +
                "    \"tables\": [\n" +
                "        {\n" +
                "            \"description\": \"1\",\n" +
                "            \"numberOfSeats\": 10\n" +
                "        },\n" +
                "        {\n" +
                "            \"description\": \"2\",\n" +
                "            \"numberOfSeats\": 4\n" +
                "        },\n" +
                "        {\n" +
                "            \"description\": \"3\",\n" +
                "            \"numberOfSeats\": 6\n" +
                "        }\n" +
                "    ],\n" +
                "    \"openingHours\": [\n" +
                "        {\n" +
                "            \"dayOfWeek\": \"SATURDAY\",\n" +
                "            \"openingTime\": \"10:00\",\n" +
                "            \"closingTime\": \"22:00\"\n" +
                "\n" +
                "        },\n" +
                "        {\n" +
                "            \"dayOfWeek\": \"SUNDAY\",\n" +
                "            \"openingTime\": \"10:00\",\n" +
                "            \"closingTime\": \"22:00\"\n" +
                "        }\n" +
                "    ]\n" +
                "    \n" +
                "}";
    }
}
