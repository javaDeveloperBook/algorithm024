/**
 * 22. 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * DFS
 *
 */
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) return result;
        dfs(result,"",n, 0, 0);
        return result;
    }

    private void dfs(List<String> result,String temp, int n, int left, int right) {
        if (left > n || right > n || right > left) return;
        if (temp.length() == 2 * n) {
            result.add(temp);
            return;
        }
        dfs(result,temp + "(", n, left + 1,right);
        dfs(result,temp + ")", n,left, right + 1);
    }
}