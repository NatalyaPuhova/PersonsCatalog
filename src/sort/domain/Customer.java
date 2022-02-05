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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String toString(){
        return "name="+name+"  adress="+adress+"   age="+age+"  area="+area;
    }



}
