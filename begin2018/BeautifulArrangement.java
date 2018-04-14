/**
 * Created by Administrator on 2018/2/5.
 * 526.
 * Suppose you have N integers from 1 to N.
 * We define a beautiful arrangement as an array that is constructed by these N numbers successfully
 *  if one of the following is true for the ith position (1 <= i <= N) in this array:
 *  1. The number at the ith position is divisible by i.
 *  2. i is divisible by the number at the ith position.
 *  Now given N, how many beautiful arrangements can you construct? (N<=15)
 *
 *  BeautifulArrangementII See Python IDE
 */
public class BeautifulArrangement {
    public static void main(String[] s) {
        System.out.print(new BeautifulArrangement().countArrangement(4));
    }

    /**90ms, 63.15%*/
    int count = 0;
    public int countArrangement1(int N) {
        if (N <= 2)
            return N;
        positionCheck(N, 1, new boolean[N + 1]);
        return count;
    }

    void positionCheck(int N, int pos, boolean[] visited) {
        if (pos > N) {
            count++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i] && (pos % i == 0 || i % pos == 0)) {
                visited[i] = true;
                positionCheck(N, pos + 1, visited);
                visited[i] = false;
            }
        }
    }

    /**5ms, 97.59%*/
    public int countArrangement(int N) {
        int[] nums = new int[N + 1];
        for (int i = 1; i < nums.length; i++)
            nums[i] = i;
        return swapCheck(N, nums);
    }

    /**N is position, reverse order*/
    int swapCheck(int N, int[] nums) {
        if (N <= 1)
            return 1;
        int count = 0;
        for (int i = 1; i <= N; i++) {//i <= N, avoid duplicated
            if (nums[i] % N == 0 ||     N % nums[i] == 0) {
                swap(nums, i, N);
                count += swapCheck(N - 1, nums);
                swap(nums, i, N);
            }
        }
        return count;
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
