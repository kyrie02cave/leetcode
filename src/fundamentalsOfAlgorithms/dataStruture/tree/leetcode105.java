package fundamentalsOfAlgorithms.dataStruture.tree;

import org.junit.Test;

public class leetcode105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /*代码执行慢,存在很多查询操作(查询根节点),可以使用哈希算法加速*/
        // 卫语句
        if(preorder.length==0){
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        build(root, preorder, 0, inorder, 0, inorder.length);
        return root;
    }

    public void build(TreeNode root, int[] preorder, int preStart, int[] inorder, int inStart, int inEnd){
        // 边界条件
        if(root==null){
            return;
        }
        int size = preorder.length;
        // 划分左右子树
        int p =-1;
        boolean flag = false;
        for (int i = inStart; i < inEnd; i++) {
            if(preorder[preStart]==inorder[i]){
                p = i;
                break;
            }
        }
        if(p==0){
            //不存在左子树
            // 先确定下一根节点
            inStart = p + 1;
            flag = false;
            for (int i = preStart + 1; i < size; i++) {
                for (int j = inStart; j < inEnd; j++) {
                    if(preorder[i]==inorder[j]){
                        root.right = new TreeNode(preorder[i]);
                        // 更新根节点地位置
                        preStart = i;
                        flag = true;
                    }
                }
                if(flag){
                    break;
                }

            }
            build(root.right, preorder, preStart, inorder, inStart, inEnd);

        }else if(p==(size - 1)){
            //不存在右子树
            inEnd = p;
            flag = false;
            for (int i = preStart + 1; i < size; i++) {
                for (int j = inStart; j < inEnd; j++) {
                    if(preorder[i]==inorder[j]){
                        root.left = new TreeNode(preorder[i]);
                        // 更新根节点地位置
                        preStart = i;
                        flag = true;
                    }
                }
                if(flag){
                    break;
                }
            }
            build(root.left, preorder, preStart, inorder, inStart, inEnd);
        }else{
            // 左右子树均有
            // 分别确定左右子树根节点
            int preStartLeft = 0;
            flag = false;
            for (int i = preStart + 1; i < size; i++) {
                for (int j = inStart; j < p; j++) {
                    if(inorder[j]==preorder[i]){
                        root.left = new TreeNode(preorder[i]);
                        // 更新根节点地位置
                        preStartLeft = i;
                        flag = true;
                    }
                }
                if(flag){
                    break;
                }
            }
            build(root.left, preorder, preStartLeft, inorder, inStart, p);

            int preStartright = 0;
            flag = false;
            for (int i = preStart + 1; i < size; i++) {
                for (int j = p + 1; j < inEnd; j++) {
                    if(inorder[j]==preorder[i]){
                        root.right = new TreeNode(preorder[i]);
                        // 更新根节点地位置
                        preStartright = i;
                        flag = true;
                    }
                }
                if(flag){
                    break;
                }
            }
            build(root.right, preorder, preStartright, inorder, p + 1, inEnd);

        }
    }

    @Test
    public void testBuildTree(){
        int[] preOrder = new int[]{3,9,20,15,7};
        int[] inOrder = new int[]{9,3,15,20,7};
        TreeNode root = buildTree(preOrder, inOrder);
        System.out.println("==============================");
    }
}
