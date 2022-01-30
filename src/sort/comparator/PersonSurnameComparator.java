package sort.comparator;

import sort.domain.Person;

import java.util.Comparator;

public class PersonSurnameComparator implements Comparator<Person> {
    public int compare (Person p1, Person p2){
        return  p1.getSurname().compareTo(p2.getSurname());
    }
}
