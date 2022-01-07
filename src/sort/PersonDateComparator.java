package sort;

import java.util.Comparator;

public class PersonDateComparator implements Comparator<Person> {
    public int compare (Person p1,Person p2){
        String [] arrayYearMonthDay1=p1.getDateBirth().split("-");
        String [] arrayYearMonthDay2=p2.getDateBirth().split("-");
        double num;
        for (int i=0;i<3;i++){
            num=Integer.parseInt(arrayYearMonthDay1[i])-Integer.parseInt(arrayYearMonthDay2[i]);
            if (num<0){
                return -1;
            }
            else if (num>0){
                return 1;
            }
        }
        return 0;




    }





}

//String [] str=new String[]{p1.getDateBirth(),p2.getDateBirth()};
//String [] arrayYearMonthDay1=new String[3];
//String [] arrayYearMonthDay2=new String[3];
/*if (Integer.parseInt(arrayYearMonthDay1[0])-Integer.parseInt(arrayYearMonthDay2[0])<0){
            return -1;
        }
        else if (Integer.parseInt(arrayYearMonthDay1[0])-Integer.parseInt(arrayYearMonthDay2[0])>0){
            return 1;
        }
        else  if(Integer.parseInt(arrayYearMonthDay1[1])-Integer.parseInt(arrayYearMonthDay2[1])<0){
            return -1;
        }
        else if (Integer.parseInt(arrayYearMonthDay1[1])-Integer.parseInt(arrayYearMonthDay2[1])>0){
            return 1;
        }
        else if(Integer.parseInt(arrayYearMonthDay1[2])-Integer.parseInt(arrayYearMonthDay2[2])<0){
            return -1;
        }
        else if(Integer.parseInt(arrayYearMonthDay1[2])-Integer.parseInt(arrayYearMonthDay2[2])>0){
            return 1;
        }

         */