import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Run {
    public static void main(String[] args) {

        // task 1 - імена, що стоять під непарним індексом
        List<String> names = Arrays.asList("John", "Bill", "Max", "Ivan");
        System.out.println(getNames(names));

        // task 2 - список рядків у верхньому регістрі відсортованих за спаданням
        System.out.println(getSortedNames(names));


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
                .map(name -> name.toUpperCase())
                .sorted(Comparator.reverseOrder())  // відсортованих за спаданням
                .collect(Collectors.toList());      // перетворити в список

    }
}
