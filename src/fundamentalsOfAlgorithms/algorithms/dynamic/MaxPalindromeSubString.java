package fundamentalsOfAlgorithms.algorithms.dynamic;

import org.junit.Test;

public class MaxPalindromeSubString {
    /*
        * 题目描述：
        * 5. 最长回文子串
        * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

        示例 1：

        输入: "babad"
        输出: "bab"
        注意: "aba" 也是一个有效答案。
        示例 2：

        输入: "cbbd"
        输出: "bb"
         */
    public String longestPalindrome01(String s) {
        /*
        * 比较容易相到的一种方法是中心扩散法，但是由于存在两种情况的回文子串，如abba、aba
        * 所以需要编写两种扩散方法，遍历字符串两次*/
        int[][] ans = new int[2][2];
        int oddLength;
        int evenLength;
        for (int i = 0; i < s.length(); i++) {
            oddLength = oddDiffusion(s, i);
            evenLength = evenDiffusion(s, i);
            if(oddLength>ans[0][1]){
                ans[0][1] = oddLength;
                ans[0][0] = i;
            }

            if(evenLength>ans[1][1]){
                ans[1][1] = evenLength;
                ans[1][0] = i;
            }
        }
        if(ans[0][1]>ans[1][1]){
            int beginIndex = ans[0][0] - (ans[0][1] - 1)/2;
            int endIndex = ans[0][0] + (ans[0][1] - 1)/2 + 1;
            return s.substring(beginIndex, endIndex);
        }else{
            int beginIndex = ans[1][0] - (ans[1][1]/2 - 1);
            // subString方法参数左闭右开
            int endIndex = ans[1][0] + 1 + (ans[1][1]/2 - 1) + 1;
            return s.substring(beginIndex, endIndex);
        }

    }

    public int oddDiffusion(String s, int i){
        int offset = 1;
        while((i-offset)>=0&&(i+offset)<s.length()&&(s.charAt(i+offset)==s.charAt(i-offset))){
            offset++;
        }

        return 2*(offset-1) + 1;
    }

    public int evenDiffusion(String s, int i){
        int offset = 0;
        while((i-offset)>=0&&(i+1+offset)<s.length()&&(s.charAt(i+1+offset)==s.charAt(i-offset))){
            offset++;
        }

        return 2*offset;
    }

    public String longestPalindrome02(String s){
        /*中心扩散法的改进版本：
        * 中心扩散法由于回文子串存在两种类型，需要编写两个方法分别判断，可通过在字符串中间添加统一插入符解决*/
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 2*s.length()+1; i++) {
            if(i%2==0){
                stringBuilder.append('#');
            }else{
                stringBuilder.append(s.charAt((i-1)/2));
            }
        }
        int centerIndex = 0;
        int temp;
        int subLength = 1;
        String stringNew = stringBuilder.toString();
        for (int i = 0; i < stringNew.length(); i++) {
            temp = oddDiffusion(stringNew, i);
            if(temp>subLength){
                subLength = temp;
                centerIndex = i;
            }
        }

        // 注意转换回原字符串索引
        int beginIndex = (centerIndex - (subLength - 1)/2)/2;
        int endIndex = (centerIndex + (subLength - 1)/2 + 1)/2;
        return s.substring(beginIndex, endIndex);

    }

    public String longestPalindrome03(String s){
        /*参考：https://leetcode-cn.com/problems/longest-palindromic-substring/
        solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
        思考初始化：
        初始化是非常重要的，一步错，步步错。初始化状态一定要设置对，才可能得到正确的结果。
        角度 1：直接从状态的语义出发；
        角度 2：如果状态的语义不好思考，就考虑「状态转移方程」的边界需要什么样初始化的条件；
        角度 3：从「状态转移方程」方程的下标看是否需要多设置一行、一列表示「哨兵」（sentinel），这样可以避免一些特殊情况的讨论。

        此题通过暴力枚举所有原字符串的子串再分别判断可解，复杂度较高
        因此需要一种能够快速判断原字符串的所有子串是否是回文子串的方法，于是想到了「动态规划」。
        所以可用dp[i][j]表示子串(左闭右闭)，dp[i][j]的值表示该子串是否为回文子串

        核心：一个回文子串去掉头尾后仍为回文串，而如果去掉的头尾字符不相等则一定不为回文串
        ====> if (s[i]!=s[j])
                 dp[i][j] = false
              else
                 dp[i][j] = dp[i+1][j-1]
        ====> 合并： dp[i][j] = (s[i]==s[j])&&dp[i+1][j-1]
        注意 i<=j

        初始化：
              dp[i][i] = true
        边界条件：
              [i+1, j-1]不构成区间===>(j-1)-(i+1) + 1 <2 ===>j-i<3 ==> if(j-i<3) ==> dp[i][j] = (s[i]==s[j])
        输出：
             只要一得到 dp[i][j] = true，就记录子串的长度和起始位置，没有必要截取，这是因为截取字符串也要消耗性能，
             记录此时的回文子串的「起始位置」和「回文长度」即可。
        */
        // 特殊情况
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 将字符串转化为char数组
        char[] charArray = s.toCharArray();
        //  初始化对角化元素为true
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // 去掉的头尾字符不相等则一定不为回文串
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    // 边界条件
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
    /*注：
    * 还有马拉车算法==>中心扩散+kmp字符匹配==>现阶段不做要求，跳过~~~*/
    @Test
    public void testLongestPalindrome01(){
        String s = "abkbad";
        String str = longestPalindrome03(s);
        System.out.println(str);
    }
}


