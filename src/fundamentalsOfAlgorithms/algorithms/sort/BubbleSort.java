package fundamentalsOfAlgorithms.algorithms.sort;

import org.junit.Test;
import java.util.Arrays;

public class BubbleSort {
    public int[] sort(int[] array){
        int temp;
        // 优化冒泡排序，引入计数变量，若本次循环不存在数据交换这说明当前序列已实现有序排列
        // 所以改进后的冒泡排序的实际执行时间受序列本身的逆序度影响
        int count;
        for (int i = 0; i <array.length ; i++) {
            count = 0;
            for (int j = 0; j <array.length -(1 + i) ; j++) {
                if(array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    count++;
                }
            }
            if(count==0){
                break;
            }
        }
        return array;
    }

    @Test
    public void testBubbleSort(){
        int[] arrayTest = new int[]{1, 5, 6, 2, 5, 9};
        int[] arraySorted = sort(arrayTest);
        System.out.println(Arrays.toString(arraySorted));
    }

}
