package sort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonReader extends XMLreader <Person> {
    public List<Person> getEntities(String fileName){
        StringBuilder sb=returnStringBuilder(fileName);
        return getListPersonByValueNew(sb);
    }

    protected List<Person> createPersonList (List<String> listFromFile){
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

    protected   Person[] readPersonsFromFile2(String fileName) {
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

    protected  List<String> readPersonsFromFile(String fileName) {
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
}

