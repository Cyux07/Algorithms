# encoding=utf-8
#电话号码按键，给出数字，求对应键上所有字母组合
# 法一reduce, 法二类似一个队列，将半成品出队，迭代本轮所有可加元素再放回队列，直到按键轮询完
#author @huxley
class Solution:
    # @return a list of strings, [s1, s2]
    def letterCombinations(self, digits):
        if '' == digits: return []
        kvmaps = {
            '2': 'abc',
            '3': 'def',
            '4': 'ghi',
            '5': 'jkl',
            '6': 'mno',
            '7': 'pqrs',
            '8': 'tuv',
            '9': 'wxyz'
        }
        return reduce(lambda acc, digit: [x + y for x in acc for y in kvmaps[digit]], digits, ['']) #param, acc=initial

    #version without reduce author @Baoyx007
    def letterCombinations2(self, digits):
        if '' == digits: return []
        kvmaps = {
            '2': 'abc',
            '3': 'def',
            '4': 'ghi',
            '5': 'jkl',
            '6': 'mno',
            '7': 'pqrs',
            '8': 'tuv',
            '9': 'wxyz'
        }
        ret=['']
        for c in digits:
            tmp=[]
            for y in ret:
                for x in kvmaps[c]:
                    tmp.append(y+x)
            ret=tmp
        
        return ret

print Solution().letterCombinations('23')

# reduce(lambda a, b: ..., list) -> (list[0], list[1]) + list[2]) +list[3])...
# reduce(lambda a, b: ..., list, init) -> (init, list[0]) + list[1]) +list[2])...

for _ in '': #zero time
    print 'loop in a empty str'

for _ in ['']: #one element, one time
    print 'loop in a str list'