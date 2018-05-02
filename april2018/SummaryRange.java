package april2018;

import java.util.ArrayList;
import java.util.List;

/**无重复数字，输出连续范围，没什么花样
 * Input: [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Input: [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]*/
public class SummaryRange {
    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();
        if (nums.length < 1)
            return summary;

        int begin = 0;
        for(int i = 1; i < nums.length; i++)
            if (nums[i] - nums[i-1] != 1) {
                if (i-1 != begin)
                    summary.add(nums[begin] + "->" + nums[i-1]);
                else
                    summary.add(nums[begin] + "");
                begin = i;
            }
        if (nums.length-1 != begin)
            summary.add(nums[begin] + "->" + nums[nums.length-1]);
        else
            summary.add(nums[begin] + "");

        return summary;
    }
}
