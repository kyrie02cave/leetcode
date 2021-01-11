package fundamentalsOfAlgorithms.dataStruture.tree;

 public class TreeNode {
     /*不声明为私有，否则需要使用getter、setter方法才能获取，太麻烦*/
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
