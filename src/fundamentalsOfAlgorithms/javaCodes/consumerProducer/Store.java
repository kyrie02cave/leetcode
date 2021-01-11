package fundamentalsOfAlgorithms.javaCodes.consumerProducer;

import java.util.ArrayList;
import java.util.List;

public class Store {
    // 最大容量
    private static int MAX_VALUE = 100;
    // 储存产品
    private List<Object> list = new ArrayList<>();

    // 生产num个产品
    public void produce(int num){
        // 同步
        synchronized (list){
            // store已满则停止生产(wait)
            while(list.size() + num > MAX_VALUE){
                System.out.println("store已满，停止生产");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // store未满，继续生产
            for (int i = 0; i < num; i++) {
                list.add(new Object());
            }
            System.out.println("已生产产品数：" + num + ",仓库容量" + list.size());
            list.notifyAll();
        }
    }

    // 消费num个产品
    public void consume(int num){
        // 同步
        synchronized(list){
            // 判断是否为空
            while(list.size() < num){
                System.out.println("库存为空");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 库存不为空
            for (int i = 0; i < num; i++) {
                list.remove(0);
            }
            System.out.println("已消费产品数：" + num + "仓库容量：" + list.size());
            list.notifyAll();
        }
    }
}
