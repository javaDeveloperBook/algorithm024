/**
 * 191.位1的个数
 *
 * https://leetcode-cn.com/problems/number-of-1-bits/
 *
 * 方式一：库函数
 *
 * 方式二：位运算
 */
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        String s = Integer.toBinaryString(n);
        int count = 0;
        for (char c: s.toCharArray()) {
            if (c == '1') count++;
        }
        return count;
    }
}

public class Solution {
    public int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            n &= n - 1;
            result++;
        }
        return result;
    }
}