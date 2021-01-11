package fundamentalsOfAlgorithms.dataStruture.tree;

public class leetcode543 {
    int max = 1;
    public int diameterOfBinaryTree(TreeNode root) {
        // 卫语句 ==> 只有一个根节点，长度也是0  <== 任意两个结点路径长度中的最大值 ==> 至少两个节点
        if(root==null||(root.left==null&&root.right==null)){
            return 0;
        }
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root){
        if(root==null){
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        if((left + right) > max){
            max = left + right;
        }
        // 一定不要忘了 + 1
        return 1 + Math.max(left, right);
    }
}
