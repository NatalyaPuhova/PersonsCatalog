package sort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PersonFactory personFactory=new PersonFactory();
        String fileName="C:\\Users\\pushinka\\Projecty\\2021\\5\\zoo\\src\\sort\\persony.txt";
        List<String> list= PersonFactory.readPersonsFromFile(fileName);

        personFactory.createPersonList(list);//
        List<Person> listPersons=personFactory.createPersonList(list);

        for (Person p:listPersons){

            System.out.println(p);
        }
        Sorting sorting=new Sorting();
        sorting.sort(listPersons);

        System.out.println("new");
        for (Person p:listPersons){

            System.out.println(p);
        }

        System.out.println("Ivan release");
        Person[] arrayPersons=personFactory.readPersonsFromFile2(fileName);
        listPersons=sorting.sort2(arrayPersons);
        for (Person p:listPersons){
            System.out.println(p);
        }
    }
}
