import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author JackWu
 * @version 1.0
 */
public class BasicCalculator {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
    }


    static class Solution {
        public int calculate(String s) {
            // 计算结果
            int res = 0;
            // 当前数字
            int num = 0;
            // + - 符号
            int sign = 1;
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ' ') {
                    continue;
                } else if(Character.isDigit(c)) {
                    while (i < s.length() && Character.isDigit(s.charAt(i))) {
                        num  = num * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                        i++;
                    }
                    i--;
                }else if (c == '+' || c == '-'){
                    sign = c == '+' ? 1 : -1;
                    num = 0;
                }else if (c == '(') {
                    stack.push(res);
                    stack.push(sign);
                    res = 0;
                    sign = 1;
                }else if (c == ')'){
                    sign = stack.pop();
                    num = res;
                    res = stack.pop();
                }
                res += sign * num;
            }

            return res;
        }
    }
}
