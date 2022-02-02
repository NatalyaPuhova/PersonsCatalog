package sort.reader;

import sort.domain.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TXTreader extends FileReader{
    public    Person[] readPersonsFromFile2(String fileName) {
        List<String> listNamesSurnamesDates=new ArrayList<>();
        try {
            BufferedReader bufferedReader=new BufferedReader(new java.io.FileReader(fileName));
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

    public List<String> readPersonsFromFile(String fileName) {
        List<String> listNamesSurnames=new ArrayList<>();
        try {
            BufferedReader bufferedReader=new BufferedReader(new java.io.FileReader(fileName));
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
