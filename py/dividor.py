# encoding=utf-8

### 负数除数取整，java，Python, C++各不相同
class Solution(object):
    def divide(self, dividend, divisor):
        """
        :type dividend: int
        :type divisor: int
        :rtype: int
        """
        #return dividend / divisor #stupid and not work, 最小负整数除以1,下取整了
        nega = (dividend < 0) == (divisor < 0)
        dividend, divisor = abs(dividend), abs(divisor)
        count = 0

        while(dividend >= divisor):
            temp, i = divisor, 1
            while(dividend >= temp):
                dividend -= temp
                count += i
                i <<= 1 # right move1 = multi 2
                temp <<= 1
        
        if not nega:
            count = -count
        
        #return count ### python int 范围内
        return min(max(-2147483648, count), 2147483647)

s = Solution()
print(s.divide(-2147483648, -1))
#-2147483648
#-1
#>>> type(2147483647)
#<type 'int'>
#>>> type(2147483648)
#<type 'long'>