package april2018;

/**41. 给出一个未排序的数组，找出最小的没有出现的正整数
 * 要求时间复杂度O(n)，常量的额外空间*/
public class FirstMissingPositive {

    //将所有nums[i]交换到i-1位置上去，如将1放到下标0
    // 再用一个循环遍历得到结果
    // 保证nums[nums[i] - 1] != nums[i]以防相同的数字导致死循环
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        for (; i < nums.length; i++) { //O(n)?
            //数字在数组长度范围内
            //下标位置i不是数字i-1
            //下标位置的数和要交换的位置数字不相等
            while (nums[i] > 0 && nums[i] - 1 < nums.length
                    && nums[i] - 1 != i && nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
        }

        i = 0;
        while (i < nums.length) {
            if (i + 1 != nums[i]) break;
            i++;
        }
        return i + 1;
    }

    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    //法二：基于 允许重复数字的k个正数，第一个丢失的正数必定位于[0,k+1]
    //由于存在0和负数，首先用单次快排将所有正数挪到一边，耗费O(n)时间和O(1)空间
    //通过这一步，可以得到所有正数位于A[0~i-1], 我决定用A[i]来表示数字(i+1)是否存在
    // 但又要同时保存它位置上原本的数字来得到答案，幸运的是他们都是正数，故将访问过的数置为负
    //再扫描一次，得到第一个正数A[i],则表示(i+1)不存在，即为所求值
    public int firstMissingPositive2(int[] A) {
        int n = A.length;
        if(n == 0)
            return 1;
        int k=partition(A)+1;
        int temp=0;
        int first_missing_Index=k;
        for(int i=0;i<k;i++){
            temp=Math.abs(A[i]);
            if(temp<=k)
                A[temp-1] = (A[temp-1]<0)?A[temp-1]:-A[temp-1];
        }
        for(int i=0;i<k;i++) {
            if(A[i]>0) {
                first_missing_Index = i;
                break;
            }
        }

        return first_missing_Index+1;
    }

    private int partition(int[] A){
        int n=A.length;
        int q=-1;
        for(int i=0;i<n;i++){
            if(A[i]>0){
                q++;
                swap2(A,q,i);
            }
        }
        return q;
    }

    //高端异或swap
    private void swap2(int[] A, int i, int j){
        if(i!=j){
            A[i]^=A[j];
            A[j]^=A[i];
            A[i]^=A[j];
        }
    }
}
