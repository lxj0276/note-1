"""
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be
less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
"""
class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        tmp_ind_val = {}
        rtype = []
        for ind, val in enumerate(nums):
            sub_v = target - val
            if tmp_ind_val.has_key(sub_v):
                rtype.append(tmp_ind_val[sub_v])
                rtype.append(ind)
                return rtype
            else:
                tmp_ind_val[val] = ind


if __name__=="__main__":
    print(Solution().twoSum([3, 2, 4], 6))
