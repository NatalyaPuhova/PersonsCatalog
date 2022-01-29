package sort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XMLreader {

    private static final String TAG_PERSON="Person";
    private static final String TAG_NAME="name";
    private static final String TAG_SURNAME="surname";
    private static final String TAG_WEIGHT="weight";
    private static final String TAG_BIRTH_DATE="LocalDate";

    public StringBuilder readXmlFile(String fileName){
        StringBuilder stringBuilder=new StringBuilder();
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(fileName));
            String str;
            while ((str=bufferedReader.readLine())!=null){
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

    public StringBuilder getValueByTag(StringBuilder stringBuilder, String tag){

        StringBuilder sb=new StringBuilder();
        String firstTag="<"+tag+">";
        String closeTag="</"+tag+">";
        int lenght=tag.length();
        int firstInd=stringBuilder.indexOf(firstTag);
        int closeInd=stringBuilder.indexOf(closeTag);
        return sb.append(stringBuilder.substring(firstInd+lenght+2,closeInd));
    }
    private String tagHooks(String tag){
        return "<"+tag+">";
    }

    public StringBuilder getPersonByValue(StringBuilder sb){
        if(sb.indexOf(tagHooks(TAG_PERSON))!=-1){
            return  getValueByTag(sb,TAG_PERSON);
        }
        return null;
    }
    public StringBuilder getNameByValue(StringBuilder sb){
        if(sb.indexOf(tagHooks(TAG_NAME))!=-1){
            return  getValueByTag(sb,TAG_NAME);
        }
        return null;
    }
    public StringBuilder getSurnameByValue(StringBuilder sb){
        if(sb.indexOf(tagHooks(TAG_SURNAME))!=-1){
            return  getValueByTag(sb,TAG_SURNAME);
        }
        return null;
    }

    public StringBuilder getWeightByValue(StringBuilder sb){

        if(sb.indexOf(tagHooks(TAG_WEIGHT))!=-1){
            return  getValueByTag(sb,TAG_WEIGHT);
        }
        return null;
    }
    public StringBuilder getDateBirthByValue(StringBuilder sb){
        if (sb.indexOf(tagHooks(TAG_BIRTH_DATE))!=-1){
            return  getValueByTag(sb,TAG_BIRTH_DATE);
        }
        return null;
    }

    public int numRepeatWord (StringBuilder sb, String word){
        String str=sb.toString();
        int num=0;
        int ind;
        int startInd=0;

        System.out.println(str.indexOf("<"+word+">"));
        while ((ind=str.indexOf("<"+word+">",startInd))!=-1){
            num++;
            startInd=startInd+ind+1;
        }
        return num;
    }
    public List<Person> getPersonByValue(String fileName){
        List<Person> personList=new ArrayList<>();
        StringBuilder stringBuilder=readXmlFile(fileName);
        //XMLreader xmLreader=new XMLreader();
        StringBuilder sbPerson=new StringBuilder();
        int num=numRepeatWord(stringBuilder,"/"+TAG_PERSON);
        for (int i=0;i<num;i++){
            sbPerson=getValueByTag(stringBuilder,TAG_PERSON);
            String name=getNameByValue(sbPerson).toString();
            String surname=getSurnameByValue(sbPerson).toString();
            Integer weight=null;
            try {
                weight=Integer.parseInt(getWeightByValue(sbPerson).toString());
            }
            catch (NumberFormatException exception){
            }

            LocalDate dateBirth=null;
            try {
                dateBirth=LocalDate.parse(getDateBirthByValue(sbPerson).toString());
            }
            catch (DateTimeException exception){
            }
            Person person=new Person(name,surname,weight,dateBirth);
            int stop=stringBuilder.indexOf("/"+TAG_PERSON)+TAG_PERSON.length()+2;
            stringBuilder.delete(0,stop);
            personList.add(person);
        }
        return personList;
    }





}
