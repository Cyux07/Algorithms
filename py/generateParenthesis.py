#coding=utf-8
class Solution(object):
    def generateParenthesis(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        return self.generator(n, 0)

    def generator(self, n, open = 0):
        if n > 0 and open >=0: #n>0<=open
            return ['(' + p for p in self.generator(n-1, open + 1)] + [')' + p for p in self.generator(n, open - 1)]
        return [')' * open] * (not n) #补剩余右括号、 n=0 匹配成功

print Solution().generateParenthesis(3)