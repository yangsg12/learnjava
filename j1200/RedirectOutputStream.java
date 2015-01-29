package j1200;



import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by Yang on 2014/12/23.
 */
public class RedirectOutputStream {
    public static void main(String[] args) {
        try {
            PrintStream out = System.out;
            PrintStream ps = new PrintStream("./log.txt");
            System.setOut(ps);

            int age = 20;
            System.out.println("年龄成功定义");
            String sex = "女";
            System.out.println("性别成功定义");
            String info = "这人" + age + "岁了" + "是个" + sex + "的";
            System.out.println("合一起" + info);

            System.setOut(out);
            System.out.println("去看日志文件");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
