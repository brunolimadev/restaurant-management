package br.com.fiap.restaurantmanagement.domain.enumerators;

import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;

/**
 * This enum represents the access roles
 */
public enum TypesOfFood {
    FAST_FOOD,
    JAPANESE,
    ITALIAN,
    BRAZILIAN,
    MEXICAN,
    CHINESE,
    ARABIAN,
    FRENCH,
    SPANISH,
    GERMAN,
    AMERICAN,
    OTHER;

    public static TypesOfFood findByName(String name) throws FoodTypeNotFoundException {
        for (TypesOfFood type : TypesOfFood.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new FoodTypeNotFoundException("Invalid type of food");
    }
}
