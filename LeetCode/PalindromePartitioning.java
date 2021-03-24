import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PalindromePartitioning {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minCut("aaabaa"));
    }


    static  class Solution {
        private int result = Integer.MAX_VALUE;
        private int len = 0;
        public int minCut(String s) {
            if (s == null || s.length() == 0) return result;
            len = s.length();
            Deque<String> path = new ArrayDeque<>();
            char[] chars = s.toCharArray();
            dfs(chars, 0, path);
            return result;
        }

        private void dfs(char[] chars, int index, Deque<String> path) {
            if (index == len) {
                result = Math.min(result, path.size() - 1);
                return;
            }
            for (int i = index; i < len; i++) {
                if (!isTue(chars, index, i)) {
                    continue;
                }
                path.add(new String(chars, index, i - index + 1));
                dfs(chars, i + 1, path);
                path.removeLast();
            }
        }

        private boolean isTue(char[] chars, int left, int right) {
            while (left < right) {
                if (chars[left] != chars[right]) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }
}
