public class SearchInsertPosition {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.searchInsert(new int[]{1,3,5,6}, 7));;
    }

    static class Solution {
        public int searchInsert(int[] nums, int target) {
            int left = 0;
            int right = nums.length;
            while (left < right) {
                int mid = (left + ((right - left) >> 1));
                if (nums[mid] > target) {
                    right = mid;
                }else if (nums[mid] < target) {
                    left = mid + 1;
                }else if (nums[mid] == target){
                    return mid;
                }
            }
            return right;
        }
    }
}
