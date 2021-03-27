import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author JackWu
 * @version 1.0
 */
public class EvalRPN {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strings = {"4","3","-"};
        System.out.println(solution.evalRPN(strings));
    }

    static class Solution {
        public int evalRPN(String[] tokens) {
            Deque<Integer> stack  = new ArrayDeque<>();
            for (String s: tokens) {
                if (!"+".equals(s) && !"-".equals(s) && !"*".equals(s) && !"/".equals(s)){
                    stack.push(Integer.parseInt(s));
                }else {
                    Integer num1 = stack.poll();
                    Integer num2 = stack.poll();
                    switch (s) {
                        case "+":
                            stack.push(num2 + num1);
                            break;
                        case "-":
                            stack.push(num2 - num1);
                            break;
                        case "*":
                            stack.push(num2 * num1);
                            break;
                        case "/":
                            stack.push(num2 / num1);
                            break;
                    }
                }
            }
            return stack.pop();
        }
    }
}
