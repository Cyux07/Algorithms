/**
 * 136. Single Number My Submissions QuestionEditorial Solution
 Total Accepted: 132672 Total Submissions: 263587 Difficulty: Medium
 Given an array of integers, every element appears twice except for one. Find that single one.

 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class singleNumber1 {
    /*public int singleNumber(int[] nums) {
        int result = 0;
        for(int i = 0;i<nums.length;i++){
            result = result ^ nums[i];
        }
        return result;
    }*/

    public static void main(String[] args) {
        int[] nums = {1,2,1,2,8};
        int ans = 0;
        for(int num : nums) {
            ans = ans ^ num;
        }

        System.out.print(ans);
        return ;
    }
}
