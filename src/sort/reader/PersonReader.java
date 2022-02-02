package sort.reader;

import sort.domain.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonReader extends XMLreader<Person>  {
    public List<Person> getEntities(String fileName){
        StringBuilder sb=returnStringBuilder(fileName);
        return getListPersonByValueNew(sb);
    }

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


}

