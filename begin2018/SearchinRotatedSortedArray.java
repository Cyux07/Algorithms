/**
 * Created by Administrator on 2016/06/16.
 * 【题目】

 Suppose a sorted array is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.
 分析：
 三种情况：无旋转；螺旋线交中垂线于低位置；于高位置；
 */
public class SearchinRotatedSortedArray {
    public static void main(String[] s) {
        System.out.println(new SearchinRotatedSortedArray().search(new int[]{8,1,2,3,4,5,6,7}, 6));
        //[1],0
        //[1,3],0
        //{4, 5, 6, 7, 0, 1, 2}, 5
        //{4, 5, 6, 7, 0, 1, 2}, 0
        //[8,1,2,3,4,5,6,7], 6
    }


    public int searchOld(int[] num, int target) {
        int l = 0;
        int r = num.length - 1;

        /*if (num.length == 1) {
            if (num[0] == target)
                return 0;
            else
                return -1;
        }else if (num.length == 0) {
            return -1;
        }*/

        int mid = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (target == num[mid])
                return mid;

            if (num[l] < num[r]) {
                if (target < num[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
                    continue;
            }else if(num[l] < num[mid]) {
                if (target < num[l] || target > num[mid])
                    l = mid + 1;
                else
                    r = mid - 1;
                    continue;
            }else/**num l > num mid*/ {
                if (target < num[mid] || target > num[r])
                    r = mid - 1;
                else
                    l = mid + 1;
                continue;
            }

        }

        return -1;
    }

    //左旋字符串
    /**49.45%*/
    public int search(int[] num, int target) {
        if (num.length == 0)
            return -1;
        return specBi(num, 0, num.length - 1, target);
    }

    private int specBi(int[] num, int begin, int end, int target) {
        if (target == num[begin])
            return begin;
        else if (target == num[end])
            return end;
        else if (begin >= end)
            return -1;

        int mid = (end + begin)/2;
        if (num[mid] == target)
            return mid;
        //旋转点在右 or 旋转点在左
        if (num[mid] > num[begin]) {
            if (target < num[mid] && target > num[begin])
                return normalBi(num, begin, Math.max(mid - 1, 0), target);
            else
                return specBi(num, mid + 1, end, target);
        }else /*if(num[mid] < num[begin])*/ {//I 问无重复数字
            if (target > num[mid] && target < num[begin])
                return normalBi(num, mid + 1, end, target);
            else
                return specBi(num, begin, Math.max(mid - 1, 0), target);
        }
    }

    private int normalBi(int[] num, int begin, int end, int target) {
        if (target == num[begin])
            return begin;
        else if (target == num[end])
            return end;
        else if (begin >= end)
            return -1;

        int mid = (end + begin)/2;
        if (num[mid] == target)
            return mid;
        else if (num[mid] > target)
            return normalBi(num, begin, mid - 1, target);
        else
            return normalBi(num, mid + 1, end, target);
    }
}
