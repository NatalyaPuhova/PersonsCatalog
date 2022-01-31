package sort.domain;

public class Customer {
    private String name;
    private String adress;
    private Integer age;
    private Area area;


    public Customer(String name, String adress,Integer age, Area area){
        this.name=name;
        this.adress=adress;
        this.age=age;
        this.area=area;
    }

    public String toString(){
        return "name="+name+"  adress="+adress+"   age="+age+"  area="+area;
    }



}
