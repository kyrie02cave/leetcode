package fundamentalsOfAlgorithms.javaCodes.consumerProducer;

public class Producer extends Thread{
    // 生产产品个数
    private int num;
    // 装载仓库
    private Store store;

    public Producer(Store store){
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
        this.store.produce(num);
    }
}
