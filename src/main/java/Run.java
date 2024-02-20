import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
    public static void main(String[] args) {
        // task 1
        List<String> names = Arrays.asList("John", "Bill", "Max", "Ivan");
        System.out.println(getNames(names));

    }

    public static String getNames (List<String> names){

        return names
                .stream()
                .filter((name) -> names.indexOf(name)%2 != 0)
                .map(name -> names.indexOf(name) + ". " + name)
                .collect(Collectors.joining(", "));
    }
}
