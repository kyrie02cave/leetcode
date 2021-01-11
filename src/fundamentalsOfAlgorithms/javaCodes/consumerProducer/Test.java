package fundamentalsOfAlgorithms.javaCodes.consumerProducer;

public class Test {
    public static void main(String[] args) {
        // 创建共享资源
        Store store = new Store();
        // 创建生产者线程
        Producer producer1 = new Producer(store);
        Producer producer2 = new Producer(store);
        Producer producer3 = new Producer(store);
        Producer producer4 = new Producer(store);
        Producer producer5 = new Producer(store);

        producer1.setNum(10);
        producer2.setNum(80);
        producer3.setNum(10);
        producer4.setNum(90);
        producer5.setNum(10);

        // 创建消费者线程
        Consumer consumer1 = new Consumer(store);
        Consumer consumer2 = new Consumer(store);
        Consumer consumer3 = new Consumer(store);
        Consumer consumer4 = new Consumer(store);
        Consumer consumer5 = new Consumer(store);
        consumer1.setNum(20);
        consumer2.setNum(40);
        consumer3.setNum(20);
        consumer4.setNum(40);
        consumer5.setNum(20);

        //启动线程

        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();

        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();


    }
}
