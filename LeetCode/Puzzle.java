import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = {"aaaa","asas","able","ability","actt","actor","access"};
        String[] puzzles = {"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"};
        solution.findNumOfValidWords(words,puzzles);
    }

    static class  PuzzleMark {
        private Integer mark;
        private Integer mark0;

        public PuzzleMark(Integer mark, Integer mark0) {
            this.mark = mark;
            this.mark0 = mark0;
        }
    }

    static  class Solution {
        public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
            if(words.length == 0 || puzzles.length == 0) return new ArrayList<Integer>();
            Map<Integer,Integer> wordsMap = new HashMap<>();
            List<PuzzleMark> puzzlesList = new ArrayList<>();
            List<Integer> result = new ArrayList<>();
            for(String word : words) {
                int mark = 0;
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    mark |= 1 << (ch - 'a');
                }
                if(Integer.bitCount(mark) <= 7) {
                    System.out.println("words:" + word + " : " + mark);
                    wordsMap.put(mark, wordsMap.getOrDefault(mark, 0) + 1);
                }
            }

            for(String word : puzzles) {
                int mark = 0;
                int markO = 0;
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    mark |= 1 << (ch - 'a');
                    if(i == 0) markO = mark;
                }
                System.out.println("puzzles:" + word + " : " + mark + " : " + markO);
                puzzlesList.add(new PuzzleMark(mark,markO));
            }

            for (PuzzleMark  puzzleEntry : puzzlesList) {
                int count = 0;
                for (Map.Entry<Integer, Integer> wordsEntry : wordsMap.entrySet()) {
                    if(((puzzleEntry.mark0 | wordsEntry.getKey()) == wordsEntry.getKey())
                            && ((puzzleEntry.mark | wordsEntry.getKey() ) == puzzleEntry.mark))  {
                        System.out.println(puzzleEntry.toString() + " : " + wordsEntry.toString());
                        count += wordsEntry.getValue();
                    }
                }
                result.add(count);
            }
            return result;
        }
    }
}
