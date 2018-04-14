/**
 * Created by Administrator on 2018/1/21.
 * 11. Container With Most Water
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 * Level UP-->See:42.Trapping Rain Water
 */
public class ContainerWithMostWater {
    public static void main(String[] s) {
        //System.out.println(new ContainerWithMostWater().maxArea(new int[]{1, 1}));//1
        System.out.println(new ContainerWithMostWater().maxArea(new int[]{1, 2, 4, 3}));//[1,2,4,3] expect:4
    }

    public int maxArea(int[] height) {
        int i = 0, j = height.length-1;
        int max = -1;

        while(i < j) {
            max = Math.max(max, Math.min(height[i], height[j]) * (j-i));
            if(height[i] < height[j])
                i++;
            else
                j--;
        }

        return max;
    }

/*
    int capacity(int y1, int y2, int x1, int x2) {
        return Math.min(y1, y2) * (x2 - x1);
    }*/
}
