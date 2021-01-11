package fundamentalsOfAlgorithms.algorithms.sort;

import org.junit.Test;

import java.util.Arrays;

public class MergeSort {

    public int[] sort(int[] A, int n){
        sort_c(A, 0, n-1);
        return A;
    }

    public void sort_c(int[] A, int p, int r){
        if(p < r){
            int q = (p + r) / 2;
            sort_c(A, p, q);
            // 上一行代码递归后，p，q，r的值发生变化
            sort_c(A, q + 1, r);
            // 归并，将分离的片段组合
            merge(A, p, q, r);
        }

    }

    public void merge(int[] A, int p, int q, int r){
        int[] temp = new int[r - p + 1];
        int left = p;
        int right = q + 1;
        int index = 0;
        int count;
        // 合并两个子数组,双指针实现
        while(left<=q&&right<=r){
            if(A[left] < A[right]){
                temp[index++] = A[left++];
            }else{
                temp[index++] = A[right++];
            }
        }

        // 剩下的直接拷贝
        while(left<=q){
            temp[index++] = A[left++];
        }
        while(right<=r){
            temp[index++] = A[right++];
        }

        // 归并排序需要额外创建一个数组，用于存储合并好的数据
        // 将合并好的数据更新到数组中
        count = 0;
        for (int i = p; i <= r; i++) {
            A[i] = temp[count++];
        }
        }

    @Test
    public void testMergeSort(){
        int[] arrayTest = new int[]{1, 4, -6, 5, 6, 2, 5, 9};
        int[] arraySorted = sort(arrayTest, arrayTest.length);
        System.out.println(Arrays.toString(arraySorted));
    }

}
