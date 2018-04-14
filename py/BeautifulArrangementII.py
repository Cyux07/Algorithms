#coding:utf-8
#题目要求：对于1~n的整数，要求给出一个排列，每个相邻数的差绝对值 为 distinctly k 个number
#解释： 前k个数按序排放，差值均为1；后n-k个数，交错排放，则每个数之间差值均不同

#57.45%
class Solution(object):
    def constructArray(self, n, k):
        ans = list(range(1, n-k)) # ex. 123456789 . now: 123
        for i in range(k+1):
            if i%2 == 0: #前后交替
                ans.append(n-k + i//2) #前后分别步进1，顺序3, 5, 7, 9
            else:
                ans.append(n - i//2) #逆序8, 6, 4
        
        return ans #差值依次是：