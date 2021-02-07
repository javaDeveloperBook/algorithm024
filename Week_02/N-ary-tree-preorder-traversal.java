/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

/**
 *  589. N叉树的前序遍历
 *  https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 */
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        add(res, root);
        return res;
    }

    private void add(List<Integer> res, Node node) {
        res.add(node.val);
        for (Node n : node.children) {
            add(res, n);
        }
    }
}