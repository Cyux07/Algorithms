package may2018;

import java.util.Arrays;

/**Contest82. 2-medium
 * 825. 适当年龄的朋友
 * 给出一个表示各自年龄的列表，A君不会向B君发出交友请求——在以下任一情况满足的时候：
 * age[B] <= 0.5 * age[A] + 7
 * age[B] > age[A]
 * age[B] > 100 && age[A] < 100
 * 否则，A将会向B发出朋友请求
 * 如果A请求了B，B不一定可以请求A。人不会自己向自己发请求
 *
 * 1 <= ages.length <= 20000.
 * 1 <= ages[i] <= 120.
 * */

/**0.5 * age[A] + 7 < age[B] <= age[A]
 * and (age[A],age[B]<100 or 100<age[A],age[B]) -> A<100,B>100, A不会向B发，但B可以*/

//[8,85,24,85,69]
public class FriendsOfAppropriateAges {
    @Deprecated
    public int numFriendRequests1(int[] ages) {
        Arrays.sort(ages);
        int requests = 0;
        int hundrad = -1;

        for (int a = 0; a+1 < ages.length; a++) {
            if (ages[a] >= 100) {
                hundrad = a;
            }else if (ages[a + 1] > 100) {
                continue;
            }

            int b = a + 1;
            while ((hundrad > 0 || ages[b] < 100) && b < ages.length) {
                if (ages[b] < 0.5 * ages[a] + 7)
                    requests++;
                else
                    break;
                b++;
            }
        }

        return requests;
    }

    /**accepted*/
    public int numFriendRequests(int[] ages) {
        int[] counts = new int[121]; //0 is empty
        int requests = 0;

        for (int age : ages)
            counts[age]++;
        for (int age : ages) {
            if (age <= 7)
                continue;
            int start = (int) (age*0.5 + 7) + 1; //a*0.5 + 7 < b (no =
            /*if (age > 100 && start < 100)
                start = 100;*/
            for (int i = start; i <= age; i++) {
                requests += counts[i];
                if (i == age)
                    requests -= 1; //minus self
            }
        }

        return requests;
    }
}
