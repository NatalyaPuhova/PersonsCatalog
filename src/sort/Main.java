package sort;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonFactory personFactory=new PersonFactory();
        String fileName="C:\\Users\\pushinka\\Projects\\2022\\PersonsCatalog\\src\\sort\\persony.txt";
        List<String> list= PersonFactory.readPersonsFromFile(fileName);
        personFactory.createPersonList(list);//
        List<Person> listPersons=personFactory.createPersonList(list);
        for (Person p:listPersons){
            System.out.println(p);
        }
        Sorting sorting=new Sorting();
        sorting.sort(listPersons);
        System.out.println("\nnew");
        for (Person p:listPersons){
            System.out.println(p);
        }
        System.out.println("\nIvan release");
        Person[] arrayPersons=personFactory.readPersonsFromFile2(fileName);//
        Person[] copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }
        System.out.println("\n!!!!!!!!!!!!!!!arrayPersons");
        for (Person p:arrayPersons){
            System.out.println(p);
        }
        listPersons=sorting.sort2(copyArrayPersons);
        System.out.println("\nlistPersons");
        for (Person p:listPersons){
            System.out.println(p);
        }
        Comparator<Person> personComparator=new PersonWeightComparator().thenComparing(new PersonSurnameComparator());
        Collections.sort(listPersons,personComparator);
        System.out.println("\nисходный list");
        for (Person p:listPersons){
            System.out.println(p);
        }
        copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }
        sorting.sort3(copyArrayPersons,new PersonSurnameComparator());
        System.out.println("\nComparator2 copyArrayPersons по фамилии");
        for (Person p:copyArrayPersons){
            System.out.println(p);
        }
        System.out.println("\narrayPersons");
        for (Person p:arrayPersons){
            System.out.println(p);
        }
        copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }
        System.out.println("\ncopyArrayPersons");
        for (Person p:copyArrayPersons){
            System.out.println(p);
        }
        sorting.sort3(copyArrayPersons,new PersonWeightComparator());
        System.out.println("\nComparator3 по весу");
        for (Person p:copyArrayPersons){
            System.out.println(p);
        }
        copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        };
        sorting.sort3(copyArrayPersons,new PersonWeightComparator().thenComparing(new PersonSurnameComparator()));
        System.out.println("\nComparator вес, потом фамилия");
        for (Person p:copyArrayPersons){
            System.out.println(p);
        }
    }


}
