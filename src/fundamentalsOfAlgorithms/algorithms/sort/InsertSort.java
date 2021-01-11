package fundamentalsOfAlgorithms.algorithms.sort;

import org.junit.Test;

import java.util.Arrays;

public class InsertSort {
    public int[] sort(int[] array){
        int temp;
        int k;
        // i之前为排序好的数据，第一个元素默认为已排好序
        for (int i = 1; i < array.length ; i++) {
                // 将第i个元素依次与之前排序好的i个元素比较，找到插入位置
                for (int j = 0; j < i; j++) {
                    // 当前元素比A[i]小
                    if(array[i] < array[j]){
                        temp = array[i];
                        // 数据搬移，注意数据的覆盖问题
                        k = i - 1;
                        while(k > j - 1){
                            array[k + 1] = array[k--];
                        }
                        array[j] = temp;
                        // 找到合适的位置，并插入了数据，跳出本次循环
                        break;
                    }
                }
            }
        return array;
        }

    @Test
    public void testInsertSort(){
        int[] arrayTest = new int[]{1, 4, -6, 5, 6, 2, 5, 9};
        int[] arraySorted = sort(arrayTest);
        System.out.println(Arrays.toString(arraySorted));
    }
}
