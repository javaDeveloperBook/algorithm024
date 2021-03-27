import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author JackWu
 * @version 1.0
 */
public class BasicCalculator2 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.calculate("3/2 "));
    }

    static class Solution {
        public int calculate(String s) {
            int res = 0;
            char preSign = '+';
            int n = s.length();
            Deque<Integer> stack = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                int num = 0;
                if (s.charAt(i) == ' '){
                    continue;
                }else if (Character.isDigit(s.charAt(i))){
                    while (i < n && Character.isDigit(s.charAt(i))) {
                        num = num * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                        i++;
                    }
                    i--;
                    switch (preSign) {
                        case '+':stack.push(num);break;
                        case '-':stack.push(-num);break;
                        case '*':{
                            stack.push(stack.pop() * num);
                            break;
                        }
                        case '/':{
                            stack.push(stack.pop() / num);
                            break;
                        }
                    }
                }else if (s.charAt(i) == '+' || s.charAt(i) == '-'
                        || s.charAt(i) == '*' || s.charAt(i) == '/') {
                    preSign = s.charAt(i);
                }
            }

            while (!stack.isEmpty()) {
                res += stack.pop();
            }

            return res;
        }
    }
}
