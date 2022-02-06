package sort.reader;

import sort.domain.Company;
import sort.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class CompanyReader extends AbstractXMLreader<Company> {
    public static final String TAG_COMPANY="Company";
    public static final String TAG_COMPANY_NAME="CompanyName";
    public List<Company> getEntities(String fileName){
        List<Company> companyList=new ArrayList<>();
        StringBuilder sb=readFile(fileName);
        int k=numRepeatWord(sb,TAG_COMPANY);
        System.out.println("company k="+k);
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
}
