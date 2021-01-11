package fundamentalsOfAlgorithms.javaCodes.consumerProducer;

public class Consumer extends Thread{
    // 消费产品个数
    private int num;
    // 装载仓库
    private Store store;

    public Consumer(Store store){
        this.store = store;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        this.store.consume(num);
    }
}
