import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/1/4.
 */
public class TwoSum {
    public static void main(String[] s) {
        System.out.print(Arrays.toString(new Solution().twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> loca = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            if(loca.containsKey(target - nums[i])) {
                return new int[]{loca.get(target - nums[i]), i};
            }

            loca.put(nums[i], i);
        }

        return new int[]{0, 0};
    }
}
