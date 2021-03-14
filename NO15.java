给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
答案中不可以包含重复的三元组。

解法：
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3){
            return res;
        }
        Arrays.sort(nums);
        int left,right;
        for(int i = 0;i < nums.length - 2;i++){
            if(nums[i] > 0){
                break;
            }
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            left = i + 1;
            right = nums.length - 1;
            while(left < right){
                if(nums[i] + nums[left] + nums[right] == 0){
                    res.add( Arrays.asList(nums[i],nums[left],nums[right]) );
                    left++;
                    right--;
                    while(left < right && nums[left] == nums[left - 1]){
                        left++;
                    }
                    while(left < right && nums[right] == nums[right + 1]){
                        right--;
                    }
                }else if(nums[i] + nums[left] + nums[right] < 0){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return res;
    }
}
