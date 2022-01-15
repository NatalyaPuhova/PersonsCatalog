package sort;


public class Company {
    private String companyName;
    private Person personHead;


    public Company(){

    }

    public Company(String companyName, Person personHead){
        this.companyName=companyName;
        this.personHead=personHead;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Person getPersonHead() {
        return personHead;
    }

    public void setPersonHead(Person personHead) {
        this.personHead = personHead;
    }
    @Override
    public String toString(){
        return "компания:"+companyName+" её глава="+personHead;
    }




















}
