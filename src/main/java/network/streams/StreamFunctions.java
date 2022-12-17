package network.streams;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFunctions {

    public static void main(String[] args) {
        streamPeek();

        System.out.println("-------------------------");

        example1();
        System.out.println("----------------------------------");

        mapStream();
        System.out.println("----------------------------------");

        flatMapStream();
        System.out.println("----------------------------------");

        streamConcatenatinon();
        System.out.println("----------------------------------");

        streamReduceOptional();
        System.out.println("----------------------------------");

        streamForEach();
        System.out.println("----------------------------------");

        streamReduceExample1();
        System.out.println("----------------------------------");

        streamCollect();
        System.out.println("-----------------------------------");

        streamToCollection();
        System.out.println("-----------------------------------");
    }

    public static void streamPeek(){
        int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(i -> i % 2 == 0)
                .peek(i -> System.out.print(i))
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println();
        System.out.println("sum = " + sum);
    }

    public static void example1(){
        System.out.println("Files.lines().dropWhile().takeWhile():");
        String file = "C:\\Users\\user\\IdeaProjects\\summer-practice";
        file += "\\JCookbook\\src\\main\\java\\network\\streams\\StreamFunctions.java";
        try(Stream<String> stream = Files.lines(Paths.get(file))){
            stream.dropWhile(l ->
                            !l.contains("dropWhile().takeWhile()"))
                    .takeWhile(l -> !l.contains("} catc" + "h"))
                    .forEach(System.out::println);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void mapStream(){
        Stream.of("This", "is", "a", "Stream.of(literals)")
                .map(i -> i.contains("is"))
                .forEach(System.out::println);
        System.out.println();
    }

    public static void flatMapStream(){
        Stream.of("This", "is", "a", "Stream.of(literals)")
                .filter(s -> s.contains("Th"))
                .flatMap(s -> Pattern.compile("(?!^)").splitAsStream(s))
                .forEach(System.out::println);
        System.out.println();
    }

    public static void streamConcatenatinon(){
        Stream.concat(Stream.of(4, 5, 6), Stream.of(7, 8, 9))
                .forEach(System.out::print);
        System.out.println();

        Stream.of(Stream.of(4, 5, 6), Stream.of(7, 8, 9))
                .flatMap(Function.identity())
                .forEach(System.out::println);
    }

    public static void streamReduceOptional(){
        Stream.of(Stream.of(4, 5, 6), Stream.of(7, 8, 9), Stream.of(1, 2, 3))
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .forEach(System.out::print);
        System.out.println();
    }

    public static void streamForEach(){
        Stream.of("3","2","1").parallel().forEach(System.out::print);
        System.out.println();
        Stream.of("3","2","1").parallel().forEachOrdered(System.out::print);
        System.out.println();

        Stream.of( "That ", "is ", "a ", null, "Stream.of(literals)" )
                .map(Optional::ofNullable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(String::toString)
                .forEach(System.out::print);
        System.out.println();
    }

    public static void streamReduceExample1(){
        int sum = Stream.of(1, 2, 3).reduce((p, e) -> p + e).orElse(0);
        System.out.println("Stream.of(1,2,3).reduce(acc): " + sum);
        System.out.println();

        sum = Stream.of(1,2,3)
                .reduce((p,e) -> {
                    System.out.println(p); //prints: 1 3
                    return p + e;
                })
                .orElse(10);
        System.out.println("Stream.of(1,2,3).reduce(acc): " + sum);
        System.out.println();

        sum = Stream.of(1,2,3).reduce(0, (p,e) -> p + e);
        System.out.println("Stream.of(1,2,3).reduce(0, acc): " + sum);
        System.out.println();

        String strsum = Stream.of(1,2,3)
                .reduce("", (p,e) -> p + e.toString(), (x,y) -> x + "," + y);
        System.out.println("Stream.of(1,2,3).reduce(,acc,comb): " + strsum);
        System.out.println();

        strsum = Stream.of(1,2,3).parallel()
                .reduce("", (p,e) -> p + e.toString(), (x,y) -> x + "," + y);
        System.out.println("Stream.of(1,2,3).reduce(,acc,comb): " + strsum);

        strsum = Stream.of(1,2,3)
                .map(i -> i.toString() + ",")
                .reduce("", (p,e) -> p + e);
        System.out.println("Stream.of(1,2,3).map.reduce(,acc): "
                + strsum.substring(0, strsum.length()-1));

        strsum = Stream.of(1,2,3).parallel()
                .map(i -> i.toString() + ",")
                .reduce("", (p,e) -> p + e);
        System.out.println("Stream.of(1,2,3).map.reduce(,acc): "
                + strsum.substring(0, strsum.length()-1));
        System.out.println();
    }

    public static class Thing {
        private int someInt;
        public Thing(int i) { this.someInt = i; }
        public int getSomeInt() { return someInt; }
        public String getSomeStr() {
            return Integer.toString(someInt); }
    }

    public static void streamCollect(){
        double aa = Stream.of(1,2,3).map(Thing::new)
                .collect(Collectors.averagingInt(Thing::getSomeInt));
        System.out.println("stream(1,2,3).averagingInt(): " + aa);
        String as = Stream.of(1,2,3).map(Thing::new).map(Thing::getSomeStr)
                .collect(Collectors.joining(","));
        System.out.println("stream(1,2,3).joining(,): " + as);
        String ss = Stream.of(1,2,3).map(Thing::new).map(Thing::getSomeStr)
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println("stream(1,2,3).joining(,[,]): " + ss);
    }

    public static void streamToCollection(){
        Object[] os = Stream.of(1,2,3).toArray();
        Arrays.stream(os).forEach(System.out::print);
        System.out.println();
        String[] sts = Stream.of(1,2,3)
                .map(i -> i.toString())
                .toArray(String[]::new);
        Arrays.stream(sts).forEach(System.out::print);

    }
}
