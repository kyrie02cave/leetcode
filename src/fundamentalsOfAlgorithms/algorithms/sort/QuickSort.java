package fundamentalsOfAlgorithms.algorithms.sort;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort {
    public int[] sort(int[] A, int n){
        sort_c(A, 0, n-1);
        return A;
    }

    public void sort_c(int[] A, int p, int r){

        if(p >= r){
            return;
        }
        int q = partition(A, p, r);
        sort_c(A, p, q-1);
        sort_c(A, q + 1, r);
        // 终止条件是什么？终止条件代码放置的位置是哪？

    }

    public int partition(int[] A, int p, int r){
        int temp;
        int middle = A[r];
        int j = p;
        for(int i = p;i<=r;i ++){
            if(A[i] <= middle){
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                j++;
            }
        }
        // 注意返回的是j-1
        return j - 1;
    }

    @Test
    public void testMergeSort(){
        int[] arrayTest = new int[]{1, 3, -6, 15, 2, 2, 5, 9};
        int[] arraySorted = sort(arrayTest, arrayTest.length);
        System.out.println(Arrays.toString(arraySorted));
    }
}
