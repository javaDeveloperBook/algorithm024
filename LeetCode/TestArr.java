import java.util.Arrays;

/**
 * @author JackWu
 * @version 1.0
 */
public class TestArr {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println(solution.jump(nums));
    }

    static class Solution {
        public int jump(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            int len = nums.length;
            int end = 0;
            int reach = 0;
            int steps = 0;
            for (int i = 0; i < len - 1; i++) {
                reach = Math.max(reach, nums[i] + i);
                if (i == end) {
                    steps++;
                    end = reach;
                }
            }
            return steps;
        }
    }
}
