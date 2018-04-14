import java.util.Arrays;

/**
 * Created by Administrator on 2018/2/12.
 * There are N children standing in a line. Each child is assigned a rating value.

 You are giving candies to these children subjected to the following requirements:

 Each child must have at least one candy.
 Children with a higher rating get more candies than their neighbors.
 What is the minimum candies you must give?
 */
public class Candy {
    public static void main(String[] s) {
        //System.out.println(new JumpGame().canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(new Candy().candy(new int[]{5, 1, 1, 1, 10, 2, 1, 1, 1, 3}));// EXpect 15
        System.out.println(new Candy().candy(new int[]{9, 7, 5, 8, 6, 4}));//12
        System.out.println(new Candy().candy(new int[]{1, 2, 2}));// Expect 4, 最后一个2只需要一颗糖果，也就是多个相等中间部分只需要最小量
    }

    /**9.45% -> 7.56%*/
    final int LEFT = -1;
    final int RIGHT = 1;
    int[] ratings;
    int[] candys;
    public int candy(int[] ratings) {
        if (ratings.length <= 1)
            return ratings.length;

        this.ratings = ratings;
        this.candys = new int[ratings.length]; //Arrays.fill(candys, 1);//数组全填充1
        if (ratings[0] < ratings[1])
            upstair(0, RIGHT);
        for (int i = 1; i < ratings.length-1; i++) {
            if (ratings[i] <= ratings[i + LEFT]
                    && ratings[i] <= ratings[i + RIGHT]) {
                /*if (ratings[i] != ratings[i + LEFT]
                        &&ratings[i] != ratings[i + RIGHT])
                    continue;
                upstair(i, LEFT);upstair(i, RIGHT);*/
                //从左右都比他大，或者至少一边比他大，另一边和他相同的点，进行左右搜寻
                if (ratings[i] != ratings[i + LEFT])
                    upstair(i, LEFT);
                if (ratings[i] != ratings[i + RIGHT])
                    upstair(i, RIGHT);
            }
        }
        if (ratings[ratings.length-2] > ratings[ratings.length-1])
            upstair(ratings.length-1, LEFT);

        int count = 0;
        for (int candy : candys)
            count += Math.max(candy, 1); // 避免有些节点未被遍历到
        return count;
    }

    private void upstair(int index, int direction) {
        int num = 1;
        if (candys[index] == 0)
            candys[index] = 1;
        while (((direction == RIGHT && index >= 0 && index < ratings.length-1)
                || (direction == LEFT && index > 0 && index < ratings.length)) ) {
            if (ratings[index] <= ratings[index + direction]) {
                if (ratings[index] != ratings[index + direction])
                    num++;
                else
                    num = 1;// 连续相同的数 夹在中间的只需要1颗糖（审题问题？）
                if (candys[index + direction] < num)
                    candys[index + direction] = num;
                index += direction;
            } else {
                break;
            }
        }
    }


    /**fastest Solution:*/
    public int candy2(int[] ratings) {
        int[] dis = new int[ratings.length];
        int n = ratings.length;
        dis[0] = 1;
        for(int i=1; i<n; i++) {
            if(ratings[i] > ratings[i-1])  dis[i] = dis[i-1] + 1;
            else dis[i] = 1;
        }
        int sum = dis[n-1];
        for(int i=n-2; i>=0; i--) {
            if(ratings[i] > ratings[i+1] && dis[i] < dis[i+1] + 1)
                dis[i] = dis[i+1] + 1;
            sum += dis[i];
        }
        return sum;
    }

    class SolutionMost { /**直接从左向右遍历一次，再从右向左遍历一次*/
        public int candy(int[] ratings) {
            //boundary condition
            if(ratings.length==0 || ratings==null)
                return 0;
            int candies[] = new int[ratings.length];
            candies[0]=1;

            //start filling the candies array from left to right
            for(int i=1;i<candies.length;i++)
            {
                if(ratings[i] > ratings[i-1])
                    candies[i]= candies[i-1]+1;
                else
                    candies[i]=1;
            }

            int result = candies[candies.length-1];
            //start fill the array now form right and left and also keep a result integer
            for(int i=candies.length-2;i>=0;i--)
            {
                int curr=1;
                if(ratings[i]>ratings[i+1])
                {
                    curr = candies[i+1]+1;

                }
                result += Math.max(curr, candies[i]);
                candies[i]= curr;
            }

            return result;

        }
    }
}
