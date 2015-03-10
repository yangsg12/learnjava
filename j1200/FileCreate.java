package j1200;

import java.io.*;

/**
 * Created by Yang on 2015/3/6.
 */
public class FileCreate {
    public static void main(String[] args) {
        File file =  new File("C:/", "Users\\Yang\\Documents\\word.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                FileOutputStream out = new FileOutputStream(file);
                byte[] buy = "黄金搭档啊".getBytes();
                out.write(buy);
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                FileInputStream in = new FileInputStream(file);
                byte[] byt = new byte[1024];
                int len = in.read(byt);
                System.out.println("文件中的信息是: "+ new String(byt,0,len));
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
