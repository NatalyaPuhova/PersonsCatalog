package sort.reader;

import sort.domain.Area;
import sort.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerReader extends AbstractXMLreader<Customer> {
    private static final String TAG_CUSTOMER="Customer";
    public  List<Customer> getEntities(String fileName){
        List<Customer> customerList=new ArrayList<>();
        StringBuilder sb=returnStringBuilder(fileName);
        int num=numRepeatWord(sb,TAG_CUSTOMER);
        for (int i=0;i<num;i++){
            System.out.println("i="+(i+1));
            StringBuilder sbCustomer=getCustomerByValue(sb);
            String name=getNameByValue(sbCustomer).toString();
            String adress=getAdressByValue(sbCustomer).toString();
            Integer age=null;
            try {
                age=Integer.parseInt(getAgeByValue(sbCustomer).toString().trim());
            }
            catch (NumberFormatException exception){
                System.out.println("NumberFormatException    "+ exception.getClass().getName());
            }
            catch (NullPointerException exception){
                System.out.println("NullPointerException     "+ exception.getClass().getName());
            }
            Area areaName=null;
            String str=null;

            try {
                str=getAreaByValue(sbCustomer).toString().trim();
                areaName=Area.getEnumByCode(str);
                System.out.println("areaName="+areaName);
            }
            catch (NullPointerException exception){
                System.out.println("NullPointerException       "+ exception.getClass().getName());
            }
            catch (IllegalArgumentException exception){
                System.out.println("IllegalArgumentException       "+ exception.getClass().getName());
            }
            System.out.println("age="+age);
            customerList.add(new Customer(name,adress,age,areaName));
            int indStop=sb.indexOf("/"+TAG_CUSTOMER);
            sb.delete(0,indStop+TAG_CUSTOMER.length()+3);
        }
        return customerList;
    }
}
