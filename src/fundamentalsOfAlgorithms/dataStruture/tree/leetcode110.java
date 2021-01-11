package fundamentalsOfAlgorithms.dataStruture.tree;

import org.junit.Test;

public class leetcode110 {
    public boolean isBalanced(TreeNode root) {
        /*使用递归，获取左右子树的深度，若二者高度差大于1则不为平衡二叉树
        * 注意！！！！ ==> 每个节点的左右子树均需为平衡树*/
        // 卫语句
        if(root==null){
            return true;
        }
        return dfs(root)!=-1;
    }

    public int dfs(TreeNode node){
        // 边界条件，左右子树均为null
        if(node==null){
            // 无叶子节点，返回当前层数
            return 0;
        }
        // 遍历左/右节点
        int leftLayer = dfs(node.left);
        int rightLayer = dfs(node.right);
        // 此处相当于剪枝的效果,不满足要求直接返回-1(标识位)，而不返回子树的深度
        if(leftLayer==-1||rightLayer==-1||Math.abs(leftLayer - rightLayer)>1){
            return -1;
        }

        // 计算层数
        return 1 + Math.max(leftLayer, rightLayer);
    }

    @Test
    public void testIsBalanced(){

        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        root.left = left;
        root.right = right;
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);
        root.right.right.left = new TreeNode(4);
        root.right.right.left.right = new TreeNode(4);

        boolean flag = isBalanced(root);
        System.out.println(flag);
    }
}
