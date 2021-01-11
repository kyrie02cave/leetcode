package fundamentalsOfAlgorithms.javaCodes.singletonMode;

public class DoubleCheck {
    // 加volatile禁用指令重排，防止获得一个未初始化地对象
    private static volatile DoubleCheck instance;
    private DoubleCheck(){
    }

    public static DoubleCheck getInstance(){
        // 第一次判断对象是否安全
        if(instance==null){
            // 加锁,锁Class文件
            synchronized (DoubleCheck.class){
                // 再次判断对象是否创建，防止其他线程已创建
                if(instance==null){
                    instance = new DoubleCheck();
                }
            }
        }
        // 已创建直接返回
        return instance;
    }
}
