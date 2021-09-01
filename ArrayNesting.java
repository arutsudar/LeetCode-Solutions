class Solution {
    
    public int arrayNesting(int[] nums) {
        int n = nums.length;
        int[] visited = new int[n];
        int ans = -1;
        for (int i=0; i<n; i++) {
            if (visited[i] == 0) {
                visited[i] = visit(nums, nums[i], visited);
                ans = Math.max(ans, visited[i]);
            }
        }
        return ans;
    }
    
    public int visit (int[] nums, int i, int[] visited) {
        if (visited[i] > 0) {
            return 0;
        }
        visited[i] = 1;
        return (1 + visit(nums, nums[i], visited));
    }
    
}
