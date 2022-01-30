package sort.comparator;

import sort.domain.Person;

import java.util.Comparator;

public class PersonDateComparator implements Comparator<Person> {
    public int compare (Person p1,Person p2){
        return p1.getDateBirth().compareTo(p2.getDateBirth());

    }
}
