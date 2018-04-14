/**
 * Created by Administrator on 2016/06/13.
 * һ�������������ֶ����������Σ�ֻ��������ֻ������һ�Σ��ҳ���������
 *
 * ˼·�����ȵı����õ���xor���ӽ���������distinct number��Ȼ��ͨ��ȡxor���ҵ�һ��1-bit(ʵ���Ͼ���
 * ȡ����һ��1-bit�����ԣ�ֻ��Ϊ�˵õ�������distinct number��һ���ĵط���
 * Ȼ���������һ��ȥɸѡ�������ĿҪ�����������
 * ��num1, num2�����һ�����ó����)
 *
 * ����ȥ1-bit��statement, can not understand
 */
public class SingleNumber3 {
    public int[] singleNumber(int[] nums) {
        int result[] = new int[2];
        int xor = nums[0];
        for (int i=1; i<nums.length; i++)
        {
            xor ^= nums[i];
        }

        int bit = xor & ~(xor-1);//���Ҽ�
        //int bit = xor & -xor;//���Ҽ�(ͬ��
        //int bit = xor & ~xor + 1;//����
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
