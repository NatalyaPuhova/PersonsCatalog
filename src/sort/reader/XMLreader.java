package sort.reader;

import sort.domain.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public abstract class XMLreader <T> {

    private static final String TAG_PERSON="Person";
    private static final String TAG_NAME="name";
    private static final String TAG_SURNAME="surname";
    private static final String TAG_WEIGHT="weight";
    private static final String TAG_BIRTH_DATE="LocalDate";
    private static final String TAG_ADRESS="adress";
    private static final String TAG_AGE="age";
    private static final String TAG_CUSTOMER="Customer";
    private static final String TAG_AREA="area";

    public abstract List<T> getEntities(String fileName);//////////////////////////////

    protected StringBuilder readXmlFile(String fileName){
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

    protected StringBuilder getValueByTag(StringBuilder stringBuilder, String tag){

        StringBuilder sb=new StringBuilder();
        String firstTag="<"+tag+">";
        String closeTag="</"+tag+">";
        int lenght=tag.length();
        int firstInd=stringBuilder.indexOf(firstTag);
        int closeInd=stringBuilder.indexOf(closeTag);
        try {
            sb.append(stringBuilder.substring(firstInd+lenght+2,closeInd));
        }
        catch (StringIndexOutOfBoundsException exception){
            System.out.println("не задано"+"; tag="+tag);
        }
        return sb;
    }
    private String tagHooks(String tag){
        return "<"+tag+">";
    }

    protected StringBuilder getPersonByValue(StringBuilder sb){
        if(sb.indexOf(tagHooks(TAG_PERSON))!=-1){
            return  getValueByTag(sb,TAG_PERSON);
        }
        return null;
    }
    protected StringBuilder getNameByValue(StringBuilder sb){
        if(sb.indexOf(tagHooks(TAG_NAME))!=-1){
            return  getValueByTag(sb,TAG_NAME);
        }
        return null;
    }
    protected StringBuilder getSurnameByValue(StringBuilder sb){
        if(sb.indexOf(tagHooks(TAG_SURNAME))!=-1){
            return  getValueByTag(sb,TAG_SURNAME);
        }
        return null;
    }
    protected StringBuilder getCustomerByValue(StringBuilder sb){
        if(sb.indexOf(tagHooks(TAG_CUSTOMER))!=-1){
            return  getValueByTag(sb,TAG_CUSTOMER);
        }
        return null;
    }

    protected StringBuilder getWeightByValue(StringBuilder sb){

        if(sb.indexOf(tagHooks(TAG_WEIGHT))!=-1){
            return  getValueByTag(sb,TAG_WEIGHT);
        }
        return null;
    }
    protected StringBuilder getAgeByValue(StringBuilder sb){

        if(sb.indexOf(tagHooks(TAG_AGE))!=-1){
            return  getValueByTag(sb,TAG_AGE);
        }
        return null;
    }
    protected StringBuilder getDateBirthByValue(StringBuilder sb){
        if (sb.indexOf(tagHooks(TAG_BIRTH_DATE))!=-1){
            return  getValueByTag(sb,TAG_BIRTH_DATE);
        }
        return null;
    }
    protected StringBuilder getAdressByValue(StringBuilder sb){
        if(sb.indexOf(tagHooks(TAG_ADRESS))!=-1){
            return  getValueByTag(sb,TAG_ADRESS);
        }
        return null;
    }
    public StringBuilder getAreaByValue(StringBuilder sb){/////////////////proteted
        if(sb.indexOf(tagHooks(TAG_AREA))!=-1){
            return  getValueByTag(sb,TAG_AREA);
        }
        return null;
    }

    protected int numRepeatWord (StringBuilder sb, String word){
        String str=sb.toString();
        int num=0;
        int ind;
        int startInd=0;
        while ((ind=str.indexOf("<"+word+">",startInd))!=-1){
            num++;
            startInd=startInd+ind;
        }
        return num;
    }



    protected StringBuilder returnStringBuilder(String fileName){
        return readXmlFile(fileName);
    }


    protected Person getPerson(StringBuilder sb){
        //int num=numRepeatWord(sb,"/"+TAG_PERSON);
        StringBuilder sbPerson=getValueByTag(sb,TAG_PERSON);
        String name=null;
        try {
            name=getNameByValue(sbPerson).toString();
        }
        catch (NullPointerException exception){
            System.out.println("  name -Не введено");
        }

        String surname=null;
        try {
            surname=getSurnameByValue(sbPerson).toString();
        }
        catch (NullPointerException exception){
            System.out.println("  surname -Не введено");
        }

        Integer weight=null;
        try {
            weight=Integer.parseInt(getWeightByValue(sbPerson).toString());
        }
        catch (NumberFormatException exception){
            System.out.println(name+"  "+surname+"  weight -Неверный формат данных");
        }
        catch (NullPointerException exception){
            System.out.println("  weight -Не введено");
        }

        LocalDate dateBirth=null;
        try {
            dateBirth=LocalDate.parse(getDateBirthByValue(sbPerson).toString());
        }
        catch (DateTimeParseException exception){
            System.out.println(name+"  "+surname+"  dateBirth-Неверный формат данных");
        }
        catch (NullPointerException exception){
            System.out.println("  dateBirth -Не введено");
        }
        Person person=new Person(name,surname,weight,dateBirth);
        int stop=sb.indexOf("/"+TAG_PERSON)+TAG_PERSON.length()+2;
        sb.delete(0,stop);
        return person;

    }

    protected List<Person> getListPersonByValueNew(StringBuilder sb){
        List<Person> personList=new ArrayList<>();
        int num=numRepeatWord(sb,TAG_PERSON);
        for (int i=0;i<num;i++){
            personList.add(getPerson(sb));
        }
        return personList;
    }





}
