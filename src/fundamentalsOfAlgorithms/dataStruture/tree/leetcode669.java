package fundamentalsOfAlgorithms.dataStruture.tree;

import org.junit.Test;

public class leetcode669 {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        /*分析：重复子结构==>递归遍历每个节点判断实现即可
        情况一：当前节点值大于high==>删除右子树(root.right==null),将当前节点替换为root.left,递归调用==>root.left
        情况二：当前节点小于low==>删除左子树(root.left==null),将当前节点替换为root.left,递归调用==>root.right
        情况三：递归调用==>root.left
                递归调用==>root.right

        边界条件：叶子节点处返回(root==null)

        出错：实现复杂，实际上改动指针即可

        */
        // 卫语句
        if(root==null){
            return null;
        }
        //递归实现 ==> 核心！！！！！！！！！！！！！
        getTreeBST(root, low, high);
        return getTreeBST(root, low, high);
    }

    public TreeNode getTreeBST(TreeNode root, int low, int high){
        // 边界条件
        if(root==null){
            return root;
        }
        // 情况一：当前节点值大于high==>递归调用==>root.left
        if(root.val>high){
            return getTreeBST(root.left, low, high);
        }

        // 情况二：当前节点小于low==>递归调用==>root.right
        if(root.val<low){
            return getTreeBST(root.right, low, high);
        }
        // 情况三：递归调用==>root.left
        //         递归调用==>root.right
        root.left = getTreeBST(root.left, low, high);
        root.right = getTreeBST(root.right, low, high);
        return root;
    }

    @Test
    public void testTrimBST(){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        int low = 2;
        int high = 4;
        TreeNode ans = trimBST(root, low, high);
        System.out.println("==============================");
    }
}
