package sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person> {
    private String name;
    private String surname;
    private int weight;
    private LocalDate dateBirth;

    public Person(){

    }
    public Person(String name,String surname, int weight, LocalDate dateBirth){
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

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    @Override
    public String toString(){
        return ("имя="+name+"; "+"фамилия="+surname+"  вес="+weight+"  ДАТА рождения="+dateBirth.toString());
    }
    @Override
    public boolean equals(Object p){
        if (p==this){
            return true;
        } else
        if ((p==null)&&(this.getClass()==p.getClass())){
            return true;
        } else
        if  (!(p instanceof Person)){
            return false;
        }
        Person p1=(Person)p;
        if (name.equals(p1.getName())&&(surname.equals(p1.getSurname()))&&(weight==p1.getWeight())&&(dateBirth.equals(p1.getDateBirth()))){

            return  true;
        }
        return false;

    }

    @Override
    public int compareTo(Person p){
        return surname.compareTo(p.surname);
    }
    public int compareNames(Person p){
        return name.compareTo(p.name);
    }



}
