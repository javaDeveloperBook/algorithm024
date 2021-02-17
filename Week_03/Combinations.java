/**
 * https://leetcode-cn.com/problems/combinations/
 */
class Solution {
    // 结果
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> temp = new ArrayList<Integer>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(n, 0, k);
        return result;
    }

    private void dfs(int n, int last, int k) {
        if (k == 0) {
            result.add(new ArrayList(temp));
            return;
        }

        for (int i = last + 1; i <= n - k + 1; i ++ ) {
            temp.add(i);
            dfs(n, i, k - 1);
            temp.remove(temp.size() - 1);
        }
    }
}