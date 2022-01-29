package sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompanyReader extends XMLreader <Company> {
    public static final String TAG_COMPANY="Company";
    public static final String TAG_COMPANY_NAME="CompanyName";
    public List<Company> getEntities(String fileName){
        List<Company> companyList=new ArrayList<>();
        StringBuilder sb=readXmlFile(fileName);
        System.out.println("sb="+sb);
        int k=numRepeatWord(sb,TAG_COMPANY);
        for (int i=0;i<k;i++){
            System.out.println("i="+(i+1));
            StringBuilder stringBuilder=getValueByTag(sb,TAG_COMPANY);
            String companyName=getCompanyNameByValue(stringBuilder).toString();
            Person person=getPerson(stringBuilder);
            int stopIndex=sb.indexOf("</"+TAG_COMPANY+">");
            sb.delete(0,stopIndex+3+TAG_COMPANY.length());
            companyList.add(new Company(companyName,person));
        }
        return companyList;
    }

    private StringBuilder getCompanyNameByValue(StringBuilder sb){
        return  getValueByTag(sb,TAG_COMPANY_NAME);
    }

    private List<StringBuilder> getCompanyList(StringBuilder sb){
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
