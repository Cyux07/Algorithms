import java.util.Arrays;

/**
 * Created by Administrator on 2018/1/27.
 * 755.
 */
public class PourWater {
    public static void main(String[] s) {
        System.out.println(Arrays.toString(new PourWater().pourWater(
                new int[]{2, 1, 1, 2, 1, 2, 2}, 4, 3)));
        //Input: heights = [2,1,1,2,1,2,2], V = 4, K = 3
        //Output: [2,2,2,3,2,2,2]

        System.out.println(Arrays.toString(new PourWater().pourWater(
                new int[]{1,2,3,4}, 2, 2)));
        //Input: heights = [1,2,3,4], V = 2, K = 2
        //Output: [2,3,3,4]

        System.out.println(Arrays.toString(new PourWater().pourWater(
                new int[]{3,1,3}, 5, 1)));
        //Input: heights = [3,1,3], V = 5, K = 1
        //Output: [4,4,4]

        System.out.println(Arrays.toString(new PourWater().pourWater(
                new int[]{1,2,3,4,3,2,1,2,3,4,3,2,1}, 2, 2)));
        //Input: [1,2,3,4,3,2,1,2,3,4,3,2,1], 2 ,2
        //Output: [2,3,3,4,3,2,1,2,3,4,3,2,1]
    }

    /**@param heights every column height
     * @param V the column water come
     * @param K the number of water drop
     * 效率：no-data ？？？*/
    public int[] pourWater(int[] heights, int V, int K) {
        while (V-- > 0)
            pour(heights, K);

        return heights;
    }

    void pour(int[] heights, int K) {
        /*if (K > 0 && heights[K-1] < heights[K]) {
            pour(heights, K - 1);
        }else if (K < heights.length - 1 && heights[K + 1] < heights[K]) {
            pour(heights, K + 1);
        }else {
            heights[K]++;
        }*/

        int sibling = K - 1;
        while (sibling >= 0) {
            if (heights[sibling] < heights[K]) {
                pour(heights, sibling);
                return;
            }else if (heights[sibling] > heights[K]){
                break;
            }else {
                sibling--;
            }
        }

        sibling = K + 1;
        while (sibling <= heights.length - 1) {
            if (heights[sibling] < heights[K]){
                pour(heights, sibling);
                return;
            }else if (heights[sibling] > heights[K]){
                break;
            }else {
                sibling++;
            }
        }

        heights[K]++;
    }
}
