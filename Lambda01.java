package day36lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lambda01 {

    public static void main(String[] args) {



        List<Double> myList=new ArrayList<>();
        myList.add(12.0);
        myList.add(4.0);
        myList.add(7.0);
        myList.add(3.6);
        myList.add(26.8);
        myList.add(38.4);
        myList.add(26.8);


      List<Double> half= getHalfElementsGreaterThanFiveDistinctReversed(myList);
        System.out.println(half);

        List<String>list=new ArrayList<>();
        list.add("Tom");
        list.add("Jhon");
        list.add("Ajda");
        list.add("Angelina");
        list.add("Tom");
        list.add("Brad");
        list.add("Cuneyt");

        printAllAlphabeticallyUpperDistinct(list);
        System.out.println();
        printAllReversedAlphabeticallyLowerDistinc(list);
        System.out.println();
        printAllSortWithLengthUpperDistinct(list);
        System.out.println();
        printAllSortWithLastCharhUpperDistinct1(list);
        System.out.println();
        printAllSortWithLengthUpperDistinctSameLengthsInAlphabeticalOrder(list);
        System.out.println();
//        System.out.println(removeElementIfTheLengthGreaterThanFive(list));
        System.out.println(removeElementIfStarsWithAorEndsWithd(list));
        System.out.println();
        System.out.println(printElementsLengthEven(list));
        System.out.println();
        printAllSortWithLastCharhUpperDistinct2(list);

    }

    //1)Create a method to find the half of the elements greater than 5 , distinct, in reverse order in list

    public static  List<Double>getHalfElementsGreaterThanFiveDistinctReversed(List<Double>myList){

     return   myList.stream().distinct().filter(t->t>5).map(t->t/2).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    //2)Tum list elemanlarini buyuk harfle alfabetik sirada tekrarsiz olarak yazdiriniz.

    public static void printAllAlphabeticallyUpperDistinct(List<String>list){

        list.stream().distinct().map(t->t.toUpperCase()).sorted().forEach(t-> System.out.print(t +" "));
    }

    //3)Tum list elemanlarini kucuk harfle alfabetik siranin tersinde  tekrarsiz olarak yazdiriniz.

  public static void   printAllReversedAlphabeticallyLowerDistinc(List<String> list){
      list.stream().distinct().map(t->t.toLowerCase()).sorted(Comparator.reverseOrder()).forEach(t -> System.out.print(t+" "));
}
    //4)Tum list elemanlarini buyuk harfle uzunluklarina gore artan sirada  tekrarsiz olarak yazdiriniz.

    public static void printAllSortWithLengthUpperDistinct(List<String>list){

        list.stream().distinct().map(t->t.toUpperCase()).sorted(Comparator.comparing(t->t.length())).forEach(t-> System.out.print(t+" "));
    }
//5)Tum list elemanlarini buyuk harfle son harflerine  gore azalan  sirada  tekrarsiz olarak yazdiriniz.
 // 1.yol

    public static void printAllSortWithLastCharhUpperDistinct1(List<String>list){

        list.
                stream().
                distinct().
                map(t->t.toUpperCase()).
                sorted(Comparator.comparing(t->t.charAt(t.length()-1))).
                forEach(t-> System.out.print(t + " "));

    }

    //5)Tum list elemanlarini buyuk harfle son harflerine  gore azalan  sirada  tekrarsiz olarak yazdiriniz.
//2.yol

    public static void printAllSortWithLastCharhUpperDistinct2(List<String>list){

        list.
                stream().
                distinct().
                map(String::toUpperCase).
                sorted(Comparator.comparing(Utils::getLastChar).
                        reversed()).
                forEach(Utils::printInTheSameLineWithASpace);
    }

    //6)Tum list elemanlarini buyuk   harfle uzunluklarina gore aretan sirada  tekrarsiz olarak yazdiriniz.
    //Uzunluklari ayni olan elemanlar alfabetik sirada olsunlar

    public static void printAllSortWithLengthUpperDistinctSameLengthsInAlphabeticalOrder(List<String>list){

        list.stream().distinct().
                map(t->t.toUpperCase()).//String :: toUpperCase yapisina "method reference" denir.
                sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder())).
                forEach(System.out::println);

        // map(t->t.toUpperCase()) ==> seklinde yazacagina, map(string :: toUpperCase)==> seklinde yazabilirsin. bu su anlama geliyor
        // String class'ina git, "toUpperCase" methodunu kullan
    }

    //7)Character sayisi 5 den fazla olan elemanlari siliniz
//    public static List<String>removeElementIfTheLengthGreaterThanFive(List<String>list){
//        list.removeIf(t->t.length()>5);
//
//        return list;
//    }

    //8)"A" ile baslayan veya "D" ile biten elemanlari siliniz.

    public static List<String>removeElementIfStarsWithAorEndsWithd(List<String>list) {

        list.removeIf(t -> t.startsWith("A") || t.endsWith("d"));
        return list;

        //Bazen "stream()" methodu bize lazim olan methodlara ulasmamiza engel olur. Bu yüzden "stream()" methodunu kullanmayız
//"removeIf()" methodunda olduğu gibi.

//Fakat "stream()" methodunu daha sonradan kullanmamız gerekibilir. Bu durumda "stream()" methodunu kullanarak istediğimiz methodlara
//ulasiriz, distinct() methoduna ulaştığımız gibi.

// sonuc bize list olarak lazımsa "collect(Collectors.toList()" ile sonucu List'e ceviririz.
        //
        }

    //Example 9:List elemanlarini karakter sayilarinin karelerini aliniz ve bir list olarak ekrana yazdiriniz
    public static List<Integer>printLengthSquare(List<String>list){
        return list.stream().map(Utils::getLengthSquare).collect(Collectors.toList());

    }

    //10) List elemanlarindan character sayisi cift sayi olanlari bir list icinde ekrana yazdiriniz.

    public static  List<String>printElementsLengthEven(List<String>list) {

     return    list.stream().filter(Utils::isEven).collect(Collectors.toList());

    }

    //Lambda ile kod yazarken once Javanin bize verdigi methodlari method reference icinde kullanmamiz tavsiye edilir
    //Eger method reference yetmiyorsa Util Class a koyup oradan kullaniriz
    //Util tekrar tekrar kullanacagimiz methodlarda kullanilir. Surekli kullanmayacagimiz methodlar kullanilarak Util i kalabaliklastirmak iyi degildir
    //Birseyi Util'e koydugumuzda bu herkesin isine yarayacak mi diye dusunmeliyiz!!
    }