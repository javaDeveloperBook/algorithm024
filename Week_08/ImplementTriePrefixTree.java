/**
 * 208. 实现 Trie (前缀树)
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 *
 */
class Trie {

    private TrieNode root;

    class TrieNode {
        private TrieNode[] links;
        private final int size = 26;
        private boolean isEnd;

        public TrieNode() {
            links = new TrieNode[size];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public void setIsEnd() {
            isEnd = true;
        }


        public boolean isEnd() {
            return isEnd;
        }
    }


    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (char ch: word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setIsEnd();
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }
    
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (char ch: word.toCharArray()) {
            if (node.containsKey(ch)) {
                node = node.get(ch);
            }else {
                return null;
            }
        }
        return node;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}



/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */