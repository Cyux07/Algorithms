package april2018;

//import static jdk.nashorn.internal.objects.NativeArray.reverse; //JS

import java.util.Arrays;

//找到一个数的下一个排列，（比他大的）。
// 如果这个数已经排列到最大，就给出最小排列
//1,2,3 → 1,3,2
//3,2,1 → 1,2,3
//1,3,2 ->2,1,3
//1,1,5 → 1,5,1
//1 2 3 4 8 7 6 -> ... 8 7 6 4
//1 2 3 4 8 6 7 -> ... 7 6
//1 2 3 4 8 6 7 5 ->  ... 7 6 ...
/**Runtime Error：可能是数据合法性没检查*/
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1)
            return;
        if (nums.length == 2){
            swap(nums, 0, 1);
            return;
        }

        int pe = 0, re = 0; //逆序数
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] >= nums[i])
                re++;
            if (nums[i - 1] <= nums[i])
                pe++;
        }
        if (pe == nums.length - 1) {//完全正序，交换末尾2
            swap(nums, nums.length - 2, nums.length - 1);
            return;
        }
        if (re == nums.length - 1) { //完全逆序，输出正序
            reverse(nums);
            return;
        }

        int firstr = 0;
        while (nums[firstr] <= nums[firstr + 1]) firstr++;

        if (firstr == nums.length - 2) {//锋头刚好在倒数第二个位置
            int i = firstr - 1;
            while (i >= 0) {
                if (nums[i] < nums[nums.length - 1]) break;
                i--;
            }
            if (i < 0) {// 2 3 1
                swap(nums, firstr - 1, firstr);
                swap(nums, firstr, firstr + 1);
            }else {
                backOneStep(nums, i);
            }
            return;
        }

        firstr++; //锋头后第一个数

    //1先检查是否全是反序
        int pos = firstr, litb = 0;
        while (pos < nums.length - 1) {
            if (nums[pos] < nums[pos + 1])
                litb = pos;
            pos++;
        }
        if (litb > firstr) { //不是全反序
            swap(nums, litb, litb + 1);
            if (litb < nums.length - 2)
                Arrays.sort(nums, litb + 1, nums.length);
            return;
        }

        pos = firstr + 1; litb = firstr; //pos & pos+1 是锋头后最后顺次 -> pos 是现在的下标从锋头后开始，litb是最小的比锋头后第一个元素大的值的下标
        while (pos < nums.length) {
            if (nums[firstr] < nums[pos])
                if (firstr == litb || nums[litb] > nums[pos])litb = pos;
            pos++;
        }


        if (litb == firstr) { //锋头之后的第一个数比后边的数都小
            /*while (firstr < nums.length && nums[firstr - 1] < nums[firstr]) //1 3 5 7 9 8 6 4 2 -> 1 3 5 9 7 8 6 4 2 -> 1 3 5 9 8 7 6 4 2
                swap(nums, firstr - 1, firstr);*/
            if (firstr == 1) { //2 1 0 0 1
                pos = 3; litb = -1;
                while (pos < nums.length) {
                    if (nums[pos - 1] < nums[pos]) litb = pos - 1;
                    pos++;
                }
                if (litb != -1) {
                    swap(nums, litb, litb + 1);
                    Arrays.sort(nums, litb + 1, nums.length);
                }
                return;
            }

    //2先检查是否全是反序
            pos = firstr; litb = 0;
            while (pos < nums.length - 1) {
                if (nums[pos] < nums[pos + 1])
                    litb = pos;
                pos++;
            }
            if (litb != 0) { //不是全反序
                swap(nums, litb, litb + 1);
                Arrays.sort(nums, litb + 1, nums.length);
                return;
            }

            firstr--;/*回到锋点*/pos = firstr + 1; litb = firstr;
            while (pos < nums.length) {
                if (nums[firstr - 1] < nums[pos])
                    if (nums[litb] > nums[pos])litb = pos;
                pos++;
            }
            swap(nums, firstr - 1, litb);
            Arrays.sort(nums, firstr, nums.length);
        } else {
            swap(nums, firstr, litb);
            if (firstr + 1 < nums.length)
                Arrays.sort(nums, firstr + 1, nums.length);
        }

    }

    //从i开始后边的向前移动一位，最终将i放在末尾
    private void moveOneStep(int[] nums, int i) {
        int temp = nums[i];
        while (i < nums.length - 1) {
            nums[i] = nums[i + 1];
            i++;
        }
        nums[i] = temp;
    }
    //从最后直到i为止全部往后移动一步，最后一个元素会放到i位置
    private void backOneStep(int[] nums, int i) {
        int temp = nums[nums.length - 1];
        int index = nums.length - 1;
        while (index > i) {
            nums[index] = nums[index - 1];
            index--;
        }
        nums[i] = temp;
    }

    private void reverse(int[] nums) {
        int mid = nums.length / 2;
        for (int i = 0; i < mid; i++) {
            swap(nums, i, nums.length - i - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    //https://en.wikipedia.org/wiki/Permutation 按字典顺序生成下一个全排列的算法
    /**在14世纪，一个名叫Narayana Pandita的人给出了下面的经典而又非常简单的算法（对符号进行小的修改以符合问题陈述）：
     * 1. Find the largest index k such that nums[k] < nums[k + 1].
     *      If no such index exists, the permutation is sorted in descending order,
     *      just reverse it to ascending order and we are done.
     *      For example, the next permutation of [3, 2, 1] is [1, 2, 3].
        2. Find the largest index l greater than k such that nums[k] < nums[l].
        3. Swap the value of nums[k] with that of nums[l].
        4. Reverse the sequence from nums[k + 1] up to and including the final element nums[nums.size() - 1].*/
    /**在当前序列中，从尾端往前寻找两个相邻元素，前一个记为first，后一个记为second，并且满足first 小于 second。然后再从尾端寻找另一个元素number，
     * 如果满足first 小于number，即将第first个元素与number元素对调，并将second元素之后（包括second）的所有元素颠倒排序，即求出下一个序列

     example:
     6，3，4，9，8，7，1
     此时 first ＝ 4，second = 9
     从尾巴到前找到第一个大于first的数字，就是7
     交换4和7，即上面的swap函数，此时序列变成6，3，7，9，8，4，1
     再将second＝9以及以后的序列重新排序，让其从小到大排序，使得整体最小，即reverse一下（因为此时肯定是递减序列）
     得到最终的结果：6，3，7，1，4，8，9*/
    public void nextPermutationComment(int[] nums) {
        if(nums.length<=1){
            return;
        }

        int i= nums.length-1;

        for(;i>=1;i--){

            if(nums[i]>nums[i-1]){ //find first number which is smaller than it's after number
                break;
            }
        }

        if(i!=0){
            swap(nums,i-1); //if the number exist,which means that the nums not like{5,4,3,2,1}
        }

        reverse(nums,i);
    }

    private void swap(int[] a,int i){
        for(int j = a.length-1;j>i;j--){
            if(a[j]>a[i]){
                int t = a[j];
                a[j] = a[i];
                a[i] = t;
                break;
            }
        }
    }

    private void reverse(int[] a,int i){//reverse the number after the number we have found
        int first = i;
        int last = a.length-1;
        while(first<last){
            int t = a[first];
            a[first] = a[last];
            a[last] = t;
            first ++;
            last --;
        }
    }

    public static void main(String[] s) {
        NextPermutation np = new NextPermutation();
        int[] tp1 = new int[]{1, 2, 3}; //1 3 2
        np.nextPermutation(tp1);
        System.out.println(Arrays.toString(tp1));
        tp1 = new int[]{3, 2, 1};// 1 2 3
        np.nextPermutation(tp1);
        System.out.println(Arrays.toString(tp1));
        tp1 = new int[]{1, 3, 2};//2 1 3
        np.nextPermutation(tp1);
        System.out.println(Arrays.toString(tp1));
        tp1 = new int[]{2, 1, 3};//2 3 1
        np.nextPermutation(tp1);
        System.out.println(Arrays.toString(tp1));
        tp1 = new int[]{2, 3, 1};//3 1 2
        np.nextPermutation(tp1);
        System.out.println(Arrays.toString(tp1));
        tp1 = new int[]{3, 1, 2};//3 2 1
        np.nextPermutation(tp1);
        System.out.println(Arrays.toString(tp1));

        int[] tp3 = new int[]{1, 1, 5}; //1 5 1
        np.nextPermutation(tp3);
        System.out.println(Arrays.toString(tp3));
        tp3 = new int[]{1, 5, 1}; // 5 1 1
        np.nextPermutation(tp3);
        System.out.println(Arrays.toString(tp3));
        tp3 = new int[]{5, 1, 1}; // 1 1 5
        np.nextPermutation(tp3);
        System.out.println(Arrays.toString(tp3));

        tp3 = new int[]{1, 2, 3, 4, 8, 6, 7, 5};
        np.nextPermutation(tp3);
        System.out.println(Arrays.toString(tp3));
        tp3 = new int[]{5,4,7,5,3,2}; //[5,5,2,3,4,7]
        np.nextPermutation(tp3);
        System.out.println(Arrays.toString(tp3));
        tp3 = new int[]{1,2,0,3,0,1,2,4}; //[1,2,0,3,0,1,4,2]
        np.nextPermutation(tp3);
        System.out.println(Arrays.toString(tp3));
        tp3 = new int[]{2,2,7,5,4,3,2,2,1}; //[2,3,1,2,2,2,4,5,7]
        np.nextPermutation(tp3);
        System.out.println(Arrays.toString(tp3));
        tp3 = new int[]{2,1,0,0,1}; //[2,1,0,1,0]
        np.nextPermutation(tp3);
        System.out.println(Arrays.toString(tp3));
        //TODO fail
        tp3 = new int[]{2,4,2,2,0,4,3}; //[2,4,2,2,3,0,4]
        np.nextPermutation(tp3);
        System.out.println(Arrays.toString(tp3));
    }
}
