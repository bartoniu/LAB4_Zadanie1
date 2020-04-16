import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Zadanie1
{
    public static void main(String[] args) throws IOException {

        Stream<String> stream = Files.lines(Paths.get("dane.txt"));

        List<Person> person = stream
                .map(dane -> dane.split((" ")))
                .map(dana -> new Person(dana[0], dana[1], dana[2], Integer.parseInt(dana[3])))
                .filter(dana -> dana.getCountry().equals("PL"))
                .sorted(Comparator.comparingInt(Person::getPension))
                .collect(toList());

        System.out.println("Suma zarobkow najmniej zarabiajacych Polakow: " + (person.get(0).getPension() + person.get(1).getPension()));
        System.out.println("Suma zarobkow najwiecej zarabiajacych Polakow: " + (person.get(person.size()-1).getPension() + person.get(person.size()-2).getPension()));

        stream.close();

        Stream<String> stream2 = Files.lines(Paths.get("dane.txt"));

        List<Person> person2 = stream2
                .map(dane -> dane.split((" ")))
                .map(dana -> new Person(dana[0], dana[1], dana[2], Integer.parseInt(dana[3])))
                .collect(toList());

        Map<String, Long> countryMap = person2.stream().collect(Collectors.groupingBy(Person::getCountry, counting()));

        System.out.println(countryMap);

        stream2.close();
    }
}
