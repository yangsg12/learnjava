package j1200;

/**
 * Created by Yang on 2014/12/24.
 */
public class MultiplicationTable {
    public static void main(String[] args) {
        for (int i=1;i<=9;i++){ //第一层 1到9
            for(int j =1;j<=i;j++){   // 第二层最大和第一层相等
                System.out.print(j+"*"+i+"="+i*j+"\t");
            }
            System.out.println();
        }
    }
}
