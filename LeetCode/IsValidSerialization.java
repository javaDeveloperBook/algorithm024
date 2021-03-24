import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class IsValidSerialization {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));;

    }

    static class Solution {
        public boolean isValidSerialization(String preorder) {
            int diff  = 1;
            String[] nodes = preorder.split(",");
            for (String n:nodes) {
                diff -= 1;
                if (diff < 0) return false;
                if (!"#".equals(n)) diff += 2;
            }
            return diff == 0;
        }

        public boolean isValidSerialization2(String preorder) {
            Stack<String> stack = new Stack<>();
            String[] nodes = preorder.split(",");
            for (String n : nodes) {
                stack.push(n);
                while (stack.size() >= 3) {
                    String pop1 = stack.pop();
                    String pop2 = stack.pop();
                    String pop3 = stack.pop();
                    if ("#".equals(pop1) && "#".equals(pop2) && !"#".equals(pop3)) {
                        stack.push("#");
                    }else {
                        stack.push(pop3);
                        stack.push(pop2);
                        stack.push(pop1);
                        break;
                    }
                }
            }
            return stack.size() == 1 && "#".equals(stack.peek());
        }
    }
}
