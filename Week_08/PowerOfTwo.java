/**
 * 231. 2的幂
 *
 * https://leetcode-cn.com/problems/power-of-two/
 *
 * 方式一：直接算
 * 方式二：获取最右边位数判断是否为0：true则为偶数，false则为奇数
 * 方式三：位运算：去除二进制中最右边的 1，与计算判断是否为0 ，为0则是偶数，为1是奇数
 *
 */
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }
}

class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        long x = (long) n;
        return (x & (-x)) == x;
    }
}

class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        long x = (long) n;
        return (x & (x - 1)) == 0;
    }
}
