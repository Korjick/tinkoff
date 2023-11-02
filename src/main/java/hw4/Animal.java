package hw4;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"MagicNumber", "NestedIfDepth"})
public record Animal(
    @Validate
    @Size(min = 2)
    String name,
    Type type,
    Sex sex,
    @Validate
    @Size(min = 1)
    Integer age,
    @Validate
    @Size(min = 2)
    Integer height,
    @Validate
    @Size(min = 2)
    Integer weight,
    boolean bites
) {
    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
    }

    public static Set<ValidationError> validate(Animal animal) {

        Set<ValidationError> validationErrors = new HashSet<>();

        for (Field field : animal.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.isAnnotationPresent(Validate.class)) {
                    if (field.isAnnotationPresent(Size.class)) {
                        Size size = field.getAnnotation(Size.class);
                        if (field.getType().equals(Number.class)) {
                            double number = ((Number) field.get(animal)).doubleValue();
                            if (number > size.max()
                                || number < size.min()) {
                                validationErrors.add(new ValidationError("Number value should been between %d and %d"
                                    .formatted(
                                        size.min(),
                                        size.max()
                                    ), field.getName()));
                            }
                        } else if (field.getType().equals(String.class)) {
                            String string = (String) field.get(animal);
                            if (string.length() > size.max()
                                || string.length() < size.min()) {
                                validationErrors.add(new ValidationError("String length should been between %d and %d"
                                    .formatted(
                                        size.min(),
                                        size.max()
                                    ), field.getName()));
                            }
                        }
                    }
                }
            } catch (Exception ignored) {

            }
        }

        return validationErrors;
    }
}
