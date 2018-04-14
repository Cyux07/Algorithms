import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Administrator on 2018/1/24.
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map,
 * compute the volume of water it is able to trap after raining.
 * Note:
 * Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
 * 三维版
 */
public class TrappingRainWaterII {
    public static void main(String[] s) {
        System.out.println(new TrappingRainWaterII().trapRainWater(
                new int[][]{
                        {1, 4, 3, 1, 3, 2},
                        {3, 2, 1, 3, 2,4},
                        {2,3,3,2,3,1}
                }));
        //For example,
        //Given [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]], return 4.
    }

    class Cell {
        int row, col, height;
        boolean visited;
        protected Cell(){}

        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    /**4.6% but no much different with 'the most runtime version'
     * see https://www.youtube.com/watch?v=cJayBq38VYw*/
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0 || heightMap[0].length == 0)
            return 0;

        PriorityQueue<Cell> queue = new PriorityQueue<>(new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return o1.height - o2.height;
            }
        });
        Cell[][] cells = new Cell[heightMap.length][heightMap[0].length];
        int max = -1, total = 0;
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[0].length; j++) {
                cells[i][j] = new Cell(i, j, heightMap[i][j]);
                if (i == 0 || i == heightMap.length-1 || j == 0 || j == heightMap[0].length-1)//add the outside boundary only
                    queue.add(cells[i][j]);
            }
        }
        Cell curr = null;
        while (!queue.isEmpty()) {//raw->in queue->visited
            curr = queue.poll();
            curr.visited = true;
            if (curr.height > max)
                max = curr.height;
            else
                total += max - curr.height;

            if (curr.row > 0 && !cells[curr.row - 1][curr.col].visited && !queue.contains(cells[curr.row - 1][curr.col]))
                queue.add(cells[curr.row - 1][curr.col]);
            if (curr.row < cells.length - 1 && !cells[curr.row + 1][curr.col].visited && !queue.contains(cells[curr.row + 1][curr.col]))
                queue.add(cells[curr.row + 1][curr.col]);
            if (curr.col > 0 && !cells[curr.row][curr.col - 1].visited && !queue.contains(cells[curr.row][curr.col - 1]))
                queue.add(cells[curr.row][curr.col - 1]);
            if (curr.col < cells[0].length - 1 && !cells[curr.row][curr.col + 1].visited && !queue.contains(cells[curr.row][curr.col + 1]))
                queue.add(cells[curr.row][curr.col + 1]);
        }

        return total;
    }
}
