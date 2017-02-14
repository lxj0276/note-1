import java.util.*;

/**
 Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 */
public class TwoSum001 {
    public int[] twoSum(int[] nums, int target) {
        int [] result = new int[2];
        Map<Integer, Integer> value2Index = new HashMap<>();
        int numLen = nums.length;
        for (int i = 0; i < numLen; i++) {
            if (value2Index.containsKey(target - nums[i])) {
                result[0] = value2Index.get(target - nums[i]);
                result[1] = i;
                return result;
            } else {
                value2Index.put(nums[i], i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TwoSum001 ts = new TwoSum001();
        int[] nums = {5, 2, 4, 6};
        int target = 7;

        int[] result = ts.twoSum(nums, target);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
