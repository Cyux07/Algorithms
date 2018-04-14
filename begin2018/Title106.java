import java.util.*;

/**
 * Created by Administrator on 2016/06/25.
 */
public class Title106 {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //final int S = 11;
        int[][] values;
        int m = 0;

        int loop = input.nextInt();
        while (loop-- > 0) {
            int s = input.nextInt();
            m = input.nextInt();

            {
                int indexs = s;
                values = new int[s][2];
                while (indexs-- > 0){
                    values[indexs][0] = input.nextInt();
                    values[indexs][1] = input.nextInt();
                }
            }
            Arrays.sort(values, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return -o1[0] + o2[0];
                }
            });

            int sum = 0;
            for (int i = 0, mi = 0; i < s; i++) {
                if (values[i][1] < m-mi) {
                    mi += values[i][1];
                    sum += values[i][0] * values[i][1];
                }else {
                    sum += values[i][0] * (m - mi);
                    break;
                }
            }
            System.out.println(sum);
        }
    }

}
