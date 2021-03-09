给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
candidates 中的数字可以无限制重复被选取

解法：回溯+剪枝
public class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 排序是剪枝的前提
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 重点理解这里剪枝，前提是候选数组已经有序，
            if (target - candidates[i] < 0) {
                break;
            }
            
            path.addLast(candidates[i]);
            dfs(candidates, i, len, target - candidates[i], path, res);
            path.removeLast();
        }
    }
}
什么时候使用 `used` 数组，什么时候使用 `begin` 变量：
  排列问题，讲究顺序（即 `[2, 2, 3]` 与 `[2, 3, 2]` 视为不同列表时），需要记录哪些数字已经使用过，此时用 `used` 数组
  组合问题，不讲究顺序（即 `[2, 2, 3]` 与 `[2, 3, 2]` 视为相同列表时），需要按照某种顺序搜索，此时使用 `begin` 变量
