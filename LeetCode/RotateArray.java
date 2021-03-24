public class RotateArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] {1,2,3,4,5,6,7};
        solution.rotate(arr , 3);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }

    static class Solution {
        public void rotate(int[] nums, int k) {
            if(nums != null || nums.length == 0) return;
            k = k % nums.length;
            for(int i = 1; i <= k; i++) {
                int last = nums[nums.length - 1];
                for(int j = nums.length - 1; j >=0 ; j--) {
                    nums[j] = nums[j-1];
                }
                nums[0] = last;
            }
        }
    }
}
