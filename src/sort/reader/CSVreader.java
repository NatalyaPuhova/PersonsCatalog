package sort.reader;
import sort.domain.Area;
import sort.domain.Customer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.util.*;

public class CSVreader extends AbstractFileReader<Customer> {

    public List<Customer> getEntities(String fileName){
        int numofColumns;
        List<Customer> customerList=new ArrayList<>();
        String str;
         try {
             BufferedReader bufferedReader=new BufferedReader(new java.io.FileReader(fileName));
             Map <Integer,String> map=new HashMap<>();

             String [] arrayStr=bufferedReader.readLine().split(",");
             numofColumns=arrayStr.length;
             System.out.println("numofColumns="+numofColumns);

             for (int i=0;i<numofColumns;i++){
                 map.put(i,arrayStr[i]);
             }
             int num=1;
             while ((str=bufferedReader.readLine())!=null){
                 String name=null;
                 String adress=null;
                 Integer age=null;
                 Area areaName=null;
                 for (int i=0;i<numofColumns;i++){
                     arrayStr=str.split(",");
                     switch (map.get(i)){
                         case "Name":
                             name=arrayStr[i];
                             break;
                         case "Prof":
                             try {
                                 areaName=Area.getEnumByCode(arrayStr[i].trim());
                             }
                             catch (IllegalArgumentException e){
                                 System.out.println("IllegalArgument"+e.getClass().getName()+"      Prof="+(num));
                                 areaName=null;
                             }
                             break;
                         case "Adress":
                             adress=arrayStr[i];
                             break;
                         case "Age":
                             try {
                                 age=Integer.parseInt(arrayStr[i].trim());
                             }
                             catch (NumberFormatException e){
                                 System.out.println("NumberFormat"+e.getClass().getName()+"  Age="+num);
                                 age=null;
                             }
                             break;
                         default:
                             System.out.println("Вы ввели неверные данные для полей");
                     }
                 }
                 customerList.add(new Customer(name,adress,age,areaName));
                 num++;
             }
         }
         catch (FileNotFoundException exception){
             System.out.println("файл не найден"+exception.getClass().getName());
         }
         catch (IOException exception){
             System.out.println("глобальная ошибка"+exception.getClass().getName());
         }
         return customerList;
    }

    public List<Customer> getEntities2 (String nameFile){
        Map<String,Integer> columns=new HashMap<>();
        List<Customer> customerList=new ArrayList<>();
        try {
            BufferedReader br=new BufferedReader(new FileReader(nameFile));
            String str=br.readLine();
            String [] arrayStr=str.split(",");
            for (int i=0;i<arrayStr.length;i++){
                columns.put(arrayStr[i].trim(),i);
            }
            while ((str=br.readLine())!=null){
                String[] arrayStrs=str.split(",");
                String adress=arrayStrs[columns.get("Adress")].trim();
                String name=arrayStrs[columns.get("Name")].trim();
                Integer age=null;
                Area areaName=null;
                try {
                    age=Integer.valueOf(arrayStrs[columns.get("Age")]);
                }
                catch (NumberFormatException exception){
                    System.out.println("NumberFormat"+exception.getClass().getName()+"age не может быть получен;  age="+arrayStrs[columns.get("Age")]);
                }
                try {
                    areaName=Area.getEnumByCode(arrayStrs[columns.get("Prof")]);
                }
                catch (IllegalArgumentException exception){
                    System.out.println("IllegalArgument"+exception.getClass().getName()+"areaName не может быть получен;  age="+arrayStrs[columns.get("Prof")]);
                }
                customerList.add(new Customer(name,adress,age,areaName));
            }
        }
        catch (FileNotFoundException exception){
            System.out.println(exception.getClass().getName());
        }
        catch (IOException exception){
            System.out.println("глобальная ошибка"+exception.getClass().getName());
        }
        return customerList;
    }
}
