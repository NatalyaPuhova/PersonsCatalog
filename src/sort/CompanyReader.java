package sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompanyReader extends XMLreader {


    public static final String TAG_COMPANY="Company";
    public static final String TAG_COMPANY_NAME="CompanyName";

    public StringBuilder getCompanyNameByValue(StringBuilder sb){
        return  getValueByTag(sb,TAG_COMPANY_NAME);
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




}
// public StringBuilder getCompanyByValue(StringBuilder sb){
//        return  getValueByTag(sb,TAG_COMPANY);
//    }
/*
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
 */