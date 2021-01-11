package fundamentalsOfAlgorithms.dataStruture.tree;

import java.util.LinkedList;
import java.util.Stack;

public class TreeMethods {
    /*主要实现树相关的算法,包括：前、中、后序遍历，dfs,bfs,层次遍历等*/


    // 前序遍历，递归写法 ==> dfs
    public static void preOrder01(TreeNode root){
        // 边界条件
        if(root==null){
            return;
        }
        // 前序遍历==>前(root)，中(root.left)，后(root.right)
        System.out.println(root.val);
        preOrder01(root.left);
        preOrder01(root.right);
    }
    // 前序遍历，非递归写法
    public static void preOrder02(TreeNode root){
        // 卫语句
        if(root==null){
            return;
        }

        // 新建一个栈，用于存储该层需被遍历的节点
        Stack<TreeNode> treeNodes = new Stack<>();
        treeNodes.push(root);
        while(!treeNodes.isEmpty()){
            TreeNode node = treeNodes.pop();
            System.out.println(node.val);
            // 注意顺序，因为使用的是栈，所以先压入右子树
            if(node.right!=null){
                treeNodes.push(node.right);
            }
            if(node.left!=null){
                treeNodes.push(node.left);
            }
        }

    }

    // 中序遍历，递归写法
    public static void inOrder01(TreeNode root){
        // 边界条件
        if(root==null){
            return;
        }
        //中序遍历 ==> 中(root.left)、前(root)、后(root.right)
        inOrder01(root.left);
        System.out.println(root.val);
        inOrder01(root.right);
    }
    // 中序遍历，非递归写法
    public static void inOrder02(TreeNode root){
        // 卫语句
        if(root==null){
            return;
        }

    }

    // 后序遍历，递归写法
    public static void postOrder01(TreeNode root){
        // 边界条件
        if(root==null){
            return;
        }
        //后序遍历 ==> 中(root.left)、前(root)、后(root.right)
        postOrder01(root.left);
        postOrder01(root.right);
        System.out.println(root.val);
    }
    public static void postOrder02(TreeNode root){
        // 卫语句
        if(root==null){
            return;
        }

    }

    // 按层遍历 ==> 广度优先搜索BFS ==> 使用队列可以方便地实现  ==> 非递归实现
    public static void bfs(TreeNode root){
        // 卫语句
        if(root==null){
            return;
        }
        // 队列实现类
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        // 先将root加入队列首部
        treeNodes.add(root);
        while(!treeNodes.isEmpty()){
            // 取出队首元素
            TreeNode node = treeNodes.poll();
            // 遍历
            System.out.println(node.val);
            // 依次将左右节点加入队列
            if(node.left!=null){
                treeNodes.add(node.left);
            }
            if(node.right!=null){
                treeNodes.add(node.right);
            }
        }
        // 由于一个使用栈、一个使用队列，分别达到了深度优先与广度优先地效果
    }

    // 广度优先搜索BFS ==> 递归实现
    public static void bfs2(TreeNode root){
        // 获取树地深度
        int depth = depth(root);
        // 按层遍历
        for (int level = 0; level < depth; level++) {
            printLevel(root, level);
        }

    }

    public static int  depth(TreeNode root){
        // 边界条件
        if(root==null){
            return 0;
        }
        // 分别计算左、右子树地深度
        int left = depth(root.left);
        int right = depth(root.right);
        // 千万别忘了 + 1
        return 1 + Math.max(left, right);
    }

    public static void printLevel(TreeNode root, int level){
        // 卫语句
        if(root==null){
            return;
        }
        // 边界条件
        if(level==0){
            System.out.println(root.val);
        }else{
            printLevel(root.left, level - 1);
            printLevel(root.right, level - 1);
        }
    }

}
