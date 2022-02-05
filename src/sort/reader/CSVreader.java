package sort.reader;
import sort.domain.Area;
import sort.domain.Customer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class CSVreader extends FileReader{
    public void readCSV_File(String fileName){
        List<Map> listMaps=new ArrayList<>();
        List<Customer> customerList=new ArrayList<>();

        String name=null;
        String adress=null;
        Integer age=null;
        Area areaName=null;
        String str;
         try {
             BufferedReader bufferedReader=new BufferedReader(new java.io.FileReader(fileName));
             while ((str=bufferedReader.readLine())!=null){
                 Map <Integer,String> map=new HashMap<>();
                 String [] arrayStr=str.split(",");
                 map.put(0,arrayStr[0]);
                 map.put(1,arrayStr[1]);
                 map.put(2,arrayStr[2]);
                 map.put(3,arrayStr[3]);
                 listMaps.add(map);
                 name=map.get(0);
                 adress=map.get(1);
                 try {
                     age=Integer.parseInt(map.get(2));
                 }
                 catch (NumberFormatException e){
                     System.out.println("NumberFormat"+e.getClass().getName());
                 }
                 try {
                     areaName=Area.getEnumByCode(map.get(3));
                 }
                 catch (IllegalArgumentException e){
                     System.out.println("IllegalArgument"+e.getClass().getName());
                 }

                 customerList.add(new Customer(name,adress,age,areaName));
             }
         }
         catch (FileNotFoundException exception){
             System.out.println("файл не найден"+exception.getClass().getName());
         }
         catch (IOException exception){
             System.out.println("глобальная ошибка"+exception.getClass().getName());
         }


         for (int i=0;i<listMaps.size();i++){
             Map<Integer,String> map1=listMaps.get(i);
             //System.out.format("%16s%16s%16s%16s"+customerList.get(0)+customerList.get(1)+customerList.get(2)+customerList.get(3));
             System.out.println();
             for (int k=0; k<map1.size();k++){
                 //System.out.format("%16s"+map1.get(k));
                 System.out.print(map1.get(k)+"\t\t\t");

             }
             for (Map.Entry<Integer, String> m:  map1.entrySet()){
                 //System.out.println("\t"+m.getValue());
                 //System.out.format("%16s%16s%16s%16s"+m.getValue());
             }


             
         }




    }



}
