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

    public StringBuilder getPersonByValue(StringBuilder sb){
        if(sb.indexOf("<Person>")!=-1){
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

    public List<StringBuilder> getCompaniesByValue(StringBuilder sb){
        List<StringBuilder> listSringBuilder=new ArrayList<>();
        int num=numRepeatWord(sb,TAG_COMPANY);
        for (int i=0;i<num;i++){
            int closeIndex=sb.indexOf("</"+TAG_COMPANY+">");
            StringBuilder stringBuilder=getValueByTag(sb,TAG_COMPANY);
            sb.delete(0,closeIndex+TAG_COMPANY.length()+3);
            listSringBuilder.add(stringBuilder);
        }
        return  listSringBuilder;
    }


    public StringBuilder getCompanyByValue(StringBuilder sb){
        return  getValueByTag(sb,TAG_COMPANY);
    }

    public StringBuilder getNameByValue(StringBuilder sb){
        if(sb.indexOf("<name>")!=-1){
            return  getValueByTag(sb,TAG_NAME);
        }
        return null;
    }
    public StringBuilder getSurnameByValue(StringBuilder sb){
        if(sb.indexOf("<surname>")!=-1){
            return  getValueByTag(sb,TAG_SURNAME);
        }
        return null;
    }

    public StringBuilder getWeightByValue(StringBuilder sb){
        if(sb.indexOf("<weight>")!=-1){
            return  getValueByTag(sb,TAG_WEIGHT);
        }
        return null;
    }
    public StringBuilder getDateBirthByValue(StringBuilder sb){
        if (sb.indexOf("<LocalDate>")!=-1){
            return  getValueByTag(sb,TAG_BIRTH_DATE);
        }
        return null;
    }

    public List<Company> companiesWithCharacteristics(StringBuilder sb2){
        List<Company> listCompaniesNew=new ArrayList<>();
        List<StringBuilder> listSbCompanies=getCompaniesByValue(sb2);
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
                listCompaniesNew.add(new Company(companyName,new Person(name,surName,weight,dateBirth)));
            }
        }
        return listCompaniesNew;
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

    public List<Company> returnListCompanies(List<StringBuilder> stringBuilders){//string buiders in companies
        List<Company> companies=new ArrayList<>();
        for (StringBuilder sb:stringBuilders){
            String companyName=null;
            String name=null;
            String surname=null;
            Integer weight=null;
            LocalDate dateBirth=null;
            String [] arrayTags=new String[]{"name","surname","weight","LocalDate"};
            int start=0;
            int stop=0;
            int indPerson=sb.indexOf("<Person>");//


            if ((start=sb.indexOf("<"+TAG_COMPANY_NAME+">"))!=-1&&(stop=sb.indexOf("</"+TAG_COMPANY_NAME+">"))!=-1){
                companyName=sb.substring(start+13,stop);
            }
            for (String arrayTag:arrayTags){
                if ((start=sb.indexOf("<"+arrayTag+">",indPerson))!=-1&&((stop=sb.indexOf("</"+arrayTag+">",indPerson)))!=-1){
                    if((start+arrayTag.length()+2)!=stop){
                        String value=sb.substring(start+arrayTag.length()+2,stop);
                        switch (arrayTag){
                            case ("name")://
                                name=value;
                                break;
                            case ("surname") ://
                                surname=value;
                                break;
                            case ("weight"):
                                weight=Integer.parseInt(value);
                                break;
                            case ("LocalDate"):
                                dateBirth=LocalDate.parse(value);
                        }
                    }

                }
            }
            companies.add(new Company(companyName,new Person(name,surname,weight,dateBirth)));
        }
        return companies;
    }




    public  List<Company> returnCompanies(StringBuilder stringBuilder) {
        List<Company>listCompanies=new ArrayList<>();
        String companyName=null;
        String [] arrayTags=new String[]{"name","surname","weight","LocalDate"};
        while (stringBuilder.indexOf("<Company>")!=-1){
            int tagPersonIndex;
            int finishtagPersonIndex;
            while ((tagPersonIndex=stringBuilder.indexOf("<Person>"))!=-1){
                int i=stringBuilder.indexOf("<CompanyName>");//
                int j=stringBuilder.indexOf("</CompanyName>");//
                finishtagPersonIndex=stringBuilder.indexOf("</Person>");
                if (i!=-1&&j!=-1){
                    companyName=stringBuilder.substring(i+13,j).trim();
                }
                String info;
                String name=null;
                String surname=null;
                int weight=Integer.parseInt("0");
                LocalDate dateBirth=null;
                for (int k=0;k<arrayTags.length;k++ ){
                    int start=stringBuilder.indexOf("<"+arrayTags[k]+">",tagPersonIndex);
                    int stop=stringBuilder.indexOf("</"+arrayTags[k]+">",tagPersonIndex);
                    info=stringBuilder.substring(start+arrayTags[k].length()+2,stop).trim();

                    if (info!=null&&!info.equals("")){
                        switch (arrayTags[k]){
                            case "name":
                                if (stop>finishtagPersonIndex){
                                    name=null;
                                }
                                else
                                name=info;
                                break;
                            case "surname":
                                if (stop>finishtagPersonIndex){
                                    surname=null;
                                }
                                else
                                    surname=info;
                                break;
                            case  "weight":
                                if (stop>finishtagPersonIndex){
                                    weight=0;
                                }
                                else
                                    weight=Integer.parseInt(info);
                                break;
                            case "LocalDate":
                                if (stop>finishtagPersonIndex){
                                    dateBirth=null;
                                }
                                else
                                    dateBirth=LocalDate.parse(info);
                                break;
                            default:
                                System.out.println("неизвестный тег");
                        }
                    }
                    else {
                        switch (arrayTags[k]){
                            case "name":
                                name=null;
                                break;
                            case "surname":
                                surname=null;
                                break;
                            case  "weight":
                                weight=Integer.parseInt("0");//////////////////////////////////
                                break;
                            case "LocalDate":
                                dateBirth=null;
                                break;
                            default:
                                System.out.println("неизвестный тег");
                        }
                    }
                }
                stringBuilder.delete(stringBuilder.indexOf("<Company>"),stringBuilder.indexOf("</Company>")+10);
                Person personHead=new Person(name,surname,weight,dateBirth);
                listCompanies.add(new Company(companyName,personHead));
            }
        }
        return listCompanies;
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

/*

    public String returnPersonCharacteristics(List<StringBuilder> listStringBuilders){
        String companyName=null;
        for (int i=0;i<listStringBuilders.size();i++){
           StringBuilder myStringBuilder=listStringBuilders.get(i);
           if ((myStringBuilder.indexOf(TAG_COMPANY_NAME)!=0&&myStringBuilder.indexOf("/"+TAG_COMPANY_NAME)!=0)){
               companyName=myStringBuilder.substring(myStringBuilder.indexOf(TAG_COMPANY_NAME),myStringBuilder.indexOf("/"+TAG_COMPANY_NAME));
           }
        }
        System.out.println(companyName);
        return companyName;
    }

 */
