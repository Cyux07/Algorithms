/**
 * Created by Administrator on 2018/1/21.
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * See: image on the website to understand
 */
public class TrappingRainWater {
    public static void main(String[] s) {
        System.out.println(new TrappingRainWater().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));//For example,
        //Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
    }

    /**找到最高的俩，计算他俩中间部分和他俩之外两部分->递归地
     * 先计算小部分低处水量，再计算整体高处水量*/
    public int trap1(int[] height) {
        return trapDeep(height, 0, height.length);
    }

    /**recrusive method, todo debug**/
    int trapDeep(int[] height, int left, int right) {
        if(left == right)
            return 0;

        int first = left, second = right;//index
        int i = left, max = -1, deeper = 0;
        for (; i < second; i++) {
            if (height[i] > max) {
                first = i;
                max = height[i];
            }
        }
        for (i = second-1, max = -1; i >= 0; i--) {
            if (i != first && height[i] > max) {
                second = i;
                max = height[i];
            }
        }

        if (left != 0 && right != height.length)
            deeper = (right - left) * (Math.min(height[left], height[right])-height[first]);

        if (first > second) {//swap
            deeper = first;
            first = second;
            second = deeper;
        }
        return trapDeep(height, left, first) + trapDeep(height, second, right) + deeper;
    }

    /**雨水聚集处即两边扫阴影相交集,rank:50%*/
    public int trap2(int[] height) {
        if (height.length == 0)
            return 0;

        int[] leftmax = new int[height.length], rightmax = new int[height.length];
        int i = 0, max = 0;

        for (max = height[0]; i < height.length; i++) {
            if (height[i] > max)
                max = height[i];
            leftmax[i] = max;
        }

        for (i = height.length - 1, max = height[i]; i >= 0; i--) {
            if (height[i] > max)
                max = height[i];
            rightmax[i] = max;
        }

        for (i = 0, max=0; i < height.length; i++) {
            max += Math.min(leftmax[i], rightmax[i]) - height[i];
        }
        return max;
    }

    /**左右分别更新最大值 70.82% and O(1)space*/
    public int trap(int[] height) {
        if (height.length == 0)
            return 0;

        int left = 0, right = height.length-1;
        int leftMax = -1, rightMax = -1, total = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] < leftMax) {
                    total += leftMax - height[left];
                } else {
                    leftMax = height[left];
                }
                left++;

            }else {
                if (height[right] < rightMax) {
                    total += rightMax - height[right];
                } else {
                    rightMax = height[right];
                }
                right--;
            }
        }

        return total;
    }

}
