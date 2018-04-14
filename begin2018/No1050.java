import java.util.Scanner;

/**
 * Created by Administrator on 2016/06/11.
 * 1050: [USACO 1.1.1]你要乘坐的飞碟在这里
 */
public class No1050 {




    public static void main(String[] args) {
        String team = null, star = null;
        long teamSum = 0, starSum = 0;
        Scanner scanner = new Scanner(System.in);

        team = scanner.next();
        teamSum = computeSum(team);
        team = null;

        star = scanner.next();
        starSum = computeSum(star);
        star = null;

        if (teamSum % 47 == starSum % 47) {
            System.out.print("GO");
        }else {
            System.out.print("STAY");
        }


    }

    private static long computeSum(String string) {
        int size = string.length();
        long sum = 1;
        for (int i = 0; i < size; i ++) {
            int c = string.charAt(i) - 'A' + 1;
            /*if (c >= 'A' && c <= 'Z')
                c = c - 'A' + 1;
            else if (c >= 'a' && c <= 'z')
                c = c - 'a' + 1;
            else
                ;*///no handle
            sum *= c;
        }

        return sum;
    }
}
