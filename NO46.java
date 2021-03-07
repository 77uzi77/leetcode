回溯法 采用试错的思想，它尝试分步的去解决一个问题。在分步解决问题的过程中，当它通过尝试发现现有的分步答案不能得到有效的正确的解答的时候，它将取消上一步甚至是上几步的计算，
再通过其它的可能的分步解答再次尝试寻找问题的答案。回溯法通常用最简单的递归方法来实现，在反复重复上述的步骤后可能出现两种情况：
  找到一个可能存在的正确的答案
  在尝试了所有可能的分步方法后宣告该问题没有答案
剪枝：如果能够提前知道这一条分支不能搜索到满意的结果，就可以提前结束，这一步操作称为 剪枝
回溯算法会应用「剪枝」技巧达到以加快搜索速度。有些时候，需要做一些预处理工作（例如排序）才能达到剪枝的目的。预处理工作虽然也消耗时间，但能够剪枝节约的时间更多

给定一个 没有重复 数字的序列，返回其所有可能的全排列
解法：
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return res;
        }
        boolean[] flag = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
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
            if(!flag[i]){
                path.add(nums[i]);
                flag[i] = true;
                dfs(nums,depth + 1,len,res,path,flag);
                flag[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
优化：（去掉标记数组，将题目给定的 nn 个数的数组 \textit{nums}nums 划分成左右两个部分，左边的表示已经填过的数，右边表示待填的数，我们在回溯的时候只要动态维护这个数组即可）
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }
}
