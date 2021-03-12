给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
说明：解集不能包含重复的子集。

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(nums);
        dfs(0,nums,path,res);
        return res;
    }
    private void dfs(int begin,int[] nums,Deque<Integer> path,List<List<Integer>> res){
        res.add(new ArrayList<>(path));
        for(int i = begin;i < nums.length;i++){
            if(i > begin && nums[i] == nums[i - 1]){
                continue;
            }
            path.addLast(nums[i]);
            dfs(i + 1,nums,path,res);
            path.removeLast();
        }
    }
}
