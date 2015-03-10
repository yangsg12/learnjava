package j1200;

import java.io.File;
import java.io.FileReader;

/**
 * Created by Yang on 2015/3/6.
 */
public class FileWriter {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Yang\\Documents\\word2.txt");
        try {
            if (!file.exists()){
                file.createNewFile();
            }

            FileReader fileReader = new FileReader("C:\\Users\\Yang\\Documents\\word.txt");
            java.io.FileWriter fileWriter = new java.io.FileWriter(file);
            int len = 0;
            while ((len = fileReader.read())!=-1){
                fileWriter.write(len);
            }
            fileReader.close();
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
