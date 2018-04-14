# coding=utf-8
#question 593.
#Given the coordinates of four points in 2D space, return whether the four points could construct a square.
#The coordinate (x,y) of a point is represented by an integer array with two integers.

#All the input integers are in the range [-10000, 10000].
#A valid square has four equal sides with positive length and four equal angles (90-degree angles).
#Input points have no order.

class Solution(object):
    def validSquare2(self, p1, p2, p3, p4):
        """
        :type p1: List[int]
        :type p2: List[int]
        :type p3: List[int]
        :type p4: List[int]
        :rtype: bool
        """
        ps = sorted((p1, p2, p3, p4), key=lambda pa : pa[0])
        return abs(ps[0][0] - ps[1][0]) == abs(ps[2][0] - ps[3][0]) and abs(ps[0][1]-ps[1][1]) == abs(ps[2][1]-ps[3][1]) and abs(ps[0][0] - ps[1][0])== abs(ps[1][1]-ps[2][1]) and abs(ps[0][1]-ps[1][1]) == abs(ps[1][0] - ps[2][0])

    #17.8%
    def validSquare(self, p1, p2, p3, p4):
        """
        :type p1: List[int]
        :type p2: List[int]
        :type p3: List[int]
        :type p4: List[int]
        :rtype: bool
        """
        pset = {(a[0]-b[0])*(a[0]-b[0]) + (a[1]-b[1])*(a[1]-b[1]) for (a, b) in ((p1, p2),(p1, p3), (p1, p4), (p2, p3), (p2, p4), (p3, p4))}
        return 0 not in pset and len(pset) == 2 #边长；对角线长

print Solution().validSquare(p1=[1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1])
#p1 = [0,0], p2 = [5,0], p3 = [5,4], p4 = [0,4]
#[0,0]
#[5,0]
#[5,4]
#[0,4]
#p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
#[1,0]
#[-1,0]
#[0,1]
#[0,-1]