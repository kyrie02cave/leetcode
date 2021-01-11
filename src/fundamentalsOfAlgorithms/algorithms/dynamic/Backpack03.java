package fundamentalsOfAlgorithms.algorithms.dynamic;


import org.junit.Test;

import java.util.Arrays;

import static java.lang.Integer.max;

public class Backpack03 {
    /*多重背包一题目描述：
    * 有 N 种物品和一个容量是 V 的背包。
        第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。
        求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
        输出最大价值。

        输入格式
        第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
        接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。

        输出格式
        输出一个整数，表示最大价值。

        数据范围
        0<N,V≤100
        0<vi,wi,si≤100
        输入样例
        4 5
        1 2 3
        2 4 1
        3 4 3
        4 5 2
        输出样例：
        10
        */

    public int solution01(int N, int V, int[] volumes, int[] nums, int[] worth){
        /*
         * 一种朴素的解法：转化为0-1背包问题*/
        // 初始化dp数组
        int[][] dp = new int[N+1][V+1];// 防止数组越界
        // 物品数量
        int k;
        for (int i = 1; i <= N; i++) {
            // 背包容量,优化：注意起始点
            for (int j = volumes[i-1]; j <= V; j++) {
                k=0;
                while((k <= j/volumes[i-1])&&(k<=nums[i-1])){
                    // 注意：此处第一个为i而不是i-1
                    dp[i][j] = max(dp[i][j], dp[i-1][j-k*volumes[i-1]] + k*worth[i-1]);
                    k++;
                }
            }
        }
        // 输出dp表
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                System.out.print(dp[i][j]);
                System.out.print('\t');
            }
            System.out.println();
        }
        //获取最大值,一般来说dp[N][V]最大，但也有特殊情况
        int maxValue = 0;
        for (int i = 1; i <= V; i++) {
            if(dp[N][i] > maxValue){
                maxValue = dp[N][i];
            }
        }
        return maxValue;
    }

    public int solution02(int N, int V, int[] volumes, int[] nums, int[] worth){
        /*
         * 一种朴素的解法：转化为0-1背包问题---优化*/
        // 初始化dp数组
        int[] dp = new int[V+1];// 防止数组越界
        // 物品数量
        int k;
        for (int i = 1; i <= N; i++) {
            // 背包容量,优化：注意起始点
            for (int j = V; j >= volumes[i-1]; j--) {
                k=1;
                while((k <= j/volumes[i-1])&&(k<=nums[i-1])){
                    dp[j] = max(dp[j], dp[j-k*volumes[i-1]] + k*worth[i-1]);
                    k++;
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        ///获取最大值,一般来说dp[N][V]最大，但也有特殊情况
        int maxValue = 0;
        for (int i = 0; i <= V; i++) {
            if(dp[i] > maxValue){
                maxValue = dp[i];
            }
        }
        return maxValue;
    }


    @Test
    public void solutionTest(){
        int N = 4;
        int V = 15;
        int[] volumes = new int[]{2,3,1,5};
        int[] worth = new int[]{5,3,3,6};
        int[] nums = new int[]{2,3,1,5};
        int f = solution01(N, V, volumes, nums, worth);
        System.out.println(f);
    }
}
