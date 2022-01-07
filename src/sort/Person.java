package sort;

import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person> {
    private String name;
    private String surname;
    private int weight;
    private String dateBirth;

    public Person(){

    }
    public Person(String name,String surname, int weight, String dateBirth){
        this.name=name;
        this.surname=surname;
        this.weight=weight;
        this.dateBirth=dateBirth;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    @Override
    public String toString(){
        return ("имя="+name+"; "+"фамилия="+surname+"  вес="+weight+"  ДАТА рождения="+dateBirth);
    }

    @Override
    public int compareTo(Person p){
        return surname.compareTo(p.surname);
    }
    public int compareNames(Person p){
        return name.compareTo(p.name);
    }



}
