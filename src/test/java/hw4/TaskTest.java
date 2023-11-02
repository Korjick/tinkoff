package hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private static final Logger LOGGER = Logger.getLogger(TaskTest.class.getName());

    private static final Animal cat = new Animal("Whiskers", Animal.Type.CAT, Animal.Sex.F, 3, 9, 12, true);
    private static final Animal dog = new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 4, 20, 30, true);
    private static final Animal bird = new Animal("Tweetie", Animal.Type.BIRD, Animal.Sex.F, 2, 5, 3, false);
    private static final Animal fish = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 3, 1, false);
    private static final Animal spider = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 1, 0, 2, false);
    private static final Animal anotherCat = new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 700, 10, true);
    private static final Animal skubidu = new Animal("Skubidubidubidu", Animal.Type.DOG, Animal.Sex.M, 5, 25, 40, true);
    private static final Animal parrot = new Animal("Polly", Animal.Type.BIRD, Animal.Sex.F, 3, 6, 0, false);
    private static final Animal goldfish = new Animal("Goldie Fish", Animal.Type.FISH, Animal.Sex.F, 1, 2, 0, false);
    private static final Animal tarantula = new Animal("C", Animal.Type.SPIDER, Animal.Sex.M, 3, 6, 0, true);

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Отсортировать животных по росту от самого маленького к самому большому")
    void sortByHeight(List<Animal> input) {
        List<Animal> result = Task.sortByHeight(input);
        input.sort(Comparator.comparingInt(Animal::height));
        assertEquals(input, result);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых")
    void sortByWeightReversed(List<Animal> input) {
        int k = 5;
        List<Animal> result = Task.sortByWeightReversed(input, k);
        input.sort(Comparator.comparingInt(Animal::weight).reversed());
        assertEquals(input.subList(0, k), result);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Сколько животных каждого вида")
    void getAnimalsByType(List<Animal> input) {
        Map<Animal.Type, Integer> result = Task.getAnimalsByType(input);
        assertEquals(5, result.size());
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("У какого животного самое длинное имя")
    void longestName(List<Animal> input) {
        Animal result = Task.longestName(input);
        assertEquals(skubidu, result);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Каких животных больше: самцов или самок")
    void compareAnimalSexCount(List<Animal> input) {
        Animal.Sex result = Task.compareAnimalSexCount(input);
        assertEquals(Animal.Sex.F, result);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Самое тяжелое животное каждого вида")
    void getAnimalsByWeight(List<Animal> input) {
        Map<Animal.Type, Animal> result = Task.getAnimalsByWeight(input);
        Map<Animal.Type, Animal> excepted = Map.of(
            Animal.Type.CAT,
            cat,
            Animal.Type.DOG,
            skubidu,
            Animal.Type.BIRD,
            bird,
            Animal.Type.FISH,
            fish,
            Animal.Type.SPIDER,
            spider
        );

        Set<Map.Entry<Animal.Type, Animal>> resultSet = result.entrySet();
        resultSet.removeAll(excepted.entrySet());

        assertEquals(Set.of(), resultSet);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("K-е самое старое животное")
    void oldestAnimal(List<Animal> input) {
        Animal result = Task.oldestAnimal(input);
        assertEquals(skubidu, result);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Самое тяжелое животное среди животных ниже k см")
    void maxWeightAnimal(List<Animal> input) {
        int k = 7;
        Optional<Animal> result = Task.maxWeightAnimal(input, k);
        result.ifPresent(r -> assertEquals(bird, r));
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Сколько в сумме лап у животных в списке")
    void pawsSum(List<Animal> input){
        Integer result = Task.pawsSum(input);
        assertEquals(36, result);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Список животных, возраст у которых не совпадает с количеством лап")
    void comparePawsAndAge(List<Animal> input) {
        List<Animal> result = Task.comparePawsAndAge(input);
        Set<Animal> resultSet = new HashSet<>(result);
        resultSet.removeAll(Set.of(cat, bird, fish, spider, anotherCat, skubidu, parrot, goldfish, tarantula));
        assertEquals(Set.of(), resultSet);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Список животных, которые могут укусить (bites == true) и рост которых превышает 100 см")
    void canBiteAndHeightLimit(List<Animal> input) {
        List<Animal> result = Task.canBiteAndHeightLimit(input, true, 100);
        assertEquals(List.of(anotherCat), result);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Сколько в списке животных, вес которых превышает рост")
    void countByWeightCompareHeight(List<Animal> input) {
        Integer result = Task.countByWeightCompareHeight(input);
        assertEquals(4, result);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Список животных, имена которых состоят из более чем двух слов")
    void compareNameLen(List<Animal> input) {
        List<Animal> result = Task.compareNameLen(input, 1);
        assertEquals(List.of(goldfish), result);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Есть ли в списке собака ростом более k см")
    void compareAnimalTypeByHeight(List<Animal> input) {
        boolean result = Task.compareAnimalTypeByHeight(input, Animal.Type.DOG, 10);
        assertEquals(true, result);

        result = Task.compareAnimalTypeByHeight(input, Animal.Type.DOG, 1000);
        assertEquals(false, result);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Найти суммарный вес животных каждого вида, которым от k до l лет")
    void getAnimalsByWeightSum(List<Animal> input) {
        Map<Animal.Type, Integer> result = Task.getAnimalsByWeightSum(input, 2, 4);
        List<Integer> resultValuesSorted = new ArrayList<>(result.values());
        resultValuesSorted.sort(Comparator.comparingInt(Integer::intValue));
        assertEquals(List.of(0, 3, 22, 30), resultValuesSorted);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Список животных, отсортированный по виду, затем по полу, затем по имени")
    void chainSort(List<Animal> input) {
        List<Animal> result = Task.chainSort(input);

        input.sort(Comparator.comparing(Animal::type));
        input.sort(Comparator.comparing(Animal::sex));
        input.sort(Comparator.comparing(Animal::name));

        assertEquals(input, result);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Правда ли, что пауки кусаются чаще, чем собаки")
    void biteTimeCompare(List<Animal> input) {
        Boolean result = Task.biteTimeCompare(input, Animal.Type.SPIDER, Animal.Type.DOG);
        assertEquals(false, result);

        result = Task.biteTimeCompare(List.of(
            new Animal("", Animal.Type.SPIDER, Animal.Sex.F, 0, 0, 0, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, false)
        ), Animal.Type.SPIDER, Animal.Type.DOG);
        assertEquals(true, result);

        result = Task.biteTimeCompare(List.of(
            new Animal("", Animal.Type.SPIDER, Animal.Sex.F, 0, 0, 0, true)
        ), Animal.Type.SPIDER, Animal.Type.DOG);
        assertEquals(false, result);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Найти самую тяжелую рыбку в 2-х или более списках")
    void maxWeightAnimalMultiple(List<Animal> input) {

        Animal heavyFish = new Animal("", Animal.Type.FISH, Animal.Sex.F, 0, 0, 5000, false);

        Animal result = Task.maxWeightAnimal(Animal.Type.FISH, input, List.of(
            new Animal("", Animal.Type.FISH, Animal.Sex.F, 0, 0, 0, true),
            heavyFish
        ));
        assertEquals(heavyFish, result);

        result = Task.maxWeightAnimal(Animal.Type.FISH, input, List.of(
            new Animal("", Animal.Type.FISH, Animal.Sex.F, 0, 0, 0, true)
        ));
        assertEquals(fish, result);
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Животные, в записях о которых есть ошибки: вернуть имя и список ошибок")
    void validate(List<Animal> input) {
        Map<String, Set<ValidationError>> result = Task.validate(input);
        assert !result.isEmpty();
    }

    @ParameterizedTest
    @MethodSource("provideAnimals")
    @DisplayName("Сделать результат предыдущего задания более читабельным: вернуть имя и названия полей с ошибками, объединенные в строку")
    void validateBeautify(List<Animal> input) {
        Map<String, String> result = Task.validateBeautify(input);
        assert !result.isEmpty();
        LOGGER.info(result.toString());
    }

    private static Stream<List<Animal>> provideAnimals() {
        List<Animal> animalList = new ArrayList<>(List.of(cat, dog, bird, fish, spider, anotherCat,
            skubidu, parrot, goldfish, tarantula
        ));
        Collections.shuffle(animalList);
        return Stream.of(animalList);
    }
}
