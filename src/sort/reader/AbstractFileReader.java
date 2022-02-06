package sort.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public abstract class AbstractFileReader<T> {

    public abstract List<T> getEntities(String fileName);

    public StringBuilder returnStringBuilder(String fileName){
        return readFile(fileName);
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

    protected StringBuilder readFile(String fileName){
        StringBuilder stringBuilder=new StringBuilder();
        try {
            BufferedReader bufferedReader=new BufferedReader(new java.io.FileReader(fileName));
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
