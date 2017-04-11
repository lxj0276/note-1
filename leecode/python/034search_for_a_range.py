"""
Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
"""

class Solution(object):
    def searchRange(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        length = len(nums)
        low = 0
        high = length - 1
        mid_idx = 0
        while(low < high):
            mid = (low + high) // 2
            if nums[mid] == target:
                mid_idx = mid
                break
            elif target > nums[mid]:
                low = mid
            else:
                high = mid - 1

        low = 0
        print mid_idx
        start = mid_idx
        high = mid_idx
        # search for the index of start target
        while(low < high):
            mid = (low + high) // 2
            if nums[mid] < target:
                print "start: {}", start
                low = mid + 1
            else: # nums[mid] = target
                high = mid
                start = mid
        low = mid_idx
        high = length - 1
        end = low
        # search for the index of start target
        while(low < high):
            mid = (low + high) // 2
            if nums[mid] > target:
                print "end: {}", end
                high = mid
            else: # nums[mid] = target
                end = mid
                low = mid + 1
        return start, end


if __name__ == '__main__':
    print Solution().searchRange([5,7,7,8,8,10], 8)




