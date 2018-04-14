/**
 * Created by Administrator on 2018/2/15.
 * 149.Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * 找出分布在同一条线上的点的数目最大值
 */

/*
     *  A line is determined by two factors,say y=ax+b
     *
     *  If two points(x1,y1) (x2,y2) are on the same line(Of course).

     *  Consider the gap between two points.

     *  We have (y2-y1)=a(x2-x1),a=(y2-y1)/(x2-x1) a is a rational, b is canceled since b is a constant

     *  If a third point (x3,y3) are on the same line. So we must have y3=ax3+b

     *  Thus,(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

     *  Since a is a rational, there exists y0 and x0, y0/x0=(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

     *  So we can use y0&x0 to track a line;
     */

import java.util.HashMap;
import java.util.List;

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class MaxPointsOnaLine {
    static class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }

    public static void main(String[] args) {
        MaxPointsOnaLine line = new MaxPointsOnaLine();
        /*System.out.println(line.generateGCD(16, 4));
        System.out.println(line.generateGCD(4, 16));
        System.out.println(line.generateGCD(17, 3));
        System.out.println(line.generateGCD(21, 14));*/
        //[[1,1],[1,1],[1,1]]
        System.out.println(line.maxPoints(new Point[]{new Point(1, 1), new Point(1, 1), new Point(1, 1)}));
    }

    /**75.52%*/
    /**kcount 不同斜率k的数对数量, 要点：斜率用分数存 故还要约分成最简（同除最小公约数）*/
    public int maxPoints(Point[] points) {
        if (points == null)
            return 0;
        if (points.length <= 2)
            return points.length;
        //SparseArray only in Android
        //or Map<x, Map<y, count>>: x/y就是斜率
        HashMap<String, Integer> kcount = new HashMap<>();//count 从一点出发，同一斜率k，即同一直线上的数对数量
        int result = 0;

        for (int i = 0; i < points.length - 1; i++) {
            kcount.clear();//首先必须同一条线，其次再是斜率同
            int overlap = 0, max = 0, gcd;
            for (int j = i + 1; j < points.length; j++) {
                int deltax = points[i].x - points[j].x,
                        deltay = points[i].y - points[j].y;
                if (deltax == 0 && deltay == 0) {
                    overlap++;
                    continue;
                }
                //化简
                gcd = generateGCD(deltax, deltay);
                deltax /= gcd;
                deltay /= gcd;

                //count
                String k = deltax + "/" + deltay;
                int count = kcount.getOrDefault(k, 0);//method upgrade
                kcount.put(k, ++count);
                max = Math.max(max, count);
                /*if (kcount.containsKey(k)) {
                    max = Math.max(max, kcount.get(k) + 1);
                    kcount.put(k, max);
                }else{
                    kcount.put(k, 1);
                }*/
            }
            result = Math.max(result, max + overlap + 1);// +1, i self
        }

        return result;
    }

    //最大公约数
    int generateGCD(int x, int y) {
        int temp = y;
        while (y != 0) {
            y = x%y;
            x = temp;
            temp = y;
        }

        return x;
    }
}
