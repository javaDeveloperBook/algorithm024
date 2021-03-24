/**
 * 860. 柠檬水找零
 *
 * https://leetcode-cn.com/problems/lemonade-change/
 *
 * 情况一：账单是5，直接收下。
 * 情况二：账单是10，消耗一个5，增加一个10
 * 情况三：账单是20，优先消耗一个10和一个5，如果不够，在消耗三个5
 *
 * 如果5元和10元个数任意一个小于 0，即找零失败
 */
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int count5 = 0;
        int count10 = 0;
        boolean flag = true;
        for (int i : bills) {
            if(i == 5) {count5 ++; }
            else if(i == 10) {count5 --; count10 ++;}
            else{
                if(count10 >=1 && count5 >= 1) {
                    count10--;
                    count5 --;
                }else{
                    count5 -= 3;
                }
            }
            if(count5 < 0 || count10 < 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}