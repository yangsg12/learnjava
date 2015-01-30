package concorrency_cookbook.thread_factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Yang on 2015/1/30.
 */
public class MyThreadFactory implements ThreadFactory {
    private int count;
    private String name;
    private List<String> stats;

    public MyThreadFactory(String name) {
        count = 0;
        this.name = name;
        stats = new ArrayList<String>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + " 的第 " + count+" 个");
        count++;
        stats.add(String.format("创建线程： %d   名字是： %s     时间是 %s\n", t.getId(), t.getName(), new Date()));
        return t;
    }

    public String getStats(){
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> iterator = stats.iterator();
        while (iterator.hasNext()){
            stringBuffer.append(iterator.next());
            stringBuffer.append("\n");

        }
        return stringBuffer.toString();
    }

}
