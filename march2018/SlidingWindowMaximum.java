package march2018;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

/** 239. Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 *  You can only see the k numbers in the window. Each time the sliding window moves right by one position.

 For example,
 Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 Therefore, return the max sliding window as [3,3,5,5,6,7].
 */

public class SlidingWindowMaximum {
    public static void main(String[] s) {
        SlidingWindowMaximum swm = new SlidingWindowMaximum();
        System.out.println(Arrays.toString(swm.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3))); //[3, 3, 5, 5, 6, 7]
        System.out.println(Arrays.toString(swm.maxSlidingWindow(new int[]{7,2,4}, 2))); //[7,4]
        //[7,2,4]
        //2
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k <= 1)
            return nums;

        Deque<Integer> deque = new ArrayDeque<>(); //just use as queue
        int[] winmax = new int[nums.length - k + 1];
        int max = nums[0], index = k, win = 0;
        while (win < k) { //init first window
            deque.addFirst(nums[win]);
            max = Math.max(nums[win++], max);
        }
        winmax[0] = max;
        win = 0;
        while (index < nums.length) {
            //max = Math.max(nums[index], max);
            //winmax[index - k + 1] = max;
            while (!deque.isEmpty() && deque.peekFirst() < nums[index]) //remove impossible element in future( 右侧
                deque.removeFirst(); //思路在于：并不一定真的要时刻保持窗口就是k这么大(只可小，不可大
            deque.addFirst(nums[index]); //window +new
            //剪得有够多 or 需要 且只剩一个就减没了 or 剩很多队列的元素肯定是从大到小的 末位那个没新来的大
            if (deque.size() == 1
                    || max == deque.removeLast())
                max = deque.peekLast();//window -old
            winmax[index - k + 1] = max;
            index++;
        }

        return winmax;
    }

    public int[] maxSlidingWindowDiscussion(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n-k+1];// store maxs
        int ri = 0;
        Deque<Integer> q = new ArrayDeque<>();// store index
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k (from head :left is head [1, 3, 7, 4, 2] 5: remove 1
            while (!q.isEmpty() && q.peek() < i - k + 1) { //peek first
                q.poll();
            }
            // remove smaller numbers in k range as they are useless (from tail: right side [1x, 3, 7, 4x, 2x] 5: remove 2, 4
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content(max in each window)
            q.offer(i); //offer last [1x, 3, 7, 4x, 2x, 5]:  offer 5
            if (i >= k - 1) {
                r[ri++] = a[q.peek()];
            }
        }
        return r;
    }
}
