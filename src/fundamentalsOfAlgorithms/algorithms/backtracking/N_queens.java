package fundamentalsOfAlgorithms.algorithms.backtracking;

import org.junit.Test;

import java.util.*;

public class N_queens {
            /*题目描述：
            * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，
            * 其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，
            * 不只是平分整个棋盘的那两条对角线。

             示例:
             输入：4
             输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
             解释: 4 皇后问题存在如下两个不同的解法。
            [
             [".Q..",  // 解法 1
              "...Q",
              "Q...",
              "..Q."],

             ["..Q.",  // 解法 2
              "Q...",
              "...Q",
              ".Q.."]
            ]
        */

    public List<List<String>> solveNQueens01(int N){
        /*首先如何解决：如何检测一个摆法的正确性，然后使用回溯算法，有可行放置点则继续向下
        * 没有则返回，如果本行都没有，则修正上一轮的选择
        * 伪代码：
         dfs函数(x) {
            if x==n { //如果x等于n了，说明每行的皇后都放置完毕
                //将棋盘内容的快照保存下来
                return
            }
            for(y=0;i<n;++y) {
                if [x,y]这个位置是有效的，即横、竖、两个斜线都有没有被攻击 {
                    将棋盘[x,y]位置设置为 Q
                    dfs(x+1) 继续尝试下一行
                    将棋盘[x,y]位置还原
                }
            }
        }
        */
        // 生成N*N的棋盘
        char[][] queens = new char[N][N];
        // 填充棋盘，每个格子默认是“.”，没有皇后
        // fill(char[] a, char val):Assigns the specified char value to each element
        // of the specified array of chars.
        for (int i = 0; i < N; i++) {
            Arrays.fill(queens[i], '.');
        }
        // 作用？？
        List<List<String>> res = new ArrayList<List<String>>();
        // N表示总行数，x表示当前进行到多少行
        dfs(queens, 0, N, res);

        return res;
    }

    public void dfs(char[][] queens, int x, int N, List<List<String>> res){
        //x是从0开始计算的，当x==n时所有行的皇后都放置完毕，此时记录结果
        // x=N,放置完毕,保存记录
        if(x==N){
            // 新建一个List<String>存储当前结果
            List<String> ans = new ArrayList<>();
            // 遍历行
            for (int i = 0; i < N; i++) {
                // 新建一个StringBuilder对象，用于拼接字符
                StringBuilder temp = new StringBuilder();
                // 遍历列，把每行数据拼接成一个字符串
                for (int j = 0; j < N; j++) {
                    if(queens[i][j]=='.'){
                        temp.append('.');
                    }else{
                        temp.append('Q');
                    }
                }
                // 将拼接好的字符串，加入ans
                ans.add(temp.toString());
            }
            // 遍历完所有列，将ans加入res
            res.add(ans);
            return;
        }

        // 遍历每一行
        for (int y = 0; y < N; y++) {
            // 检查[x,y]这一坐标是否可以放置皇后
            // 如果可以，就放置皇后，并继续检查下一行
            if(check(queens, x, y, N)){
                queens[x][y] = 'Q';
                dfs(queens, x+1, N, res);
                queens[x][y] = '.';
            }
        }
    }

    public boolean check(char[][] queens, int x, int y, int N){
        /*
        * 一种比较朴素的判断方法：
        * 分别判断当前元素的列、左上对角线、右上对角线是否存在Q*/
        // 判断当前元素所在列是否存在Q
        for (int i = 0; i < x; i++) {
            if(queens[i][y]=='Q'){
                return false;
            }
        }

        // 判断当前元素左上对角线是否存在Q
        int i = x - 1;
        int j = y - 1;
        while((i>=0)&&(j>=0)){
            if(queens[i][j] == 'Q'){
                return false;
            }
            i--;
            j--;

        }

        // 判断当前元素右上对角线是否存在Q
        i = x - 1;
        j = y + 1;
        while((j<N)&&(i>=0)){
            if(queens[i][j] == 'Q'){
                return false;
            }
            i--;
            j++;
        }

        return true;
    }

    public List<List<String>> solveNQueens02(int N){
        /*
        * 优化：
        * 1.优化check函数，因为同一对角线元素存在一定的特性，x+y/x-y为相同值
        * 2.可以使用一个一位横向数组替代N*N的char[][]*/
        // 创建一个N行的数组，下标i对应N*N棋盘格子第i行的皇后位置
        int[] queens = new int[N];
        List<List<String>> res = new ArrayList<List<String>>();
        // 三个集合，分别判断某一行，左斜线，右斜线
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> left = new HashSet<Integer>();
        Set<Integer> right = new HashSet<Integer>();
        dfs(res,N,0,queens,columns, left, right);
        return res;

    }

    public void dfs(List<List<String>> res, int N, int x, int[] queens, Set<Integer> columns, Set<Integer> left,
                    Set<Integer> right){
        if(x==N){
            // 所有皇后摆放完毕
            // 将queens数组中保存的结果保存拼接起来
            List<String> temp = new ArrayList<>();
            for (int k = 0; k < N; k++) {
                char[] row = new char[N];
                Arrays.fill(row, '.');
                row[queens[k]] = 'Q';
                temp.add(new String(row));
            }
            res.add(temp);
            return;
        }
        // 遍历行中的每一列，并检查竖线、左斜线、右斜线是否有皇后
        for (int y = 0; y < N; y++) {
            if(columns.contains(y)){
                continue;// 注意：使用的是continue
            }

            if(left.contains(x - y)){
                continue;// 注意：使用的是continue
            }

            if(right.contains(x + y)){
                continue;// 注意：使用的是continue
            }

            // 都不存在皇后
            // 将y放入数组，并在列、左斜线、右斜线集合中加入y,x-y,x+y
            queens[x] = y;
            columns.add(y);
            left.add(x-y);
            right.add(x+y);
            // 递归
            dfs(res,N,x+1,queens,columns, left, right);
            //当下一层的所有递归遍历完后，回到本轮需要将之前集合、arr数组中保存的结果都清空
            queens[x] = -1;
            columns.remove(y);
            left.remove(x-y);
            right.remove(x+y);

        }
    }

    @Test
    public void solveNQueensTest(){
        int N = 8;
        int count = 1;
        List<List<String>> res = solveNQueens02(N);
        // 打印结果
        for (List<String> stringList: res) {
            System.out.println("解法" + count + ":");
            for (String str: stringList) {
                System.out.println(str);
            }
            count++;
            System.out.println("====================================");
        }
    }
}

