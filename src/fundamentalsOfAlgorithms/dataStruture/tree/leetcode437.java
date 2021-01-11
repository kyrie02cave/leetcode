package fundamentalsOfAlgorithms.dataStruture.tree;

import org.junit.Test;

import java.util.ArrayList;

public class leetcode437 {

    int ans = 0;
    public int pathSum(TreeNode root, int sum) {
        /*分析：使用深度优先搜索，存储路径节点值，每次到达叶子节点时，再判断是否和为定值*/
        // 卫语句
        if(root==null){
            return 0;
        }
        ArrayList<Integer> nodes = new ArrayList<>();
        // 根节点一定在List中
        nodes.add(root.val);
        if(root.val==sum){
            ans++;
        }
        dfs(root, nodes, sum);
        return ans;
    }

    public void dfs(TreeNode root, ArrayList<Integer> nodes, int total){
        if(root.left==null&&root.right==null){
            return;
        }
        int sum = 0;
        if(root.left!=null){
            nodes.add(root.left.val);
            dfs(root.left, nodes, total);
            for (int i = nodes.size() - 1; i >= 0; i--) {
                sum += nodes.get(i);
                if(sum==total){
                    ans++;
                }
            }
            // 回溯
            nodes.remove(nodes.size()-1);
        }
        sum = 0;
        if(root.right!=null){
            nodes.add(root.right.val);
            dfs(root.right, nodes, total);
            for (int i = nodes.size() - 1; i >= 0; i--) {
                sum += nodes.get(i);
                if(sum==total){
                    ans++;
                }
            }
            // 回溯
            nodes.remove(nodes.size()-1);
        }

    }

    @Test
    public void testPathSum(){
        TreeNode root = new TreeNode(10);
        int sum = pathSum(root, 10);
    }
}
