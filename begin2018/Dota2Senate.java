import java.util.LinkedList;

/**
 * Created by Administrator on 2018/2/14.
 */
public class Dota2Senate {
    public static void main(String[] s) {
        //System.out.println(new Dota2Senate().predictPartyVictory("RD"));
        //System.out.println(new Dota2Senate().predictPartyVictory("RDD"));
        //System.out.println(new Dota2Senate().predictPartyVictory("DRDRR"));//Dire
        System.out.println(new Dota2Senate().predictPartyVictory("DDR"));
    }

    /**0.46%*/
    private static final int RADIANT = 0, DIRE = 1;
    public String predictPartyVictorySelf(String senate) {
        char[] senators = senate.toCharArray();
        int[] nums = new int[2], bans = new int[2];
        int flag = 0;//Radiant+, Dire-
        for(char senator : senators)
            if (senator == 'R')
                nums[RADIANT]++;
            else
                nums[DIRE]++;

        int  index = -1;
        char senator;
        while (nums[RADIANT] > 0 && nums[DIRE] > 0) {
            index = (index + 1) % senators.length;
            System.out.println(index);
            senator = senators[index];
            if (senator == 'R') {
                if (bans[RADIANT] > 0) {
                    bans[RADIANT]--;
                    nums[RADIANT]--;
                    senators[index] = 0;//ban it
                } else {
                    bans[DIRE]++;
                }
            } else if (senator == 'D') { /**'D'*/
                if (bans[DIRE] > 0) {
                    bans[DIRE]--;
                    nums[DIRE]--;
                    senators[index] = 0;//ban it
                }else {
                    bans[RADIANT]++;
                }
            }
        }

        return nums[RADIANT] == 0 ? "Dire" : "Radiant";
    }


    /**prime Solution*/
    public String predictPartyVictory(String senate) {
        char[] ss = senate.toCharArray();
        LinkedList<Integer> queueR = new LinkedList<>(), queueD = new LinkedList<>();
        for (int i = 0; i < ss.length; i++)
            if (ss[i] == 'R') queueR.add(i);
            else queueD.add(i);
        while (!queueR.isEmpty() && !queueD.isEmpty()) {
            int r = queueR.removeFirst(), d = queueD.removeFirst();
            if (r < d) queueR.addLast(r + ss.length);
            else queueD.addLast(d + ss.length);
        }
        return queueR.isEmpty() ? "Dire" : "Radiant";
    }
}
