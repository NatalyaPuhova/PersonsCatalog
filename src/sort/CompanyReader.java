package sort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompanyReader {
    public static final String TAG="Company";
    public static final String TAG_PERSON="Person";
    public static final String TAG_NAME="name";
    public static final String TAG_SURNAME="surname";
    public static final String TAG_WEIGHT="weight";
    public static final String TAG_BIRTH_DATE="LocalDate";
    public static final String TAG_COMPANY="Company";
    public static final String TAG_COMPANY_NAME="CompanyName";

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

    public StringBuilder getCompanyNameByValue(StringBuilder sb){
        return  getValueByTag(sb,TAG_COMPANY_NAME);
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

    public List<StringBuilder> getCompanyList(StringBuilder sb){
        List<StringBuilder> listCompanies=new ArrayList<>();
        int num=numRepeatWord(sb,TAG_COMPANY);
        for (int i=0;i<num;i++){
            int closeIndex=sb.indexOf("</"+TAG_COMPANY+">");
            StringBuilder stringBuilder=getValueByTag(sb,TAG_COMPANY);
            sb.delete(0,closeIndex+TAG_COMPANY.length()+3);
            listCompanies.add(stringBuilder);
        }
        return  listCompanies;
    }

    private StringBuilder getNameByValue(StringBuilder sb){
        if(sb.indexOf(tagHooks(TAG_NAME))!=-1){
            return  getValueByTag(sb,TAG_NAME);
        }
        return null;
    }
    private StringBuilder getSurnameByValue(StringBuilder sb){
        if(sb.indexOf(tagHooks(TAG_SURNAME))!=-1){
            return  getValueByTag(sb,TAG_SURNAME);
        }
        return null;
    }

    private StringBuilder getWeightByValue(StringBuilder sb){
        if(sb.indexOf(tagHooks(TAG_WEIGHT))!=-1){
            return  getValueByTag(sb,TAG_WEIGHT);
        }
        return null;
    }
    private StringBuilder getDateBirthByValue(StringBuilder sb){
        if (sb.indexOf(tagHooks(TAG_BIRTH_DATE))!=-1){
            return  getValueByTag(sb,TAG_BIRTH_DATE);
        }
        return null;
    }

    public List<Company> readCompanies(StringBuilder sb2){
        List<Company> listCompanies=new ArrayList<>();
        List<StringBuilder> listSbCompanies=getCompanyList(sb2);
        for (StringBuilder sb:listSbCompanies){
            String name=null;
            String surName=null;
            Integer weight=null;
            LocalDate dateBirth=null;
            String companyName=getCompanyNameByValue(sb).toString();
            StringBuilder sbPerson=getPersonByValue(sb);
            if (sbPerson!=null){
                if (getNameByValue(sbPerson)!=null){
                    name=getNameByValue(sbPerson).toString();
                }
                if (getSurnameByValue(sbPerson)!=null){
                    surName=getSurnameByValue(sbPerson).toString();
                }
                if (getWeightByValue(sbPerson)!=null){
                    weight=Integer.parseInt(getWeightByValue(sbPerson).toString());
                }
                if (getDateBirthByValue(sbPerson)!=null){
                    dateBirth=LocalDate.parse(getDateBirthByValue(sbPerson).toString());
                }
                listCompanies.add(new Company(companyName,new Person(name,surName,weight,dateBirth)));
            }
        }
        return listCompanies;
    }

    public List<StringBuilder> returnContainByTag(StringBuilder stringBuilder){
        String firstTag="<"+TAG+">";
        String closeTag="</"+TAG+">";
        System.out.println(5);
        List<StringBuilder> liststringBuilders=new ArrayList<>();
        int startFrom=-1;
        while (stringBuilder.indexOf(firstTag,startFrom+1)!=-1&&stringBuilder.indexOf(closeTag,startFrom+1)!=-1){
            int start=stringBuilder.indexOf(firstTag,startFrom+1);
            startFrom=stringBuilder.indexOf(closeTag,startFrom+1);
            StringBuilder partOfStringbuilder=new StringBuilder();
            partOfStringbuilder.append(stringBuilder.substring(start,startFrom));
            liststringBuilders.add(partOfStringbuilder);
        }
        return liststringBuilders;
    }

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
}
// public StringBuilder getCompanyByValue(StringBuilder sb){
//        return  getValueByTag(sb,TAG_COMPANY);
//    }