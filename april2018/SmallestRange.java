package april2018;

import java.util.*;

/**给出k个list，查找包含每个k列表中至少一个数字的最窄范围。
 * 如果b-a < d-c (区间较窄)或者 如果b-a == d-c（区间长度相等时) a < c，则我们定义范围[a，b]小于范围[c，d]。*/
//example:
//Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
//Output: [20,24]
//Explanation:
//List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
//List 2: [0, 9, 12, 20], 20 is in range [20,24].
//List 3: [5, 18, 22, 30], 22 is in range [20,24].
public class SmallestRange {

    /**Wrong Answer*/
    public int[] smallestRangeWA(List<List<Integer>> nums) {
        if (nums.size() < 1) //k = 0
            return new int[0];
        if (nums.size() == 1) { // k = 1
            if (nums.get(0).size() < 1)
                return new int[0];
            else
                return new int[] {nums.get(0).get(0), nums.get(0).get(0)};
        }


        int amin = Integer.MAX_VALUE, amax = Integer.MIN_VALUE;
        int minspan = Integer.MAX_VALUE;
        int len = nums.size();
        for (int i : nums.get(0)) { //从第一个数列依次选择一个数
            int min = i, max = i; //所有选择中最大和最小的
            for (int row = 1; row < len; row++) { //依次从其他list中选择最接近第一个数列选出数的
                int near = bisearch(i, nums.get(row));
                if (near >= nums.get(row).size())
                    near = nums.get(row).size() - 1;
                min = Math.min(min, nums.get(row).get(near));
                max = Math.max(max, nums.get(row).get(near));
            }
            //System.out.println("min - max" + min +","+ max);
            if (minspan > max - min || (minspan == max - min && min < amin)) { //题干 比小
                minspan = max - min;
                amax = max;
                amin = min;
            }
        }

        return new int[]{amin, amax};
    }

    //二分查找应该被插入的位置 即i-1小于，i大于 (不重复的数，不会有等于情况
    public int bisearch(int key, List<Integer> num) {
        return bis(key, num, 0, num.size() - 1);
    }

    private int bis(int key, List<Integer> num, int begin, int end) {
        if (begin >= end) {
            if (num.get(begin) > key) {
                if (begin == 0)
                    return begin;
                return num.get(begin) - key < key - num.get(begin - 1) ? begin : begin - 1;
            } else {
                if (begin >= num.size() - 1)
                    return begin;
                return num.get(begin + 1) - key < key - num.get(begin) ? begin + 1 : begin;
            }
        }

        int mid = (begin + end) / 2;
        if (num.get(mid) < key)
            return bis(key, num, mid + 1, end);
        else
            return bis(key, num, begin, mid - 1);
    }


    /**1.93%, 596ms*/
    /** 使用有序队列 共同维护当前来自各个数组的'the choosen one'
     * 队列min和max里同时只存在每个队列里一个元素，而且minmax持有每个list的同一个元素 仅顺序不同
     * 升序和降序，分别[0]位置取到当前数组最大值和最小值，即得到range
     * update: 只用一个PriorityQueue，并保存一个上限和一个下限，因为无论如何，排序每次都会需要做*/
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> pqMin = new PriorityQueue<>((a, b) -> a[0] - b[0]); //升序
        PriorityQueue<int[]> pqMax = new PriorityQueue<>((a, b) -> b[0] - a[0]); //降序
        int[] result = new int[2];
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i).size() == 0) return result; //check: range不存在
            int[] ini = new int[]{nums.get(i).get(0), i, 0}; //value, outer index, inner index
            pqMin.add(ini);
            pqMax.add(ini);
        }

        int diff = Integer.MAX_VALUE;
        while (!pqMax.isEmpty() && !pqMin.isEmpty()) {
            int[] min = pqMin.poll(); //min一定会更新：向前遍历
            int[] max = pqMax.peek(); //max不是需要被更新的那一行
            if (min[0] == max[0]) { //check: 只有一行, range 长度1，最小元素
                result[0] = result[1] = min[0]; //java8 连等
                return result;
            }

            if (max[0] - min[0] < diff) {
                diff = max[0] - min[0];
                result[0] = min[0]; result[1] = max[0];
            }
            if (min[2] + 1 == nums.get(min[1]).size()) //遍历完一个数组
                break;
            pqMax.remove(min); //更新位置 重新排序
            min[0] = nums.get(min[1]).get(++min[2]); //取本数组的下一个
            pqMin.add(min);
            pqMax.add(min);
        }

        return result;
    }

    /**50ms*/
    public int[] smallestRangeP(List<List<Integer>> nums) {
        int[] window = new int[nums.size()]; //从每一个list中选出数的个数
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++)
            for (int j = 0; j < nums.get(i).size(); j++)
                nodes.add(new Node(i, nums.get(i).get(j)));

        Collections.sort(nodes);
        int min = Integer.MAX_VALUE;
        int start = 0;
        for (int i = 0, j = 0, cnt = 0; i < nodes.size(); i++) {
            if (window[nodes.get(i).id]++ == 0) {
                cnt++;
            }
            while (cnt == nums.size() && j <= i) { //所有数组都参与了
                if (nodes.get(i).val - nodes.get(j).val + 1 < min) {
                    min = nodes.get(i).val - nodes.get(j).val + 1;
                    start = nodes.get(j).val;
                }
                if (--window[nodes.get(j).id] == 0) {
                    cnt--;
                }
                j++;
            }
        }
        return new int[]{start, start + min - 1};
    }

    private static class Node implements Comparable<Node>{
        int id;
        int val;

        public Node(int id, int val) {
            this.id = id;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }

    public static void main(String[] s) {
        SmallestRange sr = new SmallestRange();

        List<List<Integer>> nums = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        Collections.addAll(a, 4,10,15,24,26);
        List<Integer> b = new ArrayList<>();
        Collections.addAll(b, 0,9,12,20);
        List<Integer> c = new ArrayList<>();
        Collections.addAll(c, 5,18,22,30);
        nums.add(a);
        nums.add(b);
        nums.add(c);
        System.out.println(Arrays.toString(sr.smallestRange(nums))); //[20,24]

        /*[[11,38,83,84,84,85,88,89,89,92],[28,61,89],[52,77,79,80,81],[21,25,26,26,26,27],[9,83,85,90],[84,85,87],[26,68,70,71],[36,40,41,42,45],[-34,21],[-28,-28,-23,1,13,21,28,37,37,38],[-74,1,2,22,33,35,43,45],[54,96,98,98,99],[43,54,60,65,71,75],[43,46],[50,50,58,67,69],[7,14,15],[78,80,89,89,90],[35,47,63,69,77,92,94]]*/
        //[15,84]
    }
}
