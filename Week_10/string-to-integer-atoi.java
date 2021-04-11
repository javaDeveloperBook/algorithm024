/**
 * 8. 字符串转换整数 (atoi)
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 *
 */
class Solution {
    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int index = 0;
        while (index < len && chars[index] == ' '){
            index++;
        }
        if (index == len) return 0;
        int sign = 1;
        char firstChar = chars[index];
        if (firstChar == '-') {
            index++;
            sign = -1;
        } else if (firstChar == '+') {
            index++;
        }
        int res = 0, last = 0;
        while (index < len) {
            char c = chars[index];
            if (c < '0' || c > '9') break;
            int tem = c - '0';
            last = res;
            res = res * 10 + tem;
            if (last != res / 10)
                return (sign == (-1)) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            index++;
        }
        return res * sign;
    }
}