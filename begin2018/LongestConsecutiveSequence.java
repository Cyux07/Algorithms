import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Administrator on 2018/2/19.
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 For example,
 Given [100, 4, 200, 1, 3, 2],
 The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

 Your algorithm should run in O(n) complexity.(所以不能排序

 Solution: Union-Find 动态连通性，见 算法（第四版）1.5

 优化：加权，记录每个点到根深度
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        //[100,4,200,1,3,2], Expect:4
        System.out.println(new LongestConsecutiveSequence().longestConsecutive(new int[]{100,4,200,1,3,2}));
    }

    /**15.52%*/
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> indexs = new HashMap<>();//Map<num, index>
        UF uf = new UF(nums.length);//只存下标

        for (int i = 0; i < nums.length; i++) {
            if (indexs.containsKey(nums[i]))//避免重复数
                continue;
            indexs.put(nums[i], i);

            if (indexs.containsKey(nums[i] - 1))
                uf.union(i, indexs.get(nums[i] - 1));
            if (indexs.containsKey(nums[i] + 1))
                uf.union(i, indexs.get(nums[i] + 1));
        }

        return uf.maxUnionSize();
    }

    /***/
    final class UF {
        final private int[] id;

        public UF(int length) {
            id = new int[length];
            for(int i=0; i<length; i++)//每个数字初始每个自己为自己的组
                id[i] = i;
        }

        void union(int p, int q) {
            int idp = find(p), idq = find(q);
            id[idp] = idq;//p q 加到同一个集，谁加谁都一样
        }

        /**加权*/
        int[] height;
        int maxSize = 0;
        void unionWeight(int p, int q) {
            int idp = find(p), idq = find(q);
            if (idp == idq)
                return;

            int localMaxHeight = 1;
            if (height[idq] > height[idp]) {
                id[idq] = idp;
                height[idq] += height[idp];
                localMaxHeight = height[idq];
            }else {
                id[idp] = idq;
                height[idp] += height[idq];
                localMaxHeight = height[idp];
            }

            maxSize = Math.max(maxSize, localMaxHeight);
        }

        //找到点p所属的分量，此题即数p所在的连续串
        private int find(int p) {
            while (p != id[p]) {
                id[p] = id[id[p]];//此题限定，连续的数，ID相连着下一个连续的数
                p = id[p];
            }

            return p;
        }

        public int maxUnionSize() {
            int[] count = new int[id.length];
            int max = 0;

            for (int i = 0, root; i < id.length; i++) {
                root = find(i);
                count[root]++;
                max = Math.max(max, count[root]);
            }
            return max;
        }
    }
}
