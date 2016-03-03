package concorrency_cookbook.c5_fork_join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2016/2/28.
 */

public class Product {
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


class ProductListGenerator{
    public List<Product> generate(int size) {
        List<Product> ret = new ArrayList<Product>();
        for (int i = 0; i < size; i++) {
            Product product = new Product();
            product.setName("Product "+ i);
            product.setPrice(10);
            ret.add(product);
        }
        return ret;
    }
}

class Task extends RecursiveAction {
    private static final long serialVersionUID = 1L;
    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if (last - first < 10) {
            updatePrice();
        }else {
            int middle = (last + first)/2;
            System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
            Task t1 = new Task(products, first, middle + 1, increment);
            Task t2 = new Task(products, middle + 1, last, increment);
            invokeAll(t1, t2);
        }

    }

    private void updatePrice() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));

        }
    }
}


 class Main {
    public static void main(String[] args) {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(100);
        Task task = new Task(products, 0, products.size(), 0.20);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        do {
            System.out.printf("Main : Thread count : %d \n", pool.getActiveThreadCount());
            System.out.printf("Main : Thread steal : %d \n", pool.getStealCount());
            System.out.printf("Main : Parallelism :  %d \n", pool.getParallelism());

            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());
        pool.shutdown();

        if (task.isCompletedNormally()) {
            System.out.printf("Main : The process has completed normally.\n");
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                if (product.getPrice() != 12) {
                    System.out.printf("Product %s : %f \n", product.getName(), product.getPrice());
                }
            }
        }

        System.out.println("Main : End of the program.\n");
    }
}