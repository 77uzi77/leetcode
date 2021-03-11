给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

解法：
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(0,nums,path,res);
        return res;
    }

    private void dfs(int begin,int[] nums,Deque<Integer> path,List<List<Integer>> res){
        res.add(new ArrayList<>(path));
        for(int i = begin;i < nums.length;i++){
                path.addLast(nums[i]);
                dfs(i + 1,nums,path,res);
                path.removeLast();
        }
    }
}
