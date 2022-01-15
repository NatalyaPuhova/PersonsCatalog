package sort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompanyReader {



    public  List<Company> returnCompanies(StringBuilder stringBuilder) {
        List<Company>listCompanies=new ArrayList<>();
        String [] arrayTags=new String[]{"name","surname","weight","LocalDate"};
        while (stringBuilder.indexOf("<Company>")!=-1){
            while (stringBuilder.indexOf("<Person>")!=-1){

                int indCompany1=stringBuilder.indexOf("<Company>")+9;
                int indCompany2=stringBuilder.indexOf("<",indCompany1+1);
                String companyName=stringBuilder.substring(indCompany1,indCompany2).trim();
                String name=null;
                String surname=null;
                int weight=Integer.parseInt("0");
                LocalDate dateBirth=null;
                for (int k=0;k<arrayTags.length;k++ ){
                    int start=stringBuilder.indexOf("<"+arrayTags[k]+">")+arrayTags[k].length()+2;
                    int stop=stringBuilder.indexOf("</"+arrayTags[k]+">");
                    String info=stringBuilder.substring(start,stop);
                    switch (arrayTags[k]){
                        case "name":
                            name=info;
                            break;
                        case "surname":
                            surname=info;
                            break;
                        case  "weight":
                            weight=Integer.parseInt(info);
                            break;
                        case "LocalDate":
                            dateBirth=LocalDate.parse(info);
                            break;
                        default:
                            System.out.println("неизвестный тег");
                    }
                }
                int ind1=stringBuilder.indexOf("<Company>");
                int ind2=stringBuilder.indexOf("</Company>");
                stringBuilder.delete(ind1,ind2+10);
                Person personHead=new Person(name,surname,weight,dateBirth);
                listCompanies.add(new Company(companyName,personHead));
            }
        }
        return listCompanies;
    }

    public StringBuilder readXmlFile(String fileName){
        List<String> listNamesSurnamesDates=new ArrayList<>();
        StringBuilder stringBuilder=new StringBuilder();
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(fileName));
            String str;
            while ((str=bufferedReader.readLine())!=null){
                listNamesSurnamesDates.add(str);
                stringBuilder.append(str);
            }
        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println("нет файла");
            fileNotFoundException.printStackTrace();
        }
        catch (IOException exception){
            System.out.println("ошибка");
            exception.printStackTrace();
        }
        return stringBuilder;
    }
}
