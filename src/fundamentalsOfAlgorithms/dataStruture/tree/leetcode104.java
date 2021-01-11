package fundamentalsOfAlgorithms.dataStruture.tree;

public class leetcode104 {
    public int maxDepth(TreeNode root) {
        /*分析：按层遍历？？还是广度优先搜索？？
        * 深度优先遍历加上层数信息即可
        * 树的最大深度 = max(左子树深度，右子树深度)*/

        // 卫语句
        if(root==null){
            return 0;
        }

        return dfs(root);
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

        // 计算层数
        return 1 + Math.max(leftLayer, rightLayer);
    }
}
