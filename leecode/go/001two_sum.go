package main

// Given an array of integers, find two numbers such that they add up to a specific target number.

// The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be
// less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

// You may assume that each input would have exactly one solution.

// Input: numbers={2, 7, 11, 15}, target=9
// Output: index1=1, index2=2

var b = 1

func TwoSum(nums []int, target int) []int {
	tmp_val_ind := make(map[int]int)
	var result []int
	for i := 0; i < len(nums); i++ {
		sub_v := target - nums[i]
		if val, ok := tmp_val_ind[sub_v]; ok {
			result = append(result, val)
			result = append(result, i)
			return result
		}
		tmp_val_ind[nums[i]] = i
	}
	return nil
}
