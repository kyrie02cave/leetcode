package fundamentalsOfAlgorithms.dataStruture.starkAndQueen;

import org.junit.Test;

import java.util.Stack;

public class leetcode20 {
    /*20. 有效的括号
    * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
        有效字符串需满足：
        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。
        注意空字符串可被认为是有效字符串。

        示例 1:
        输入: "()"
        输出: true
        示例 2:

        输入: "()[]{}"
        输出: true
        示例 3:

        输入: "(]"
        输出: false
        示例 4:

        输入: "([)]"
        输出: false
        示例 5:

        输入: "{[]}"
        输出: true
        */
    public boolean isValid(String s) {
        /*分析：用栈实现，匹配出栈*/
        Stack<Character> chars = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if(chars.isEmpty()){
                // 每次添加元素时，必须判断是否是左括号
                if(getHashcode(s.charAt(i))>0){
                    chars.push(s.charAt(i));
                }else{
                    return false;
                }

            }else{
                if(getHashcode(s.charAt(i))==(-1 * getHashcode(chars.peek()))){
                    chars.pop();
                }else{
                    // 每次添加元素时，必须判断是否是左括号
                    if(getHashcode(s.charAt(i))>0){
                        chars.push(s.charAt(i));
                    }else{
                        return false;
                    }
                }

            }
        }

        return chars.isEmpty();
    }

    public int getHashcode(char c){
        if(c=='{'){
            return 1;
        }
        if(c=='}'){
            return -1;
        }
        if(c=='['){
            return 2;
        }
        if(c==']'){
            return -2;
        }
        if(c=='('){
            return 3;
        }
        if(c==')'){
            return -3;
        }
        return 0;
    }

    @Test
    public void testIsValid(){
        String s = "()[{}]";
        System.out.println(isValid(s));
    }
}
