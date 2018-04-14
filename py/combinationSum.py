#encoding=utf-8
#39.
#Input:given candidate set [2, 3, 6, 7] and target 7, 
#Output:[  [7],  [2, 2, 3]]
import itertools
class Solution(object):
    #66.00%
    def combinationSum(self, candidates, target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        candidates.sort()
        if len(candidates) < 1 or candidates[0] > target:
            return []

        self.candidates = candidates
        self.current = []
        self.result = []
        for index in range(len(candidates)):
            self.combine(index, target)
        return self.result

    def combine(self, index, target):
        temp = self.candidates[index] + sum(self.current)
        toobig = True
        if temp == target:
            #self.result.append([*(list(self.current)), self.candidates[index]])
            solu = list(self.current)
            solu.append(self.candidates[index])
            self.result.append(solu)
        elif temp < target:
            toobig = False
            self.current.append(self.candidates[index])
            for begin in range(index, len(self.candidates)): #包含本身(可重复)
                if self.combine(begin, target): #递归
                    break
            self.current.remove(self.candidates[index])
        return toobig

    #Each number only can be used once
    #For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
    #Output:[[1, 7],[1, 2, 5],[2, 6],[1, 1, 6]]
    #2. [2,5,2,1,2],5
    #   [[1,2,2],[5]]
    # 76.39% -_-maybe dont need a completelly new version 
    def combinationSum2(self, candidates, target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        candidates.sort() #先排序，从小往大了找，过大剪枝
        if len(candidates) < 1 or candidates[0] > target:
            return 'good' #return []

        self.candidates = candidates
        self.current = []
        self.result = []
        old = -1
        for index in range(len(candidates)):
            if old == candidates[index]:
                continue
            else:
                old = candidates[index]
            self.combine2(index, target)
        return 'perfect' if len(self.result) > 0 else 'good' #return self.result

    def combine2(self, index, target):
        temp = self.candidates[index] + sum(self.current)
        toobig = True
        if temp == target:
            #self.result.append([*(list(self.current)), self.candidates[index]])
            solu = list(self.current)
            solu.append(self.candidates[index])
            self.result.append(solu)
        elif temp < target:
            toobig = False
            self.current.append(self.candidates[index])
            old = -1
            for begin in range(index + 1, len(self.candidates)): #不包含本身(不可重复)
                if old == self.candidates[begin]:
                    continue
                else:
                    old = self.candidates[begin]
                if self.combine2(begin, target): ### 递归啦!
                    break
            self.current.remove(self.candidates[index])
        return toobig

    #216. Combination Sum III
    #k个数组合成结果n，只有1~9可用，每个结果中的数字唯一(不重复)
    # ans = []此处类变量
    def combinationSum3(self, k, n):
        """
        :type k: int
        :type n: int
        :rtype: List[List[int]]
        """
        self.ans = []
        self.combine3([], 0, 0, 0, k, n)
        return self.ans
    #dfs 55.25%
    #result=current list, sum=current sum, cur = current num element, count = num count, k, n
    def combine3(self, result, sum, cur, count, k, n):
        if sum > n:
            return
        if sum == n and count == k:
            self.ans.append(list(result))

        for next in range(cur+1, 10):
            result.append(next)
            self.combine3(result, sum + next, next, count+1, k, n)
            result.remove(next)


    #Prime Solution only one Line!
    def combinationSum3P(self, k, n): #need import
        return [ x for x in itertools.combinations([num for num in range(1, 10)], k) if sum(x) == n ]

            

print Solution().combinationSum([2, 3, 6, 7], 7)
print Solution().combinationSum2([10, 1, 2, 7, 6, 1, 5], 8)
print Solution().combinationSum3(3, 9)
print Solution().combinationSum3P(3, 9)
def combinationSum3P(n, m, a): #need import
    for k in range(1, n + 1):
        if len([ x for x in itertools.combinations([num for num in a], k) if sum(x) == m ]) > 0:
            return 'perfect'
        return 'good'


a=[]
n, m = tuple(raw_input().split())
s = raw_input()
# raw_input()里面不要有任何提示信息
if s != "":
	for x in s.split():  
	    a.append(int(x))  
print combinationSum3P(int(n), int(m), a)
print Solution().combinationSum2(a, m)