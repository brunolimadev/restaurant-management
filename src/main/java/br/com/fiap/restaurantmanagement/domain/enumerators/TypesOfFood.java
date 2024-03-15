package br.com.fiap.restaurantmanagement.domain.enumerators;

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

    public static TypesOfFood findByName(String name){
        for (TypesOfFood type : TypesOfFood.values()) {
            if (type.name().equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid type of food");
    }
}
