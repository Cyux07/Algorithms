import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/06/24.
 * 一笔画问题
 时间限制：3000 ms  |  内存限制：65535 KB
 难度：4
 描述
 zyc从小就比较喜欢玩一些小游戏，其中就包括画一笔画，他想请你帮他写一个程序，判断一个图是否能够用一笔画下来。

 规定，所有的边都只能画一次，不能重复画。



 输入
 第一行只有一个正整数N(N<=10)表示测试数据的组数。
 每组测试数据的第一行有两个正整数P,Q(P<=1000,Q<=2000)，分别表示这个画中有多少个顶点和多少条连线。（点的编号从1到P）
 随后的Q行，每行有两个正整数A,B(0<A,B<P)，表示编号为A和B的两点之间有连线。
 输出
 如果存在符合条件的连线，则输出"Yes",
 如果不存在符合条件的连线，输出"No"。
 样例输入
 2
 4 3
 1 2
 1 3
 1 4
 4 5
 1 2
 2 3
 1 3
 1 4
 3 4
 样例输出
 No
 Yes
 */
//Wrong Answer
public class Title42 {
    final static int MAXP = 1005;
    static int[] parent = new int[MAXP];
    static int[] size = new int[MAXP];//树深度
    static int[] count = new int[MAXP];//子孙数量 /*只能得出联通，不能判断一笔画*/
    //static int max;

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);


        int loop = input.nextInt();
        int p = 0, q = 0;

        while (loop-- > 0) {
            p = input.nextInt();
            q = input.nextInt();
            for (int i = 1; i <= p; i++) {
                parent[i] = i;
                size[i] = 1;
                count[i] = 0;
            }
            //max = -1;

            int point1 = 0, point2 = 0;
            while (q-- > 0) {
                point1 = input.nextInt();
                point2 = input.nextInt();

                union(point1, point2);
            }

            /*if (max == p)
                System.out.print("Yes");
            else
                System.out.print("No");*//**连通*/

            int indiv = 0, sum = 0;//独立体联组的数目，奇数连接的点(欧拉)
            for (int i = 1; i <= p; i++) {
                if (parent[i] == i){
                    indiv++;
                    if (indiv>1)
                        break;
                }
                if ((count[i] & 1) != 0)//奇数度
                    sum++;
            }

            if (indiv > 1){
                System.out.println("No");
                continue;
            }

            if (sum == 0 || sum == 2){
                System.out.println("Yes");
            }else {
                System.out.println("No");
            }
        }
    }

    private static int find(int point) {
        while (point != parent[point])
            point = parent[point];

        return point;
    }

    private static void union(int point1, int point2) {
        int root1 = find(point1);
        int root2 = find(point2);

        if (root1 != root2) {
            if (size[root1] > size[root2]) {/**哪颗树大哪颗做root*/
                parent[root2] = root1;
                size[root1]++;
                /*count[root1] += count[root2];
                if (count[root1] > max)
                    max = count[root1];*/
            } else {
                parent[root1] = root2;
                size[root2]++;
                /*count[root2] += count[root1];
                if (count[root2] > max)
                    max = count[root2];*/
            }

        }
        //单点加入后会成环就允许加入
        //parent[point1] = root2;//也不改变树序
        count[point2]++;//size(深度)不加
        count[point1]++;
    }


}
