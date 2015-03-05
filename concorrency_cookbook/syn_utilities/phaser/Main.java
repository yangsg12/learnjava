package concorrency_cookbook.syn_utilities.phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/2/3.
 */
public class Main {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        FileSearch system = new FileSearch("C:\\Users\\Yang\\Pictures\\Camera Roll", "jpg", phaser);
        FileSearch apps = new FileSearch("C:\\Users\\Yang\\Pictures\\本机照片", "jpg", phaser);
        FileSearch document = new FileSearch("C:\\Users\\Yang\\Music", "mp3", phaser);

        Thread sysThread = new Thread(system, "system");
        sysThread.start();
        Thread appThread = new Thread(apps, " apps");
        appThread.start();
        Thread docThread = new Thread(document, "document");
        docThread.start();

        try {
            sysThread.join();
            appThread.join();
            docThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Terminated :  "+phaser.isTerminated());

    }
}



/**
 * Created by Yang on 2015/2/3.
 */
 class FileSearch implements Runnable {
    private String initPath;
    private String end;
    private List<String> result;
    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser){
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
        result = new ArrayList<String>();
    }

    private void directoryProcess(File file){
        File list[] = file.listFiles();
        if (list != null){
            for (int i =0;i<list.length;i++){
                if (list[i].isDirectory()) {
                    directoryProcess(list[i]);
                }else {
                    fileProcess(list[i]);
                }
            }
        }
    }

    private void fileProcess(File file) {
        if (file.getName().endsWith(end)) {
            result.add(file.getAbsolutePath());
        }
    }

    private void filterResult() {
        List<String> newResult = new ArrayList<String>();
        long actualDate = new Date().getTime();

        for (int i=0;i<newResult.size();i++) {
            File file = new File(result.get(i));
            long fileDate = file.lastModified();

            if (actualDate - fileDate< TimeUnit.MILLISECONDS.convert(1,TimeUnit.DAYS)) {
                newResult.add(result.get(i));
            }
        }

        result = newResult;
    }

    private boolean checkResult(){
        if (result.isEmpty()) {
            System.out.printf(" %s : Phase %d : 0 result. \n",
                    Thread.currentThread().getName(), phaser.getPhase());
            System.out.printf(" %s : Phase %d end. \n",
                    Thread.currentThread().getName(),phaser.getPhase());
            phaser.arriveAndDeregister();
            return false;
        }else {
            System.out.printf(" %s : Phase %d : %d result \n ",
                    Thread.currentThread().getName(),phaser.getPhase(),result.size());
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    private void showInfo(){
        for (int i =0;i<result.size();i++) {
            File file = new File(result.get(i));
            System.out.printf(" %s : %s \n",Thread.currentThread().getName(),file.getAbsolutePath());
        }
        phaser.arriveAndAwaitAdvance();

    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        System.out.printf(" %s 开始了 \n", Thread.currentThread().getName());
        File file = new File(initPath);
        if (file.isDirectory()) {
            directoryProcess(file);
        }
        if (!checkResult()){
            return;
        }

        filterResult();
        if (!checkResult()) {
            return;
        }

        showInfo();
        phaser.arriveAndDeregister();
        System.out.printf("%s 完成了！", Thread.currentThread().getName());

    }
}
