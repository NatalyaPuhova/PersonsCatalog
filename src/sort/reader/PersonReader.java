package sort.reader;

import sort.domain.Person;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonReader extends AbstractXMLreader<Person> {
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

    public Person[] convertListToArray(List<Person> personList){
        Person[] arrayPersons=new Person[personList.size()];
        int i=0;
        for (Person p:personList){
            arrayPersons[i]=p;
            i++;
        }
        return arrayPersons;

    }


}

