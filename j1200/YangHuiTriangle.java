package j1200;

/**
 * Created by Yang on 2014/12/24.
 */
public class YangHuiTriangle {
    public static void main(String[] args) {
        int triangle[][] = new int[8][];

        for (int i = 0; i < triangle.length - 1; i++) {
            triangle[i] = new int[i+1];//初始化第二层数组大小
            for (int j =0;j<=triangle[i].length-1;j++) {
                if ( i==0 || j == 0 || j == triangle[i].length - 1){
                    triangle[i][j] = 1;
                }else{
                    triangle[i][j] = triangle[i - 1][j] + triangle[i - 1][j - 1];
                }
                System.out.print(triangle[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
