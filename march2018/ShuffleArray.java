package march2018;

import java.util.Random;

public class ShuffleArray {

}

/**58.4%*/
class Solution {
    private int[] nums;

    public Solution(int[] nums) {
        this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] dizz = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {//new Random().nextInt(bound); [0, bound)
            int r = (int) (Math.random() * (i + 1));//random 0.0 ~ 1.0 -> r=0 ~ i
            dizz[i] = dizz[r];//每次一定会把前面的数字和后边的换
            dizz[r] = nums[i];
        }

        return dizz;
    }
}
