package may2018;

/**Contest82 4-hard
 * 827. 建造岛
 * 一个2D的格子，只有0和1，最多可以将一个0变成1
 * 问经过这个改变可以形成的最大岛屿，连在一起的1即是岛屿, 斜向不算*/
//Example 1:
//Input: [[1, 0], [0, 1]]
//Output: 3
//Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

//1 <= grid.length = grid[0].length <= 50.
//0 <= grid[i][j] <= 1.

import java.util.HashSet;

/**并查集？ See: LongestConsecutiveSequence*/
public class MakingALargeIsland {
    public int largestIsland(int[][] grid) {
        if (grid.length < 1)
            return 0;
        if (grid[0].length < 1)
            return 0;
        HashSet<Integer> indexs = new HashSet<>();//Map<num, index>
        int col = grid.length, row = grid[0].length;
        UF uf = new UF(row * col);//只存下标, or UF(50x50)

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 1) //存岛屿1，不存空地0
                    continue;
                indexs.add(i*row + j); //行列存，or Pair<row, col>?

                //四方向存在相连为同一个岛，建岛
                if (j < row-1 && indexs.contains(i*row + j + 1)) //两个坐标揉到一起就需要考虑越界
                    uf.union(i*row + j, i*row + j + 1);
                if (j > 0 && indexs.contains(i*row + j - 1)) //contain表示此处是岛屿陆地
                    uf.union(i*row + j, i*row + j - 1);
                if (i < col-1 && indexs.contains((i+1)*row + j)) //不contain表示此处是空的
                    uf.union(i*row + j, (i+1)*row + j);
                if (i > 0 && indexs.contains((i-1)*row + j)) //相邻陆地连在一起表示是同一块岛屿
                    uf.union(i*row + j, (i-1)*row + j);
            }
        }
        uf.ensureUnionSize();

        //找所有空位，查看填充是否可以与现有的岛屿相连接，保留最大
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int temp = 0;
                if (grid[i][j] == 0) {
                    boolean left = false, right = false, up = false, down = false;
                    temp = 1; //+ 1 self

                    if (j < row-1 && indexs.contains(i*row + j + 1)) { //right
                        temp += uf.count[uf.find(i * row + j + 1)];
                        right = true;
                    }
                    if (j > 0 && indexs.contains(i*row + j - 1)) {  //left
                        if (!right || (uf.find(i * row + j - 1) != uf.find(i * row + j + 1))) {
                            temp += uf.count[uf.find(i * row + j - 1)];
                            left = true;
                        }
                    }
                    if (i < col-1 && indexs.contains((i+1)*row + j)) { //down
                        if ((!left || (uf.find((i+1)*row + j) != uf.find(i*row + j - 1)))
                                && (!right || (uf.find((i+1)*row + j) != uf.find(i*row + j + 1)))) {
                                temp += uf.count[uf.find((i + 1) * row + j)];
                                down = true;
                            }
                    }
                    if (i > 0 && indexs.contains((i-1)*row + j)) { //up
                        if ((!right || (uf.find((i-1)*row + j) != uf.find(i*row + j + 1)))
                            && (!left || (uf.find((i-1)*row + j) != uf.find(i*row + j - 1)))
                            && (!down || (uf.find((i-1)*row + j) != uf.find((i+1)*row + j))))
                            temp += uf.count[uf.find((i-1)*row + j)];
                    }
                }
                max = Math.max(max, temp);
            }
        }
        //地图格上没有0
        if (max == 0) {
            for (int c : uf.count)
                max = Math.max(max, c);
        }

        return max;
    }

    /***/
    final class UF {
        final private int[] id;
        final int[] count;

        UF(int length) {
            id = new int[length];
            count = new int[id.length];
            for(int i=0; i<length; i++)//每个数字初始每个自己为自己的组
                id[i] = i;
        }

        void union(int p, int q) {
            int idp = find(p), idq = find(q);
            id[idp] = idq;//p q 加到同一个集，谁加谁都一样
        }

        /**加权*/
        int[] height;
        int maxSize = 0;
        void unionWeight(int p, int q) {
            int idp = find(p), idq = find(q);
            if (idp == idq)
                return;

            int localMaxHeight = 1;
            if (height[idq] > height[idp]) {
                id[idq] = idp;
                height[idq] += height[idp];
                localMaxHeight = height[idq];
            }else {
                id[idp] = idq;
                height[idp] += height[idq];
                localMaxHeight = height[idp];
            }

            maxSize = Math.max(maxSize, localMaxHeight);
        }

        //找到点p所属的分量，即p所在的岛屿
        private int find(int p) {
            if (p >= id.length) //invalid input
                return -1;

            while (p != id[p]) {
                id[p] = id[id[p]];//此题限定，连续的数，ID相连着下一个连续的数
                p = id[p];
            }

            return p;
        }

        //得到初始岛屿各自的大小，以此为基础考虑转换哪一个点来连接
        public void ensureUnionSize() {
            for (int i = 0, root; i < id.length; i++) {
                root = find(i);
                count[root]++;
            }
        }
    }


    public static void main(String[] s) {
        MakingALargeIsland island = new MakingALargeIsland();
        int large = island.largestIsland( //4
                new int[][]{
                {1,1},
                {1,1}});
        System.out.println(large);
        //[[0,0],[1,0]] 2
        large = island.largestIsland(
                new int[][]{
                        {0,0},
                        {1,0}});
        System.out.println(large);
        //[[1, 0], [0, 1]] 3
        large = island.largestIsland(
                new int[][]{
                        {1,0},
                        {0,1}});
        System.out.println(large);
        //[[1,0,1,0,1],[0,1,1,0,1],[1,1,1,0,0],[1,0,1,1,1],[0,0,1,1,0]] 15
        large = island.largestIsland(
                new int[][]{
                        {1,0,1,0,1},
                        {0,1,1,0,1},
                        {1,1,1,0,0},
                        {1,0,1,1,1},
                        {0,0,1,1,0}});
        System.out.println(large);
    }
}
