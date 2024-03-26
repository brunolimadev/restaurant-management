package br.com.fiap.restaurantmanagement.domain.enumerators;

/**
 * This enum represents the access roles
 */
public enum DaysOfWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    public static DaysOfWeek validateDay(String dayName) {
        try {
            return DaysOfWeek.valueOf(dayName);
        } catch (IllegalArgumentException exception) {
            throw  new IllegalArgumentException("dia não inválido");
        }
    }
}