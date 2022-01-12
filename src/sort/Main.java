package sort;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonFactory personFactory=new PersonFactory();
        String fileName="C:\\Users\\pushinka\\Projects\\2022\\PersonsCatalog\\src\\sort\\persony.txt";
        String fileName3="C:\\Users\\pushinka\\Projects\\2022\\PersonsCatalog\\src\\sort\\personXML.xml";
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
        Person[] arrayPersons=personFactory.readPersonsFromFile2(fileName);

        List<Person> personList=personFactory.readPersonsFromFile3XML(fileName3);
        System.out.println("!!!!!!!!!!!!!!!");
        for (Person p:personList){
            System.out.println(p);
        }



        Person[] copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }
        System.out.println("\narrayPersons");
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



        copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }

        sorting.sort3(copyArrayPersons,new PersonDateComparator());
        System.out.println("\nComparator по ДАТЕ");
        for (Person p:copyArrayPersons){
            System.out.println(p);
        }


        System.out.println("\n до сортировки Comparator по ДАТЕ LAMBDA");
        for (Person p:listPersons){
            System.out.println(p);
        }
        Comparator <Person> comparatorLambda=(p1,p2)->(p1.getDateBirth().compareTo(p2.getDateBirth()));
        Collections.sort(listPersons,comparatorLambda);
        System.out.println("\nComparator по ДАТЕ LAMBDA");
        for (Person p:listPersons){
            System.out.println(p);
        }

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


}
