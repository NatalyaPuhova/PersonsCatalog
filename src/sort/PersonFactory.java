package sort;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonFactory {

    public List<Person> createPersonList (List<String> listFromFile){
        List<Person> listPersons=new ArrayList<>();
        String name;
        String surName;
        int weight;
        LocalDate dateBirth;
        for (String s:listFromFile){
            String [] array=s.split(" ");
            name=array[0];
            surName=array[1];
            weight=Integer.parseInt(array[2]);
            String[] arrayDateBirth=array[3].split("-");
            int year=Integer.parseInt(arrayDateBirth[0]);
            int month=Integer.parseInt(arrayDateBirth[1]);
            int date=Integer.parseInt(arrayDateBirth[2]);

            dateBirth=LocalDate.of(year,month,date);
            listPersons.add(new Person(name,surName,weight,dateBirth));
        }

        return listPersons;
    }

    public  Person[] readPersonsFromFile2(String fileName) {
        List<String> listNamesSurnamesDates=new ArrayList<>();
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(fileName));
            String str;
            while ((str=bufferedReader.readLine())!=null){
                listNamesSurnamesDates.add(str);
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
        int size=listNamesSurnamesDates.size();
        Person[] arrayPersons=new Person[size];
        int weight;

        for (int i=0;i<size;i++){
            String stroka=listNamesSurnamesDates.get(i);
            String [] arrayNameSurnameDate=stroka.split(" ");
            String name=arrayNameSurnameDate[0];
            String surname=arrayNameSurnameDate[1];
            weight=Integer.parseInt(arrayNameSurnameDate[2]);
            LocalDate dateBirth=LocalDate.parse(arrayNameSurnameDate[3]);

            arrayPersons[i]=new Person(name,surname,weight,dateBirth);
        }

        System.out.println(listNamesSurnamesDates);
        return arrayPersons;
    }
    public  List<Person> readPersonsFromFile3XML(String fileName3) {
        String name;
        String surname;
        int weight;
        LocalDate dateBirth;

        List<Person> personList=new ArrayList<>();
        StringBuilder stringBuilder=null;
        int num=0;
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(fileName3));
            stringBuilder=new StringBuilder();
            String str;
            while ((str=bufferedReader.readLine())!=null){
                stringBuilder.append(str);
                num++;
            }
            System.out.println("stringBuilder="+stringBuilder);
        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println("нет файла");
            fileNotFoundException.printStackTrace();
        }
        catch (IOException exception){
            System.out.println("ошибка");
            exception.printStackTrace();
        }


        int start=0;
        int stop=0;
        for (int i=0;i<stringBuilder.length();i++){
            while ((start=stringBuilder.indexOf("<name>"))!=-1){
                stop=stringBuilder.indexOf("</name>");
                name=stringBuilder.substring(start+6,stop);
                stringBuilder.delete(start,stop+7);

                start=stringBuilder.indexOf("<surname>");
                stop=stringBuilder.indexOf("</surname>");
                surname=stringBuilder.substring(start+9,stop);
                stringBuilder.delete(start,stop+10);

                start=stringBuilder.indexOf("<weight>");
                stop=stringBuilder.indexOf("</weight>");
                weight=Integer.parseInt(stringBuilder.substring(start+8,stop));
                stringBuilder.delete(start,stop+9);

                start=stringBuilder.indexOf("<LocalDate>");
                stop=stringBuilder.indexOf("</LocalDate>");
                dateBirth=LocalDate.parse(stringBuilder.substring(start+11,stop));
                stringBuilder.delete(start,stop+12);

                System.out.println("name="+name);
                System.out.println("surname="+surname);
                System.out.println("weight="+weight);
                System.out.println("dateBirth="+dateBirth);
                personList.add(new Person(name,surname,weight,dateBirth));

            }

        }
        return personList;
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



}
