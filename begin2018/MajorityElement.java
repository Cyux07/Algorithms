/**
 * Created by Administrator on 2018/2/21.
 * 169.主要元素问题 -> 最多投票问题
 * 一般解法：排序，取正中数，时间复杂度Onlogn
 * 进阶解法：Boyer-Moore Majority Vote Algorithm 时间复杂度On
 *
 * 使用 cnt 来统计一个元素出现的次数，当遍历到的元素和统计元素不想等时，令 cnt--。
 * 如果前面查找了 i 个元素，且 cnt == 0 ，说明前 i 个元素没有 majority，或者有 majority，
 * 但是出现的次数少于 i / 2 ，因为如果多于 i / 2 的话 cnt 就一定不会为 0 。
 * 此时剩下的 n - i 个元素中，majority 的数目多于 (n - i) / 2，因此继续查找就能找出 majority。
 * （这个解法 东大的考研数据结构里出现过）
 *
 * ‘Boyer-Moore算法’(不同的)用于 字符串匹配，优于KMP
 */
public class MajorityElement {
    public static void main(String[] args) {
        //[100,4,200,1,3,2], Expect:4
        System.out.println(new MajorityElement().majorityElement(new int[]{100,4,200,1,3,2}));
    }

    /**72.05%*/
    public int majorityElement(int[] nums) {
        int count = 0, curr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                curr = nums[i];
                count++;
                continue;
            }

            if(nums[i] != curr)
                count--;
            else
                count++;
        }

        //验证合法，leetcode所给数据中都是合法数据，不需要这一步
        int real = 0;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == curr)
                real++;
        }

        return real > nums.length / 2 ? curr : -1;
        //return curr;
    }
}
