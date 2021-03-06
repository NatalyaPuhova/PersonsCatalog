package sort;
import sort.comparator.PersonDateComparator;
import sort.comparator.PersonSurnameComparator;
import sort.comparator.PersonWeightComparator;
import sort.domain.Company;
import sort.domain.Customer;
import sort.domain.Person;
import sort.reader.*;
import sort.sorting.Sorting;
import sort.test.CheckProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String companyXML="C:\\Users\\pushinka\\Projects\\2022\\PersonsCatalog\\src\\sort\\resourses\\company.xml";
        String personXML="C:\\Users\\pushinka\\Projects\\2022\\PersonsCatalog\\src\\sort\\resourses\\personXML.xml";
        String customerXML="C:\\Users\\pushinka\\Projects\\2022\\PersonsCatalog\\src\\sort\\resourses\\customerXML.xml";
        String fileName = "C:\\Users\\pushinka\\Projects\\2022\\PersonsCatalog\\src\\sort\\resourses\\persony.txt";
        String customersCSV = "C:\\Users\\pushinka\\Projects\\2022\\PersonsCatalog\\src\\sort\\resourses\\customers.csv";
        String customers1CSV = "C:\\Users\\pushinka\\Projects\\2022\\PersonsCatalog\\src\\sort\\resourses\\customers1.csv";


        CompanyReader companyReader = new CompanyReader();
        PersonReader personReader=new PersonReader();

        CSVreader csVreader=new CSVreader();

        System.out.println();
        System.out.println("####1 попытка чтения csv- файла");
        List<Customer> customerList1=csVreader.getEntities(customersCSV);
        //System.out.println("csVreader.getNumOfColumns="+csVreader.getNumOfColumns(customersCSV));

        for (int i=0;i<customerList1.size();i++){
            System.out.print("i="+(i+1)+"   ");
            System.out.println(customerList1.get(i));
        }

        System.out.println();
        System.out.println("####2 попытка чтения csv- файла");
        List<Customer> customerList2=csVreader.getEntities(customers1CSV);
        for (int i=0;i<customerList2.size();i++){
            System.out.print("i="+(i+1)+"   ");
            System.out.println(customerList2.get(i));
        }

        System.out.println();
        System.out.println("####3 попытка чтения csv- файла");
        List<Customer> customerList3=csVreader.getEntities2(customers1CSV);
        for (int i=0;i<customerList3.size();i++){
            System.out.print("i="+(i+1)+"   ");
            System.out.println(customerList2.get(i));
        }


        /*System.out.println("\nКоллектив нью22");//
        List<Person> personList2=personReader.getEntities(personXML);//*
        for (Person p:personList2){
            System.out.println(p);//
        }

        System.out.println("\ncompany22");//
        List<Company> personList3=companyReader.getEntities(companyXML);//*
        int k=1;
        for (Company c:personList3){
            System.out.println("\nk="+k);
            System.out.println(c);//
            k++;
        }

        System.out.println("!!!!!!!!!!\nCustomer");//
        CustomerReader customerReader=new CustomerReader();
        List<Customer> customerList=customerReader.getEntities(customerXML);
        int m=1;
        for (Customer c:customerList){
            System.out.println("\nm="+m);
            System.out.println(c);
            m++;
        }
           */
        TXTreader txTreader=new TXTreader();

        PersonReader personReader1 = new PersonReader();
        //List<String> list = personReader1.getEntities(fileName);//
        System.out.println("\nКоллектив");

        List<String> list=new ArrayList<>();
        list=txTreader.readPersonsFromFile(fileName);

        List<Person> listPersons=personReader1.createPersonList(list);
        printListPersons(listPersons);

        Sorting sorting=new Sorting();
        sorting.sort(listPersons);
        System.out.println("\nnew");
        printListPersons(listPersons);


        List<Person> personList=personReader1.getEntities(fileName);
        System.out.println("personList.size()="+personList.size());
        System.out.println("!!!!!!!!!!!!!!!");

        Person[]arrayPersons=txTreader.readPersonsFromFile2(fileName);//
        for (Person p:arrayPersons){
            System.out.println("3"+p);
        }


        Person[] copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }

        System.out.println("\narrayPersons");
        printArrayPersons(arrayPersons);

        listPersons=sorting.sort2(copyArrayPersons);
        System.out.println("\nlistPersons");
        printListPersons(listPersons);

        Comparator<Person> personComparator=new PersonWeightComparator().thenComparing(new PersonSurnameComparator());
        Collections.sort(listPersons,personComparator);
        System.out.println("\nисходный list");
        printListPersons(listPersons);

        copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }
        sorting.sort3(copyArrayPersons,new PersonSurnameComparator());
        System.out.println("\nComparator2 copyArrayPersons по фамилии");
        printArrayPersons(copyArrayPersons);
        System.out.println("\narrayPersons");
        printArrayPersons(copyArrayPersons);

        copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }
        System.out.println("\ncopyArrayPersons");
        printArrayPersons(copyArrayPersons);
        sorting.sort3(copyArrayPersons,new PersonWeightComparator());
        System.out.println("\nComparator3 по весу");
        printArrayPersons(copyArrayPersons);
        copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }
        sorting.sort3(copyArrayPersons,new PersonWeightComparator().thenComparing(new PersonSurnameComparator()));
        System.out.println("\nComparator вес, потом фамилия");
        printArrayPersons(copyArrayPersons);

        copyArrayPersons=new Person[arrayPersons.length];
        for (int i=0; i< arrayPersons.length;i++){
            copyArrayPersons[i]=arrayPersons[i];
        }

        sorting.sort3(copyArrayPersons,new PersonDateComparator());
        System.out.println("\nComparator по ДАТЕ");
        printListPersons(listPersons);
        printArrayPersons(copyArrayPersons);

        System.out.println("\n до сортировки Comparator по ДАТЕ LAMBDA");
        for (Person p:listPersons){
            System.out.println(p);
        }
        Comparator <Person> comparatorLambda=(p1,p2)->(p1.getDateBirth().compareTo(p2.getDateBirth()));
        Collections.sort(listPersons,comparatorLambda);
        System.out.println("\nComparator по ДАТЕ LAMBDA");
        printListPersons(listPersons);

        CheckProperties checkProperties=new CheckProperties();
        LocalDate localDateNewPerson=LocalDate.of(1987,01,11);

        Person newPerson=new Person("Маша","Анина",93,localDateNewPerson);

        Person newPerson1=new Person("Маша","Анина",93,localDateNewPerson);
        Person newPerson2=new Person("Маша","Анина",93,localDateNewPerson);
        Person newPerson3=new Person("Маша","Анина",93,localDateNewPerson);


        for (Person p:listPersons){
            System.out.println("\nnnnnn"+p);
            if (p.equals(newPerson)){
                int num=(int) (Math.random()*100);
                System.out.println("num="+num);
                do {
                    num=num-list.size();
                }
                while (num>=list.size());
                System.out.println("num="+num);
                System.out.println("checkSymmetry:"+checkProperties.checkSymmetry(p,newPerson));
                System.out.println("checkReflection:"+checkProperties.checkReflection(p));
                System.out.println("checkTransitivity:"+checkProperties.checkTransitivity(newPerson1,newPerson2,newPerson3));
            }
        }
    }
    private static void printListPersons(List<Person >listPersons){
        for (Person p:listPersons){
            System.out.println(p);
        }
    }
    private static void printArrayPersons(Person [] arrayPersons){
        for (Person p:arrayPersons){
            System.out.println(p);
        }
    }

}
/*
System.out.println("+++++++++++");
        StringBuilder sb2 = companyReader.readXmlFile("C:\\Users\\pushinka\\Projects\\2022\\PersonsCatalog\\src\\sort\\resourses\\company.xml");
        List<Company> listCompaniesNew = companyReader.readCompanies(sb2);
        for (Company c : listCompaniesNew) {
            System.out.println(c);
        }
 */
/*
 System.out.println("\nКоллектив нью");
        StringBuilder sb3=personReader.returnStringBuilder(personXML);
        List<Person> personList=personReader.getListPersonByValueNew(sb3);
        for (Person p:personList){
            System.out.println(p);//
        }
 */


