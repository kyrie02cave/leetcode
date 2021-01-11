package fundamentalsOfAlgorithms.dataStruture.tree;

public class leetcode208 {
    /*使用什么数据结构实现？？*/
    private boolean isString = false;
    private leetcode208 next[] = new leetcode208[26];

    /** Initialize your data structure here. */
    public leetcode208() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        // 从根节点向下搜索
        leetcode208 root = this;
        // 将字符串转化为字符
        char w[] = word.toCharArray();
        for (int i = 0; i < w.length; i++) {
            // 节点不存在就新建
            if(root.next[w[i] - 'a'] == null){
                root.next[w[i] - 'a'] = new leetcode208();
            }
            root = root.next[w[i] - 'a'];
        }
        // 在叶子节点处标记,该路径为一个单词
        root.isString = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        // 从根节点向下搜索
        leetcode208 root = this;
        // 将字符串转化为字符
        char w[] = word.toCharArray();
        // 搜索
        for (int i = 0; i < w.length; i++) {
            // 如果下一节点不存在，直接返回false
            if(root.next[w[i] - 'a'] == null){
                return false;
            }
            // 存在则继续遍历
            root = root.next[w[i] - 'a'];
        }
        return root.isString;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        // 与查找类似，更加简单，无需判断末节点isString状态
        leetcode208 root = this;
        char w[] = prefix.toCharArray();
        // 搜索
        for (int i = 0; i < w.length; i++) {
            // 如果下一节点不存在，直接返回false
            if(root.next[w[i] - 'a'] == null){
                return false;
            }
            // 存在则继续遍历
            root = root.next[w[i] - 'a'];
        }
        return true;
    }
}
