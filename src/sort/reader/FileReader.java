package sort.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {

    protected StringBuilder returnStringBuilder(String fileName){
        return readFile(fileName);
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
