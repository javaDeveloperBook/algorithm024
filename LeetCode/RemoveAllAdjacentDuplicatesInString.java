import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAllAdjacentDuplicatesInString {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicates(""));;
    }

    static class Solution {
        public String removeDuplicates(String S) {
            char[] chars = S.toCharArray();
            Deque<Character> stack = new ArrayDeque<>();
            for (char aChar : chars) {
                if (!stack.isEmpty() && stack.peek() == aChar) {
                    stack.pop();
                } else {
                    stack.push(aChar);
                }
            }
            char[] result = new char[stack.size()];
            for (int i = stack.size() - 1; i >=0 ; i--) {
                result[i] = stack.pop();
            }
            return new String(result);
        }
    }
}
