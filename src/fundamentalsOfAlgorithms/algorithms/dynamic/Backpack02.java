package fundamentalsOfAlgorithms.algorithms.dynamic;

import org.junit.Test;

import java.util.Arrays;

import static java.lang.Integer.max;


public class Backpack02 {
    /*完全背包问题题目描述：
    * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
        第 i 种物品的体积是 vi，价值是 wi。
        求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
        输出最大价值。

        输入格式
        第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
        接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 种物品的体积和价值。

        输出格式
        输出一个整数，表示最大价值。

        数据范围
        0<N,V≤1000
        0<vi,wi≤1000
        输入样例
        4 5
        1 2
        2 4
        3 4
        4 5
        *
        输出样例：
        10
        */

    public int solution01(int N, int V, int[] volumes, int[] worth){
        /*
         * 一种朴素的解法：转化为0-1背包问题*/
        // 初始化dp数组
        int[][] dp = new int[N+1][V+1];// 防止数组越界
        // 物品数量
        for (int i = 1; i <= N; i++) {
            // 背包容量,优化：注意起始点
            for (int j = volumes[i-1]; j <= V; j++) {
                for (int k = 0; k <= j/volumes[i-1]; k++){
                    // 背包容量小于当前物品体积，只能不拿-----！！！注意：此处第一个为i而不是i-1
                    dp[i][j] = max(dp[i][j], dp[i-1][j-k*volumes[i-1]] + k*worth[i-1]);
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


    public int solution02(int N, int V, int[] volumes, int[] worth){
        /*
        * 朴素解法优化(仿0-1背包)*/
        // 初始化dp数组
        int[] dp = new int[V+1];

        for (int i = 1; i <= N; i++) {
            // 背包容量
            // 注意方向，用到的是前面的信息
            for (int j = V; j >= volumes[i-1]; j--) {
                for (int k = 0; k <= j/volumes[i-1]; k++) {
                    dp[j] = max(dp[j], dp[j-k*volumes[i-1]] + k*worth[i-1]);
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        //获取最大值,一般来说dp[N][V]最大，但也有特殊情况
        int maxValue = 0;
        for (int i = 0; i <= V; i++) {
            if(dp[i] > maxValue){
                maxValue = dp[i];
            }
        }
        return maxValue;

    }

        public int solution03(int N, int V, int[] volumes, int[] worth){
        /*相较于0-1背包，完全背包去除了每个物品都只有一个的限制，物品是无限个，但是
        * 因为背包本身的容量是有限的，所以并做不到真正的无限选取，故只需在原来的基础上加
        * 一层循环即可。注意：与朴素解法不同，完全背包同一行的数据间其实也存在先后关系，可以将同一行前面的每个
        * 元素看成单独一行的一部分且前后有一定的关系
        *    +
        *    ++
        *    +++
        *    ++++
        *    +++++
        * 与朴素方法不同的是，子问题的角度不一样，朴素方法是背包容量j相同，物品种类不同；而本方法是，物品可用种类相同，背包容量j不同
        * */
        int[][] dp = new int[N+1][V+1];
        int k;
        for (int i = 1; i <= N; i++) {
            for (int j = volumes[i-1]; j <= V; j++) {
                k=0;
                while(k <= j/volumes[i-1]){
                    // 状态转移方程,注意最后一个dp的首索引是i(区别于0-1背包的i-1)
                    dp[i][j]=max(dp[i-1][j], dp[i][j-k*volumes[i-1]] + k*worth[i-1]);
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

    public int solution04(int N, int V, int[] volumes, int[] worth){
        /*优化：时间复杂度上应该优化不了，主要是空间复杂度上的优化
        从状态转移方程分析：
              dp[i][j]=max(dp[i-1][j], dp[i-1][j-k*volumes[i-1]] + k*worth[i-1]);
        当前状态与上一轮的同位置状态以及本轮的先前状态有关,举例：
             dp[2][1], dp[2][2],dp[1][3]===>dp[2][3]
        不存在覆盖问题，所以可以使用滚动数组实现，注意：此时，循环的方向为从前往后
        * */
        int[] dp = new int[V+1];
        int k;
        for (int i = 1; i <= N; i++) {
            for (int j = volumes[i-1]; j <= V; j++) {
                k=1;
                while(k <= j/volumes[i-1]){
                    dp[j]=max(dp[j], dp[j-k*volumes[i-1]] + k*worth[i-1]);
                    k++;
                }
            }
            // 输出dp表
            System.out.println(Arrays.toString(dp));
        }

        //获取最大值,一般来说dp[N][V]最大，但也有特殊情况
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
        int N = 10;
        int V = 25;
        int[] volumes = new int[]{1,3,1,5,2,6,4,3,4,3};
        int[] worth = new int[]{2,3,3,6,4,6,7,3,2,5};
        int f = solution01(N, V, volumes, worth);
        System.out.println(f);
    }
}
