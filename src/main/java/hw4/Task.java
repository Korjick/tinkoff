package hw4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Task {
    private Task() {

    }

    public static List<Animal> sortByHeight(Collection<Animal> animals) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    public static List<Animal> sortByWeightReversed(Collection<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::weight).reversed()).limit(k).toList();
    }

    public static Map<Animal.Type, Integer> getAnimalsByType(Collection<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(Animal::type, s -> 1, Integer::sum));
    }

    public static Animal longestName(Collection<Animal> animals) {
        return animals.stream().max(Comparator.comparingInt(o -> o.name().length())).orElse(null);
    }

    public static Animal.Sex compareAnimalSexCount(Collection<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream().max(Map.Entry.comparingByValue())
            .orElseThrow()
            .getKey();
    }

    public static Map<Animal.Type, Animal> getAnimalsByWeight(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.maxBy(Comparator.comparingInt(Animal::weight))))
            .entrySet().stream().filter(typeOptionalEntry -> typeOptionalEntry.getValue().isPresent())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                typeOptionalEntry -> typeOptionalEntry.getValue().get()
            ));
    }

    public static Animal oldestAnimal(Collection<Animal> animals) {
        return animals.stream().max(Comparator.comparingInt(Animal::age)).orElse(null);
    }

    public static Optional<Animal> maxWeightAnimal(Collection<Animal> animals, int heightLimitMax) {
        return animals.stream().filter(a -> a.height() < heightLimitMax).max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer pawsSum(Collection<Animal> animals) {
        return animals.stream().map(Animal::paws).reduce(0, Integer::sum);
    }

    public static List<Animal> comparePawsAndAge(Collection<Animal> animals) {
        return animals.stream().filter(a -> a.paws() != a.age()).toList();
    }

    public static List<Animal> canBiteAndHeightLimit(Collection<Animal> animals, boolean canBite, int heightLimitMin) {
        return animals.stream().filter(a -> a.bites() == canBite && a.height() > heightLimitMin).toList();
    }

    public static Integer countByWeightCompareHeight(Collection<Animal> animals) {
        return Math.toIntExact(animals.stream().filter(a -> a.weight() > a.height()).count());
    }

    public static List<Animal> compareNameLen(Collection<Animal> animals, int nameLenMin) {
        return animals.stream().filter(a -> a.name().split(" ").length > nameLenMin).toList();
    }

    public static Boolean compareAnimalTypeByHeight(Collection<Animal> animals, Animal.Type type, int heightMin) {
        return animals.stream().anyMatch(a -> a.type().equals(type) && a.height() > heightMin);
    }

    public static Map<Animal.Type, Integer> getAnimalsByWeightSum(Collection<Animal> animals, int ageMin, int ageMax) {
        return animals.stream()
            .filter(a -> a.age() >= ageMin && a.age() <= ageMax)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> chainSort(Collection<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type))
            .sorted(Comparator.comparing(Animal::sex))
            .sorted(Comparator.comparing(Animal::name))
            .toList();
    }

    public static Boolean biteTimeCompare(Collection<Animal> animals, Animal.Type first, Animal.Type second) {
        var animalsMap = animals.stream()
            .filter(a -> a.type().equals(first) || a.type().equals(second))
            .collect(Collectors.groupingBy(Animal::type))
            .entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().stream().filter(Animal::bites).count()));

        if (animalsMap.size() <= 1) {
            return false;
        }

        return animalsMap.get(first) > animalsMap.get(second);
    }

    public static Animal maxWeightAnimal(Animal.Type type, Collection<Animal>... animalsLists) {
        return Arrays.stream(animalsLists)
            .flatMap(Collection::stream)
            .filter(a -> a.type().equals(type))
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> validate(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(Animal::name, Animal::validate))
            .entrySet().stream()
            .filter(v -> !v.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, String> validateBeautify(Collection<Animal> animals) {
        Map<String, Set<ValidationError>> map = validate(animals);
        return map.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().stream()
                    .map(er -> "Field name: " + er.getFieldName() + ". " + "Error message: " + er.getMessage() + "\n")
                    .reduce("", (l, r) -> l + r)
            ));
    }
}
