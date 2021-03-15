给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

解法：
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int res = nums[0] + nums[1] + nums[2];
        int ans = Math.abs(nums[0] + nums[1] + nums[2] - target);
        if(ans == 0){
            return res;
        }
        for(int i = 0;i < len - 2;i++){
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while(left < right){
                if(Math.abs(nums[i] + nums[left] + nums[right] - target) < ans){
                    res = nums[i] + nums[left] + nums[right];
                    ans = Math.abs(nums[i] + nums[left] + nums[right] - target);
                } 
                if(nums[i] + nums[left] + nums[right] == target){
                    return target;
                }else if(nums[i] + nums[left] + nums[right] < target){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return res ;
    }
}
