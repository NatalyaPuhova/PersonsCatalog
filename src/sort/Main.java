package sort;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName="C:\\Users\\pushinka\\Projects\\2022\\PersonsCatalog\\src\\sort\\persony.txt";
        String fileName3="C:\\Users\\pushinka\\Projects\\2022\\PersonsCatalog\\src\\sort\\personXML.xml";

        PersonFactory personFactory=new PersonFactory();
        List<String> list= PersonFactory.readPersonsFromFile(fileName);

        List<Person> listPersons=personFactory.createPersonList(list);

        printListPersons(listPersons);

        Sorting sorting=new Sorting();
        sorting.sort(listPersons);
        System.out.println("\nnew");
        printListPersons(listPersons);

        Person[] arrayPersons=personFactory.readPersonsFromFile2(fileName);
        List<Person> personList=personFactory.readPersonsFromFile3XML(fileName3);
        System.out.println("!!!!!!!!!!!!!!!");
        printListPersons(personList);

        Person[] copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }

        System.out.println("\narrayPersons");
        printArrayPersons(arrayPersons);

        listPersons=sorting.sort2(copyArrayPersons);
        System.out.println("\nlistPersons");
        printListPersons(listPersons);

        Comparator<Person> personComparator=new PersonWeightComparator().thenComparing(new PersonSurnameComparator());
        Collections.sort(listPersons,personComparator);
        System.out.println("\nисходный list");
        printListPersons(listPersons);

        copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }
        sorting.sort3(copyArrayPersons,new PersonSurnameComparator());
        System.out.println("\nComparator2 copyArrayPersons по фамилии");
        printArrayPersons(copyArrayPersons);
        System.out.println("\narrayPersons");
        printArrayPersons(copyArrayPersons);

        copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }
        System.out.println("\ncopyArrayPersons");
        printArrayPersons(copyArrayPersons);
        sorting.sort3(copyArrayPersons,new PersonWeightComparator());
        System.out.println("\nComparator3 по весу");
        printArrayPersons(copyArrayPersons);
        copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }
        sorting.sort3(copyArrayPersons,new PersonWeightComparator().thenComparing(new PersonSurnameComparator()));
        System.out.println("\nComparator вес, потом фамилия");
        printArrayPersons(copyArrayPersons);

        copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }

        sorting.sort3(copyArrayPersons,new PersonDateComparator());
        System.out.println("\nComparator по ДАТЕ");
        printListPersons(listPersons);
        printArrayPersons(copyArrayPersons);

        System.out.println("\n до сортировки Comparator по ДАТЕ LAMBDA");
        for (Person p:listPersons){
            System.out.println(p);
        }
        Comparator <Person> comparatorLambda=(p1,p2)->(p1.getDateBirth().compareTo(p2.getDateBirth()));
        Collections.sort(listPersons,comparatorLambda);
        System.out.println("\nComparator по ДАТЕ LAMBDA");
        printListPersons(listPersons);

        CheckProperties checkProperties=new CheckProperties();
        LocalDate localDateNewPerson=LocalDate.of(1987,01,11);
        Person newPerson=new Person("Маша","Анина",93,localDateNewPerson);

        Person newPerson1=new Person("Маша","Анина",93,localDateNewPerson);
        Person newPerson2=new Person("Маша","Анина",93,localDateNewPerson);
        Person newPerson3=new Person("Маша","Анина",93,localDateNewPerson);

        for (Person p:listPersons){
            System.out.println("\nnnnnn"+p);
            if (p.equals(newPerson)){
                int num=(int) (Math.random()*100);
                System.out.println("num="+num);
                do {
                    num=num-list.size();
                }
                while (num>=list.size());
                System.out.println("num="+num);
                System.out.println("checkSymmetry:"+checkProperties.checkSymmetry(p,newPerson));
                System.out.println("checkReflection:"+checkProperties.checkReflection(p));
                System.out.println("checkTransitivity:"+checkProperties.checkTransitivity(newPerson1,newPerson2,newPerson3));
            }
        }
    }
    private static void printListPersons(List<Person >listPersons){
        for (Person p:listPersons){
            System.out.println(p);
        }
    }
    private static void printArrayPersons(Person [] arrayPersons){
        for (Person p:arrayPersons){
            System.out.println(p);
        }
    }

}


