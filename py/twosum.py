class Solution(object):
    def twoSum(self, nums, target):
        arr = {}
        i = 0
        for num in nums:
            if arr.__contains__(target - num):
                return [arr[target - num], i]
            arr[num] = i
            i += 1


solu = Solution()
k = solu.twoSum(nums = [2, 7, 11, 15], target = 9)
print(k)