package sort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonReader {
    private  static StringBuilder stringBuilder=new StringBuilder();
    private static String str;

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
            String[] arrayNameSurnameDate = stroka.split(" ");
            String name = arrayNameSurnameDate[0];
            String surname = arrayNameSurnameDate[1];
            weight = Integer.parseInt(arrayNameSurnameDate[2]);
            LocalDate dateBirth = LocalDate.parse(arrayNameSurnameDate[3]);

            arrayPersons[i] = new Person(name, surname, weight, dateBirth);
        }

        System.out.println(listNamesSurnamesDates);
        return arrayPersons;
    }


    public  List<Person> readPersonsFromFile3XML(String fileName3) {
        String [] arrayTags=new String[]{"name","surname","weight","LocalDate"};
        List<Person> personList=new ArrayList<>();
        String name=null;
        String surname=null;
        int weight=0;
        LocalDate dateBirth=null;
        bufferReadFromFile(fileName3);
        for (int i=stringBuilder.indexOf("<Person>");i<stringBuilder.indexOf("</Person>") ;i++){
            for (int j=0;j<arrayTags.length;j++){
                int start=stringBuilder.indexOf("<"+arrayTags[j]+">",i);
                int stop=stringBuilder.indexOf("</"+arrayTags[j]+">",i);
                String info=stringBuilder.substring(start+arrayTags[j].length()+2,stop);

                switch (arrayTags[j]){
                    case "name":
                        name=info;
                        break;
                    case "surname":
                        surname=info;
                        break;
                    case "weight":
                        weight=Integer.parseInt(info);
                        break;
                    case "LocalDate":
                        dateBirth=LocalDate.parse(info);
                        break;
                    default:
                        System.out.println("что-то неизвестное");
                }
                stringBuilder.delete(start,stop+arrayTags[j].length()+3);
            }
            personList.add(new Person(name,surname,weight,dateBirth));
            int n1=stringBuilder.indexOf("<Person>");
            int n2=stringBuilder.indexOf("</Person>")+9;
            stringBuilder.delete(n1,n2);
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

        return listNamesSurnames;
    }


    private  static StringBuilder bufferReadFromFile(String fileName3){
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(fileName3));
            while ((str=bufferedReader.readLine())!=null){
                stringBuilder.append(str);
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
        return stringBuilder;
    }


}

