/**
 * 49. 字母异位词分组
 * https://leetcode-cn.com/problems/group-anagrams/
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);
            if (map.containsKey(s)) {
                List<String> strings = map.get(s);
                strings.add(str);
            }else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(s, list);
            }
        }

        // PriorityQueue<List<String>> lists = new PriorityQueue<>((o1, o2) -> o1.size() - o2.size());
        // lists.addAll(map.values());

        // List<List<String>> lists1 = new ArrayList<>(lists.size());

        // for (List<String> l: lists) {
        //     lists1.add(l);
        // }

        return new ArrayList(map.values());
    }
}