package fundamentalsOfAlgorithms.algorithms.greedy;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class Greedy01 {

    /*
    * 例题1：钱币找零问题
            1、题目：指定币值和相应的数量，用最少的数量凑齐某金额。
            2、思路：利用贪心算法，我们优先选择面值大的钱币，以此类推，直到凑齐总金额。*/

    public int[] getMoney(int sum, int[] values, int[] counts){
        /*
        * sum:待找零的数目
        * values:所拥有的钱币的面值,升序排列
        * counts:对应的每种面值的数量*/
        int res;
        ArrayList<Integer> moneyList = new ArrayList<>();
        for (int i = values.length - 1; i >= 0; i--) {
            while(sum - values[i] >= 0 && counts[i]>0){
                    sum = sum - values[i];
                    counts[i]--;
                    moneyList.add(values[i]);
            }
        }
        // 待优化，将ArrayList转数组int[]
        int num = moneyList.size();
        int[] moneyArray = new int[num];
        for (int i = 0; i < moneyList.size() ; i++) {
            moneyArray[i] = moneyList.get(i);
        }
        return moneyArray;
    }

    @Test
    public void getMoneyTest(){
        int sum = 253;
        int[] values = new int[]{1, 2, 5, 10, 20, 50, 100};
        int[] counts = new int[]{5, 3, 4, 2, 6, 4, 2};
        int[] moneyArray = getMoney(sum, values, counts);
        System.out.println(Arrays.toString(moneyArray));

    }
}
