package network.streams;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class StreamCreation {

    public static void main(String[] args) {
        create();
        System.out.println();
        arraysStreamCreation();
        System.out.println();
        streamOf();
        System.out.println();
        streamGenerate();
        streamIterate();

        System.out.println("----------------------");
        streamPath();
        System.out.println("----------------------");

        findFile();
    }

    public static void create() {
        List.of("This", "is", "created", "by", "List.of().stream()")
                .stream().forEach(System.out::print);
        System.out.println();
        Set.of("This", "is", "created", "by", "Set.of().stream()")
                .stream().forEach(System.out::print);
        System.out.println();
        Map.of(1, "This ", 2, "is ", 3, "built ", 4, "by ", 5,
                        "Map.of().entrySet().stream()")
                .entrySet().stream().forEach(System.out::print);
    }

    public static void arraysStreamCreation() {
        String[] array = {"That ", "is ", "an ", "Arrays.stream(array)"};
        Arrays.stream(array).forEach(System.out::print);
        System.out.println();
        String[] array1 = {"That ", "is ", "an ",
                "Arrays.stream(array,0,2)"};
        Arrays.stream(array1, 0, 2).forEach(System.out::print);
    }

    public static void streamOf() {
        String[] array = {"That ", "is ", "a ", "Stream.of(array)"};
        Stream.of(array).forEach(System.out::print);
        System.out.println();
        Stream.of("That ", "is ", "a ", "Stream.of(literals)")
                .forEach(System.out::print);
    }

    public static void streamGenerate() {
        Stream.generate(() -> "generated ")
                .limit(3).forEach(System.out::print);
        System.out.println();
    }

    public static void streamIterate() {
        System.out.print("Stream.iterate().limit(10): ");
        Stream.iterate(0, i -> i + 1)
                .limit(10).forEach(System.out::print);
        System.out.println();
        System.out.print("Stream.iterate(Predicate < 10): ");
        Stream.iterate(0, i -> i < 10, i -> i + 1)
                .forEach(System.out::print);
        System.out.println();
    }

    public static void streamPath() {
        String path = "C:\\Users\\user\\IdeaProjects\\summer-practice";
        System.out.println("Files.list(dir): ");
        Path dir = FileSystems.getDefault()
                .getPath(path);

        try (Stream<Path> stream = Files.list(dir)) {
            stream.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Files.lines().limit(3): ");
        try (Stream<String> stream = Files.lines(Paths.get(path + "\\JCookbook\\src\\main\\java\\network\\httprequests\\HttpGET.java"))
                .limit(3)) {
            stream.forEach(l -> {
                if (l.length() > 0) {
                    System.out.println(" " + l);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void findFile(){
        Path dir = FileSystems.getDefault()
                .getPath("src/main/java/network/streams/StreamCreation.java");
        BiPredicate<Path, BasicFileAttributes> select =
                (p, b) -> p.getFileName().toString().contains("Factory");
        try(Stream<Path> stream = Files.find(dir, 2, select)){
            stream.map(path -> path.getFileName())
                    .forEach(System.out::println);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
