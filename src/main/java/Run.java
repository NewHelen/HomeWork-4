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

        // task 3 -
        List<String> array = Arrays.asList("1, 2, 0", "4, 5");
        System.out.println(getNumbers(array));

        // task 5 - перемішати елементи зі стрімів first та second
        Stream<String> first = Arrays.asList("John", "Bill", "Max", "Ivan").stream();
        Stream<String> second = Arrays.asList("1", "5", "3").stream();
        zip(first, second).forEach(System.out::println);
    }

    public static String getNames(List<String> names) {
        return names
                .stream()
                .filter((name) -> names.indexOf(name) % 2 != 0)  // імена з непарним індексом
                .map(name -> names.indexOf(name) + ". " + name)
                .collect(Collectors.joining(", "));      // перетворити в строку
    }

    public static List <String> getSortedNames (List<String> names){
        return names
                .stream()
                .map(name -> name.toUpperCase())    // у верхньому регістрі
                .sorted(Comparator.reverseOrder())  // відсортованих за спаданням
                .collect(Collectors.toList());      // перетворити в список
    }

    public static String getNumbers (List<String> array){
       return array
                .stream()
                .flatMap(s -> Arrays.stream(s.split(", ")))
                .map(Integer::parseInt)
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        List<T> firstList = first.collect(Collectors.toList());   // перетворити в список
        List<T> secondList = second.collect(Collectors.toList());

        int size = Math.min(firstList.size(), secondList.size()); // визначити мінімальний розмір списків

        return Stream.iterate(0, i -> i < size, i -> i + 1)
                .flatMap(i -> Stream.of(firstList.get(i), secondList.get(i)));
    }
}
