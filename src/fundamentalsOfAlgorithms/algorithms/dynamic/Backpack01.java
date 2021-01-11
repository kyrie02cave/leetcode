package fundamentalsOfAlgorithms.algorithms.dynamic;

import org.junit.Test;

import java.util.Arrays;

import static java.lang.Integer.max;

public class Backpack01 {
    /*
    * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
    第 i 件物品的体积是 vi，价值是 wi。
    求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。输出最大价值。

    输入格式:
        第一行两个整数，N，V，用空格隔开，分别表示物品数量和背包容积。
        接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 件物品的体积和价值。

    输出格式:
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
    输出样例：
            8
     */
    // 解法一：贪心算法，优先选择单位价值最大的物品，但是不能保证最优解
    // 解法二：动态规划
    public int solution01(int N, int V, int[] volumes, int[] worth){
        /*
        * 这是一个多阶段决策问题，问题的最优解包含子问题的最优解(最优子结构)
        * 用子问题定义状态：即 F[i, v] 表示“前 i 件物品恰放入一个容量为 v 的背包”可以获得
          的最大价值。则其状态转移方程便是：
                    F[i, v] = max{F[i − 1, v], F [i − 1, v − Ci] + Wi}
        * 且当前状态至于前面的状态有关(无后效性)，
        * 每一次决策问题都相同(重复子问题)
        * 打表解法*/
        // 初始化dp数组
        int[][] dp = new int[N+1][V+1];// 防止数组越界
        // 物品数量
        for (int i = 1; i <= N; i++) {
            // 背包容量,优化：注意起始点
            for (int j = volumes[i-1]; j <= V; j++) {
                // 背包容量小于当前物品体积，只能不拿
                dp[i][j] = max(dp[i-1][j], dp[i-1][j-volumes[i-1]] + worth[i-1]);
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

    // 优化:由状态转移方程可知，当前状态只与前一状态有关，从数组上反映就是下一行只与前一行有关。
    // 所以，可利用滚动数组优化空间复杂度，注意数组覆盖的方向
    public int solution02(int N, int V, int[] volumes, int[] worth){
        // 初始化dp数组
        int[] dp = new int[V+1];

        for (int i = 1; i <= N; i++) {
            // 背包容量
            // 注意方向，用到的是前面的信息
            for (int j = V; j >= volumes[i-1]; j--) {
                dp[j] = max(dp[j], dp[j-volumes[i-1]] + worth[i-1]);
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

    @Test
    public void solutionTest(){
        int N = 10;
        int V = 25;
        int[] volumes = new int[]{2,3,1,5,2,6,4,3,4,3};
        int[] worth = new int[]{5,3,3,6,4,6,7,3,2,5};
        int f = solution01(N, V, volumes, worth);
        System.out.println(f);
    }
}
