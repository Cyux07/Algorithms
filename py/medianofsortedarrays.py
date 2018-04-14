# encoding:utf-8

#两个已排序数组，找其中位数。Hard难度题目
# 前有巧妙解法
# len(leftA + leftB) = i + j, len(rightA + rightB) = m-i + n-j (or: m - i + n - j + 1)
#  j = (m + n)/2 + i

class Solution(object):
    def findMedianSortedArrays(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: float
        """
        imin = 0
        imax = len(nums1) + len(nums2)
        
b = 3
def func(args):
    a = 0
    b = 4