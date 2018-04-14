package march2018;

/**牛客网 最大矩形面积 or LeetCode 84. Largest Rectangle in Histogram
 * 找直方图中的最大矩形面积*/

import java.util.Stack;

public class LargestRectangularinaHistogram {
    public static void main(String[] s) {
        int hist[] = { 6, 2, 5, 4, 5, 1, 6 };
        System.out.println("Maximum area is " + getMaxArea(hist, hist.length));
    }

    /**54.74%*/
    private static int getMaxArea(int[] hist, int len) {
        Stack<Integer> stack = new Stack<>(); //存下标
        int i = 0, maxArea = 0;

        while (i < len) {
            if (stack.empty() || hist[stack.peek()] <= hist[i]) {
                stack.push(i++); //i前进
            } else { //否则一直pop直到栈顶小于当前值为止
                int ti = stack.pop(); // get and remove

                int curArea = hist[ti] * (stack.isEmpty() ? i : i - stack.peek() - 1); //前一个比他小的，如果这个小的和自己之间下标差大于一说明中间有被丢掉的更高的，此时就会算到最终的面积里去
                maxArea = Math.max(maxArea, curArea);
            }
        }

        while (!stack.empty()) {
            int ti = stack.pop(); // get and remove

            int curArea = hist[ti] * (stack.isEmpty() ? i : i - stack.peek() - 1); //?
            maxArea = Math.max(maxArea, curArea);
        }

        return maxArea;
    }
}
