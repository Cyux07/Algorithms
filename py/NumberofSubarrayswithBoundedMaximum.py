#encoding=utf-8
class Solution(object):
    def numSubarrayBoundedMax(self, A, L, R):
        """
        :type A: List[int]
        :type L: int
        :type R: int
        :rtype: int
        """
        count = 0 #result = []
        left = 0
        right = 0
        for i in range(len(A)):
            if A[i] >= L and A[i] <= R:
                right += 1
                curMax = max(A[left:right + 1])
                if curMax >= L and curMax <= R:
                    count+=1
            else:
                while left != right:
                    #result.append(A[left:right+1])
                    left += 1
                    curMax = max(A[left:right + 1])
                    if curMax >= L and curMax <= R:
                        count+=1
                left += 1
                right += 1

        while left != right:
                left += 1
                curMax = max(A[left:right + 1])
                if curMax >= L and curMax <= R:
                    count+=1
        return count
            
print Solution().numSubarrayBoundedMax([2,1,4,3], 2, 3)