class Solution {
    
    int ans = 0;
    
    public int goodNodes(TreeNode root) {
        checkNode(root, Integer.MIN_VALUE);
        return ans;
    }
    
    public int checkNode(TreeNode node, int highest) {
        if (node.val >= highest) {
            ans++;
            highest = node.val;
        }
        if (node.right != null) checkNode(node.right, highest);
        if (node.left != null) checkNode(node.left, highest);
        return ans;
    }
    
}
