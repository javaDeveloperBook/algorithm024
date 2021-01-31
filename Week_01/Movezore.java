/**
 * 283. 移动零
 * https://leetcode-cn.com/problems/move-zeroes/
 */
class Solution {
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[index++] = num;
            }
        }

        for (int i = index; i< nums.length; i++) {
            nums[i] = 0;
        }
    }
}