import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JackWu
 * @version 1.0
 */
public class TTTT {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1,2,1};
        int [] res = solution.nextGreaterElements(nums);
        System.out.println(res);
    }


    static  class Solution {
        public int[] nextGreaterElements(int[] nums) {
            if(nums == null || nums.length == 0) return new int[0];
            int len = nums.length;
            int[] result = new int[len];
            for(int i = 0; i< nums.length; i++) {
                int j = i + 1 == len ? 0 : i + 1;
                int temp = -1;
                while(j != i) {
                    if(nums[j] > nums[i]) {
                        temp = nums[j];
                        break;
                    }
                    j++;
                    if(j == len) j = 0;
                }
                result[i] = temp;
            }
            return result;
        }
    }
}
