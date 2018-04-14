/**
 * Created by Administrator on 2016/06/13.
 * 一个数组其他数字都出现了两次，只有两个数只出现了一次，找出这两个数
 *
 * 思路：首先的遍历得到的xor融杂进了这两个distinct number，然后通过取xor最右的一个1-bit(实际上就是
 * 取任意一个1-bit都可以，只是为了得到这两个distinct number不一样的地方，
 * 然后用这个不一样去筛选分离出题目要求的两个数，
 * 故num1, num2各异或一个，得出结果)
 *
 * 困惑：去1-bit的statement, can not understand
 */
public class SingleNumber3 {
    public int[] singleNumber(int[] nums) {
        int result[] = new int[2];
        int xor = nums[0];
        for (int i=1; i<nums.length; i++)
        {
            xor ^= nums[i];
        }

        int bit = xor & ~(xor-1);//最右集
        //int bit = xor & -xor;//最右集(同上
        //int bit = xor & ~xor + 1;//最左集
        int num1 = 0;
        int num2 = 0;

        for (int num : nums)
        {
            if ((num & bit) > 0)
            {
                num1 ^= num;
            }
            else
            {
                num2 ^= num;
            }
        }

        result[0] = num1;
        result[1] = num2;
        return result;
    }

    public static void main(String[] args){
        int[] result = new SingleNumber3().singleNumber(new int[]{1,3,1,3,6,8});
        System.out.print(result[0] +" "+ result[1]);
    }
}
