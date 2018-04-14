import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/2/21.
 * Given an integer array of size n, find ALL elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
 * 229. Majority Element II
 *
 * ->
 * 在数组中找到出现次数大于N/K的数:程序员代码面试指南 第八章 五小节
 * k-1个候选人，最后验证
 */
public class MajorityElementII {
    public static void main(String[] args) {
        //[100,4,200,1,3,2], Expect:4
        System.out.println(new MajorityElement().majorityElement(new int[]{100,4,200,1,3,2}));
    }

    /**4.35%, 因为通用于找n/k的情况*/
    public List<Integer> majorityElement(int[] nums) {
        HashMap<Integer, Integer> candidates = new HashMap<>(); //<num,count>
        final int K = 3;

        for (int num : nums) {
            if (candidates.containsKey(num)) {
                candidates.put(num, candidates.get(num) + 1);
            }else {
                if (candidates.size() < K-1) {
                    candidates.put(num, 1);
                }else {
                    allOneMinus(candidates);
                }
            }
        }

        for (int num : candidates.keySet())
            candidates.put(num, 0);
        for (int num : nums)
            if (candidates.containsKey(num))
                candidates.put(num, candidates.get(num) + 1);
        List<Integer> results = new ArrayList<>();
        for (int num : candidates.keySet())
            if (candidates.get(num) > nums.length / K)
                results.add(num);
        /*//java 8 风格
        candidates.keySet().stream()
                .filter((num) -> candidates.get(num) > nums.length / K)
                .forEach(results::add);
        //ide生成java8
        List<Integer> results2 = candidates.keySet().stream()
                .filter((num) -> candidates.get(num) > nums.length / K)
                .collect(Collectors.toList());*/

        return results;
    }

    private void allOneMinus(HashMap<Integer, Integer> candidates) {
        List<Integer> drops = new ArrayList<>();

        for (int num : candidates.keySet()) {
            if (candidates.get(num) == 1)
                drops.add(num);
            candidates.put(num, candidates.get(num) - 1);
        }
        for (int drop : drops)
            candidates.remove(drop);
    }


    /**Prime Solution*/
    public List<Integer> majorityElement1(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return new LinkedList<Integer>();
        }
        List<Integer> res = new LinkedList<Integer>();
        int num1 = nums[0];
        int num2 = nums[0];
        int count1 = 0;
        int count2 = 0;
        int l = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num1) {
                count1++;
            } else if (nums[i] == num2) {
                count2++;
            } else if (count1 == 0) {
                num1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                num2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num1) {
                count1++;
            }else if (nums[i] == num2) {
                count2++;
            }
        }

        if (count1 > l / 3) {
            res.add(num1);
        }
        if (count2 > l / 3) {
            res.add(num2);
        }

        return res;
    }
}

//java
//candidates.keySet().stream().filter(num -> candidates.get(num) == 1).forEach(num -> {});