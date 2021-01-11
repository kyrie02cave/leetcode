package fundamentalsOfAlgorithms.algorithms.dynamic;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SplitpalindroomString {
    /*131.分割回文串
    * 题目描述：
    * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
    返回 s 所有可能的分割方案。
    示例:
    输入: "aab"
    输出:
    [
      ["aa","b"],
      ["a","a","b"]
    ]
    */

    public List<List<String>> solution01(String s) {
       /* 参考：https://leetcode-cn.com/problems/palindrome-partitioning/solution/hui-su-you-hua-jia-liao-dong-tai-gui-hua-by-liweiw/
        搜索问题主要使用回溯法。
        回溯法思考的步骤：
        1、画递归树；
        2、根据自己画的递归树编码。

        思考如何根据这棵递归树编码：
        1、每一个结点表示剩余没有扫描到的字符串，产生分支是截取了剩余字符串的前缀；
        2、(核心)单个字母也是回文，产生前缀字符串的时候，判断前缀字符串是否是回文。
            如果前缀字符串是回文，则可以产生分支和结点；
            如果前缀字符串不是回文，则不产生分支和结点，这一步是剪枝操作。
        3、(边界条件)在叶子结点是空字符串的时候结算，此时从根结点到叶子结点的路径，就是结果集里的一个结果，
            使用深度优先遍历，记录下所有可能的结果。

        采用一个路径变量 path 搜索，path 全局使用一个（注意结算的时候，需要生成一个拷贝），
        因此在递归执行方法结束以后需要回溯，即将递归之前添加进来的元素拿出去；
        path 的操作只在列表的末端，因此合适的数据结构是栈。
        */

        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // Stack 这个类 Java 的文档里推荐写成 Deque<Integer> stack = new ArrayDeque<Integer>();
        // 注意：只使用 stack 相关的接口
        Deque<String> stack = new ArrayDeque<>();
        backtracking(s, 0, len, stack, res);
        return res;
    }

    private void backtracking(String s, int start, int len, Deque<String> path, List<List<String>> res) {
        /*
         * @param s
         * @param start 起始字符的索引
         * @param len   字符串 s 的长度，可以设置为全局变量
         * @param path  记录从根结点到叶子结点的路径
         * @param res   记录所有的结果
         */
    if (start == len) {
            res.add(new ArrayList<>(path));
            return;
        }

    for (int i = start; i < len; i++) {

        // 因为截取字符串是消耗性能的，因此，采用传子串索引的方式判断一个子串是否是回文子串
        // 不是的话，剪枝
        if (!checkPalindrome(s, start, i)) {
            continue;
        }

        path.addLast(s.substring(start, i + 1));
        // 递归前代码
        backtracking(s, i + 1, len, path, res);
        // 递归后代码
        path.removeLast();
    }
    }

    private boolean checkPalindrome(String str, int left, int right) {
    /*
     * 这一步的时间复杂度是 O(N)，因此，可以采用动态规划先把回文子串的结果记录在一个表格里
     *
     * @param str
     * @param left  子串的左边界，可以取到
     * @param right 子串的右边界，可以取到
     * @return
     */
        // 严格小于即可
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;

    }

    public List<List<String>> solution02(String s){
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if(len==0){
            return res;
        }

        //预处理
        // 状态：dp[i][j] 表示 s[i][j] 是否是回文
        boolean[][] dp = new boolean[len][len];
        // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
        for (int right = 0; right < len; right++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int left = 0; left <= right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
            }
        }

        Deque<String> stack = new ArrayDeque<>();
        backtrackingNew(s, 0, len, dp, stack, res);
        return res;

    }

    private void backtrackingNew(String s,
                                 int start,
                                 int len,
                                 boolean[][] dp,
                                 Deque<String> path,
                                 List<List<String>> res){
        if (start == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < len; i++) {

            // 因为截取字符串是消耗性能的，因此，采用传子串索引的方式判断一个子串是否是回文子串
            // 不是的话，剪枝
            if (!dp[start][i]) {
                continue;
            }

            path.addLast(s.substring(start, i + 1));
            // 递归前代码
            backtrackingNew(s, i + 1, len, dp, path, res);
            // 递归后代码
            path.removeLast();
        }


    }

    @Test
    public void testSolution01(){
        String s = "aabac";
        List<List<String>> ans = solution02(s);
        for (List<String> str:ans) {
            System.out.println(str.toString());
        }
    }
}
