package sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person> {
    private String name;
    private String surname;
    private Integer weight;
    private LocalDate dateBirth;

    public Person(){

    }
    public Person(String name,String surname, Integer weight, LocalDate dateBirth){//Integer for weight//
        this.name=name;
        this.surname=surname;
        this.weight=weight;
        this.dateBirth=dateBirth;
    }

    public void setName(String name){
        this.name=name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
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
        String rezult;
        if (dateBirth!=null){
            rezult="имя  "+name+"; "+"фамилия="+surname+"  вес="+weight+"  ДАТА рождения="+dateBirth;
        } else {

            rezult="имя "+name+"; "+"фамилия="+surname+"  вес="+weight+"  ДАТА рождения="+null;
        }
        return rezult;
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
        return name.equals(p1.getName())&&(surname.equals(p1.getSurname()))&&(weight.equals(p1.getWeight()))&&(dateBirth.equals(p1.getDateBirth()));

    }

    @Override
    public int compareTo(Person p){
        return surname.compareTo(p.surname);
    }
    public int compareNames(Person p){
        return name.compareTo(p.name);
    }



}
