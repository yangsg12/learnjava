package concorrency_design.callable;

import java.util.concurrent.Callable;

/**
 * Created by Yang on 2017/7/20.
 */
public class RealData implements Callable<String> {
    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i< 10;i++) {
            sb.append(para);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return sb.toString();
    }
}
