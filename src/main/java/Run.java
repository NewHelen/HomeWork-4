import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Run {
    public static void main(String[] args) {

        // task 1 - імена, що стоять під непарним індексом
        List<String> names = Arrays.asList("John", "Bill", "Max", "Ivan");
        System.out.println(getNames(names));

        // task 2 - список рядків у верхньому регістрі відсортованих за спаданням
        System.out.println(getSortedNames(names));

        // task 3 - отримати з масиву всі числа, і вивести їх у відсортованому вигляді через кому
        List<String> array = Arrays.asList("1, 2, 0", "4, 5");
        System.out.println(getNumbers(array));

        // task 4
        long seed = 42;
        long a = 25214903917L;
        long c = 11L;
        long m = (long) Math.pow(2, 48);

        Stream<Long> randomStream = generateRandomStream(a, c, m, seed);
        randomStream.limit(10).forEach(System.out::println); // перші 10 випадкових чисел

        // task 5 - перемішати елементи зі стрімів first та second
        Stream<String> first = Arrays.asList("John", "Bill", "Max", "Ivan").stream();
        Stream<String> second = Arrays.asList("1", "5", "3").stream();
        zip(first, second).forEach(System.out::println);
    }

    // task 1
    public static String getNames(List<String> names) {
        return names
                .stream()
                .filter((name) -> names.indexOf(name) % 2 != 0)  // імена з непарним індексом
                .map(name -> names.indexOf(name) + ". " + name)
                .collect(Collectors.joining(", "));      // перетворити в строку
    }

    // task 2
    public static List <String> getSortedNames (List<String> names){
        return names
                .stream()
                .map(name -> name.toUpperCase())    // у верхньому регістрі
                .sorted(Comparator.reverseOrder())  // відсортованих за спаданням
                .collect(Collectors.toList());      // перетворити в список
    }

    // task 3
    public static String getNumbers (List<String> array){
       return array
                .stream()
                .flatMap(s -> Arrays.stream(s.split(", ")))
                .map(Integer::parseInt)
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    // task 4
    public static Stream<Long> generateRandomStream(long a, long c, long m, long seed) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }

    // task 5
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        List<T> firstList = first.collect(Collectors.toList());   // перетворити в список
        List<T> secondList = second.collect(Collectors.toList());

        int size = Math.min(firstList.size(), secondList.size()); // визначити мінімальний розмір списків

        return Stream.iterate(0, i -> i < size, i -> i + 1)
                .flatMap(i -> Stream.of(firstList.get(i), secondList.get(i)));
    }
}
