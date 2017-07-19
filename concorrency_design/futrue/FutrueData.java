package concorrency_design.futrue;

/**
 * Created by Yang on 2017/7/19.
 */
 class FutrueData implements Data {
    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        return realData.result;
    }


    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完成!!!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据 == " +data.getResult());
    }
}

 class RealData implements Data{
    protected final String result;

    public RealData(String para) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}


 class Client{
     public Data request(final String queryStr) {
         final FutrueData futrue = new FutrueData();
         new Thread(){
             @Override
             public void run() {
                 RealData realData = new RealData(queryStr);
                 futrue.setRealData(realData);
             }
         }.start();

         return futrue;
     }
}
