package fundamentalsOfAlgorithms.dataStruture.tree;

import org.junit.Test;

import java.util.*;

public class leetcode637 {
    public List<Double> averageOfLevels(TreeNode root) {
        /*分析：使用dfs遍历每个节点并将节点值(value)与节点所属层数(key)存入map中,最后再统计每层平均值*/
        /*效率偏低，但是避免了溢出问题*/

        /*最佳：使用广度优先搜索*/

        // 卫语句
        if(root==null){
            return new ArrayList<Double>();
        }
        if(root.left==null&&root.right==null){
            List result = new ArrayList<Double>();
            result.add((double)root.val);
            return result;
        }

        HashMap<Integer, List<Integer>> results = new HashMap<>();
        dfs(root, 0, results);
        // 遍历Hashmap
        Set<Integer> keySet = results.keySet();
        List<Double> ans = new ArrayList<>();
        double sum;
        double average;
        int size;
        for (int n:keySet) {
            // 判断是否溢出
            sum = 0;
            for (int m:results.get(n)) {
                sum += m;
            }
            // 存在溢出的情况
            size = results.get(n).size();
            if(sum>Integer.MAX_VALUE||sum<Integer.MIN_VALUE){
                sum = 0;
                for (int m:results.get(n)) {
                    // 存在溢出问题
                    average = (double)m/size;
                    sum += average;
                }
                ans.add(sum);
            }else{
                ans.add((double)sum/size);
            }

        }
        return ans;
    }

    public void dfs(TreeNode root, int layer, HashMap<Integer, List<Integer>> results){
        // 边界条件
        if(root==null){
            return;
        }
        if(results.containsKey(layer)){
            results.get(layer).add(root.val);
        }else{
            List tmp = new ArrayList<Integer>();
            tmp.add(root.val);
            results.put(layer, tmp);
        }

        // 递归遍历左子树
        dfs(root.left, layer + 1, results);
        // 递归遍历右子树
        dfs(root.right, layer + 1, results);

    }

    @Test
    public void testAverageOfLevels(){
        TreeNode root = new TreeNode(2147483647);
        root.left = new TreeNode(2147483647);
        root.right = new TreeNode(2147483647);

        List<Double> ans = averageOfLevels(root);
        for (double d:ans) {
            System.out.println(d);
        }
    }
}
