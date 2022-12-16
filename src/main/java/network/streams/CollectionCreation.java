package network.streams;

import java.util.*;
import static java.util.Map.entry;

public class CollectionCreation {
    class A{}
    class B extends A{}

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("This ");
        list.add("is ");
        list.add("add ");
        list.add("by ");
        list.add("list.add() ");
        list.forEach(System.out::print);
        System.out.println();
        //-------------------------------------
        Arrays.asList("This ", "is ", "build ", "by ", "Arrays.asList()")
                .forEach(System.out::print);
        System.out.println("-------------------------------------");


        //-------------------------------------

        Set<String> set = new HashSet<>();
        set.add("This ");
        set.add("is ");
        set.add("build ");
        set.add("by ");
        set.add("set.add() ");
        set.forEach(System.out::print);
        System.out.println();
        //-------------------------------------
        new HashSet<String>(Arrays.asList("This ", "is ", "created ", "by ", "new HashSet(Arrays.asList()) "))
                .forEach(System.out::print);
        System.out.println("-------------------------------------");

    //-------------------------------------
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "This ");
        map.put(2, "is ");
        map.put(3, "build ");
        map.put(4, "by ");
        map.put(5, "map.put() ");
        map.entrySet().forEach(System.out::print);
        System.out.println("-------------------------------------");


        of();
        System.out.println("-------------------------------------");

        try{
            collectionImmutability();
        }catch (UnsupportedOperationException e){
            System.out.println(e.getStackTrace());
        }
        System.out.println("-------------------------------------");

        try{
            nullCheck();
        }catch (NullPointerException e){
            System.out.println(e.getStackTrace());
        }
        System.out.println("-------------------------------------");

        copyOfCollection();
        System.out.println("-------------------------------------");

        CollectionCreation sc = new CollectionCreation();
        List<A> listA = Arrays.asList(sc.new B(), sc.new B());
        Set<A> setA = new HashSet<>(listA);
        List<B> listB = Arrays.asList(sc.new B(), sc.new B());
        setA = new HashSet<>(listB);
        //List<B> listB = Arrays.asList(new A(), new A()); //compiler error
        //Set<B> setB = new HashSet<>(listA); //compiler erro

        System.out.println("-------------------------------------");

    }

    public static void of(){
        List.of("This ", "is ", "created ", "by ", "List.of()")
                .forEach(System.out::print);
        System.out.println();
        Set.of("This ", "is ", "created ", "by ", "Set.of() ")
                .forEach(System.out::print);
        System.out.println();
        Map.of(1, "This ", 2, "is ", 3, "built ", 4, "by ", 5,"Map.of() ")
                .entrySet().forEach(System.out::print);
        ofEntries();
    }

    public static void ofEntries(){
        Map.ofEntries(
                entry(1, "This "),
                entry(2, "is "),
                entry(3, "built "),
                entry(4, "by "),
                entry(5, "Map.ofEntries() ")
        ).entrySet().forEach(System.out::print);
    }

    public static void collectionImmutability(){
        List<String> list = List.of("This ", "is ", "immutable");
        list.add("Is it?"); //throws UnsupportedOperationException
        list.set(1, "is not "); //throws UnsupportedOperationException
    }

    public static void nullCheck(){
        // List.of() method does not accept a null element
        List<String> list = List.of("This ", "is ", "not ", "created ", null);
    }

    public static void copyOfCollection(){
        List<Integer> list = Arrays.asList(1,2,3);
        list = List.copyOf(list);
        //list.set(1, 0); //UnsupportedOperationException
        //list.remove(1); //UnsupportedOperationException

        Set<Integer> setInt = Set.copyOf(list);
        //setInt.add(42); //UnsupportedOperationException
        //setInt.remove(3); //UnsupportedOperationException

        Set<String> set = new HashSet<>(Arrays.asList("a","b","c"));
        set = Set.copyOf(set);
        //set.add("d"); //UnsupportedOperationException
        //set.remove("b"); //UnsupportedOperationException

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one ");
        map.put(2, "two ");
        map = Map.copyOf(map);
        //map.remove(2); //UnsupportedOperationException
        //map.put(3, "three "); //UnsupportedOperationException
    }
}
