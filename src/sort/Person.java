package sort;

import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person> {
    private String name;
    private String surname;

    public Person(){

    }
    public Person(String name,String surname){
        this.name=name;
        this.surname=surname;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    public String getSurname(){
        return surname;
    }



    @Override
    public String toString(){
        return ("имя="+name+"; "+"фамилия="+surname);
    }

    @Override
    public int compareTo(Person p){
        return surname.compareTo(p.surname);
    }
    public int compareNames(Person p){
        return name.compareTo(p.name);
    }



}
