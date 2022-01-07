package sort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonFactory {

    public List<Person> createPersonList (List<String> listFromFile){
        List<Person> listPersons=new ArrayList<>();
        String name;
        String surName;
        int weight;
        for (String s:listFromFile){
            String [] array=s.split(" ");
            name=array[0];
            surName=array[1];
            weight=Integer.parseInt(array[2]);
            listPersons.add(new Person(name,surName,weight));
        }

        return listPersons;
    }

    public  Person[] readPersonsFromFile2(String fileName) {
        List<String> listNamesSurnames=new ArrayList<>();
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(fileName));
            String str;
            while ((str=bufferedReader.readLine())!=null){
                listNamesSurnames.add(str);
            }
        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println("нет файла");
            fileNotFoundException.printStackTrace();

        }
        catch (IOException exception){
            System.out.println("ошибка");
            exception.printStackTrace();

        }
        int size=listNamesSurnames.size();
        Person[] arrayPersons=new Person[size];
        int weight;
        for (int i=0;i<size;i++){
            String stroka=listNamesSurnames.get(i);
            String [] arrayNameSurname=stroka.split(" ");
            String name=arrayNameSurname[0];
            String surname=arrayNameSurname[1];
            weight=Integer.parseInt(arrayNameSurname[2]);
            arrayPersons[i]=new Person(name,surname,weight);
        }

        System.out.println(listNamesSurnames);
        return arrayPersons;
    }

    public static List<String> readPersonsFromFile(String fileName) {
        List<String> listNamesSurnames=new ArrayList<>();

        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(fileName));
            String str;
            while ((str=bufferedReader.readLine())!=null){
                listNamesSurnames.add(str);
            }
        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println("нет файла");
            fileNotFoundException.printStackTrace();

        }
        catch (IOException exception){
            System.out.println("ошибка");
            exception.printStackTrace();

        }
        System.out.println(listNamesSurnames);
        return listNamesSurnames;
    }

    public int  surnamesRepeat(List<Person> listPersons){
        int num=0;
        for (Person p:listPersons){
            for (int i=0;i<listPersons.size();){
                System.out.println(p.getSurname());
                System.out.println(listPersons.get(i).getSurname());
                System.out.println("i="+i);
                if (p.getSurname().equals(listPersons.get(i).getSurname())){
                    num++;
                    System.out.println("num="+num);
                    i++;
                }
            }

        }
        return num;
    }


}
