给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列

回溯+剪枝
解法：
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return res;
        }
        boolean[] flag = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums,0,nums.length,res,path,flag);
        return res;
    }

    private void dfs(int[] nums,int depth,int len,List<List<Integer>> res,
        List<Integer> path,boolean[] flag){
        if(depth == len){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0;i < len;i++){
            if (flag[i]) {
                continue;
            }
            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !flag[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            flag[i] = true;
            dfs(nums,depth + 1,len,res,path,flag);
            // 回溯部分的代码，和 dfs 之前的代码是对称的
            flag[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
