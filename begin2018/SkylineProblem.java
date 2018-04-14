import java.util.*;

/**
 * Created by Administrator on 2018/1/26.
 * 218. See image https://leetcode.com/problems/the-skyline-problem/description/
 *A city's skyline is the outer contour of the silhouette formed by all the buildings
 * in that city when viewed from a distance. Now suppose you are given the locations and height
 * of all the buildings as shown on a cityscape photo (Figure A), write a program
 * to output the skyline formed by these buildings collectively (Figure B).
 *
 * represented by a triplet of integers [Li, Ri, Hi]
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 *
 * 找出城市中所有楼共同形成的顶部曲线
 */
public class SkylineProblem {
    public static void main(String[] s) {
        List<int[]> result = new SkylineProblem().getSkyline(
                new int[][]{
                        {2, 9, 10},
                        {3, 7, 15},
                        {5, 12, 12},
                        {15, 20, 10},
                        {19, 24, 8}
                });
        for (int[] res : result) {
            System.out.println(Arrays.toString(res));
        }
        //For example,
        //Given [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ],
        // return [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]..

        List<int[]> result2 = new SkylineProblem().getSkyline(
                new int[][]{
                        {0,2,3},
                        {2,5,3}
                });
        for (int[] res : result2) {
            System.out.println(Arrays.toString(res));
        }
        //2.
        //Given [[0,2,3],[2,5,3]]
        // return [[0,3],[5,0]]

        //3.
        //Given [[2,9,10],[9,12,15]]
        // return [[2,10],[9,15],[12,0]]
    }

    /**49.06%*/
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        if (buildings.length == 0)
            return result;
        if (buildings.length == 1) {
            result.add(new int[]{buildings[0][0], buildings[0][2]});
            result.add(new int[]{buildings[0][1], 0});
            return result;
        }

        List<int[]> corners = new ArrayList<>();
        for (int[] buil : buildings) {
            corners.add(new int[]{buil[0], buil[2]});
            corners.add(new int[]{buil[1], -buil[2]});//right corner sign as negative value
        }
        Collections.sort(corners, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0])
                    return o1[0]-o2[0];
                else
                    return -o1[0]+o2[0];//same left, compare hight (right 与 另一个的 left 制造一个交错)
            }
        });

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {//0left, 1right, 2height
                return  -o1 + o2;//o1[1] - o2[1];//compare hight
            }
        });
        /*LinkedList<int[]> queue = new LinkedList<>();*///time sequen = left priority sequen
        queue.offer(0);
        int last = 0, top = 0;
        for (int[] curr : corners) {
            if (curr[1] > 0)//left corner
                queue.offer(curr[1]);
            else
                queue.remove(-curr[1]);

            top = queue.peek();
            if (last != top) {
                int size = result.size();
                if (size > 0 && result.get(size - 1)[0] == curr[0] && result.get(size - 1)[1] != curr[1])
                    result.remove(result.size() - 1);
                else
                    result.add(new int[]{curr[0], top});
                last = top;//update second high
            }
        }

        /*int max = -1;//right max
        while (!queue.isEmpty()) {
            //max = Math.max(queue.poll()[1], max);//right
            max = queue.poll()[1];
        }
        result.add(new int[]{max, 0});*/
        /*if (!queue.isEmpty())
            result.add(new int[]{queue.peek()[1], 0});
*/
        return result;
    }
}

/*for (int[] curr : corners) {
        last = 0;
        if (!queue.isEmpty()) {
        int[] tail = queue.peek();
        if (tail[1] < curr[0]) {//两者有间隔
        result.add(new int[]{tail[1], 0});
        queue.poll();
        result.add(new int[]{curr[0], curr[2]});
                    *//*if (max == tail[2])
                        max = 0;*//*
        } else if (tail[1] < curr[1]) {//高度上有相交
        if (tail[2] > curr[2]) {
        result.add(new int[]{tail[1], curr[2]});
        queue.poll();
        }else{
        result.add(new int[]{curr[0], curr[2]});
        }
        }else {//包含于前者，不用再考虑，不加入队列
        if (tail[2] > curr[2])
        continue;
        else
        result.add(new int[]{curr[0], curr[2]});

        }
        }else {
        result.add(new int[]{curr[0], curr[2]});
        }
        queue.add(curr);
        }*/

/**prime Solution*/
/*public */class SolutionP {
    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings.length == 0)
            return new LinkedList<int[]>();
        return recurSkyline(buildings, 0, buildings.length - 1);
    }

    private LinkedList<int[]> recurSkyline(int[][] buildings, int p, int q) {
        if (p < q) {
            int mid = p + (q - p) / 2;
            return merge(recurSkyline(buildings, p, mid),
                    recurSkyline(buildings, mid + 1, q));
        } else {
            LinkedList<int[]> rs = new LinkedList<int[]>();
            rs.add(new int[] { buildings[p][0], buildings[p][2] });
            rs.add(new int[] { buildings[p][1], 0 });
            return rs;
        }
    }


    private LinkedList<int[]> merge(LinkedList<int[]> l1, LinkedList<int[]> l2) {
        LinkedList<int[]> rs = new LinkedList<int[]>();
        int h1 = 0, h2 = 0;
        while (l1.size() > 0 && l2.size() > 0) {
            int x = 0, h = 0;
            if (l1.getFirst()[0] < l2.getFirst()[0]) {
                x = l1.getFirst()[0];
                h1 = l1.getFirst()[1];
                h = Math.max(h1, h2);
                l1.removeFirst();
            } else if (l1.getFirst()[0] > l2.getFirst()[0]) {
                x = l2.getFirst()[0];
                h2 = l2.getFirst()[1];
                h = Math.max(h1, h2);
                l2.removeFirst();
            } else {
                x = l1.getFirst()[0];
                h1 = l1.getFirst()[1];
                h2 = l2.getFirst()[1];
                h = Math.max(h1, h2);
                l1.removeFirst();
                l2.removeFirst();
            }
            if (rs.size() == 0 || h != rs.getLast()[1]) {
                rs.add(new int[] { x, h });
            }
        }
        rs.addAll(l1);
        rs.addAll(l2);
        return rs;
    }
}
