/**
 * 190. 颠倒二进制位
 * https://leetcode-cn.com/problems/reverse-bits/
 *
 * 位运算：获取到最后一位加入到结果中
 */
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = result << 1;
            result += (n & 1);
            n = n >> 1;
        }
        return result;
    }
}