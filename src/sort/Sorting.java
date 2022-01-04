package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorting {
    public List<Person> sort(List<Person> listPersons){
        boolean isSorted=false;
        while (!isSorted){
            Person personBuf;
            isSorted=true;
            for (int i=0;i<listPersons.size()-1;i++){
                if (listPersons.get(i).compareTo(listPersons.get(i+1))>0){
                    isSorted=false;
                    personBuf=listPersons.get(i);

                    listPersons.remove(i);
                    listPersons.add(i,listPersons.get(i));
                    listPersons.remove(i+1);
                    listPersons.add(i+1,personBuf);

                }
            }
        }
        return listPersons;
    }
    public List<Person> sort2(Person[] persons){
        boolean isSorted=false;
        while (!isSorted){
            Person personBuf;
            isSorted=true;
            for (int i=0;i<persons.length-1;i++){
                if (persons[i].compareTo(persons[i+1])>0){
                    isSorted=false;
                    personBuf=persons[i];
                    persons[i]=persons[i+1];
                    persons[i+1]=personBuf;
                }
            }
        }

        return Arrays.asList(persons);
    }

}
