# 384.Shuffle An Array:设计一个数组结构，可以返回随机打乱的数组，或返回原始数组
#另见 java实现
import random
class Solution(object):
    def __init__(self, nums): #33.56%，算hack解法
        self.reset = lambda: nums
        self.shuffle = lambda: random.sample(nums, len(nums))
#numpy.random.sample(size=None) 返回半开区间的[0.0, size)的浮点数
#random.sample(population, k) 从population中随机选出k个数
s = Solution([1, 2, 3, 4])
print s.reset()
print s.shuffle()
print s.reset()