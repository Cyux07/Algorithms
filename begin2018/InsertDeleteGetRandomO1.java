import java.util.*;

/**
 * Created by Administrator on 2018/2/26.
 * Design a data structure that supports all following operations in average O(1) time.

 Note: Duplicate elements are allowed.
 insert(val): Inserts an item val to the collection.
 remove(val): Removes an item val from the collection if present.
 getRandom: Returns a random element from current collection of elements.
 The probability of each element being returned is linearly related to the number of same value the collection contains.
 */

/*
["RandomizedCollection","insert","insert","insert","getRandom","remove","getRandom"]
[[],[1],[1],[2],[],[1],[]]
*/

/*
["RandomizedCollection","insert","remove","insert","remove","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom"]
[[],[0],[0],[-1],[0],[],[],[],[],[],[],[],[],[],[]]
*/

/*
["RandomizedCollection","insert","insert","insert","insert","insert","insert","remove","remove","remove","remove","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom"]
[[],[1],[1],[2],[1],[2],[2],[1],[2],[2],[2],[],[],[],[],[],[],[],[],[],[]]
*/

public class InsertDeleteGetRandomO1 {
    public RandomizedCollection getCollection(){
        return new RandomizedCollection();
    }
    public static void main(String[] s) {
        // Init an empty collection.
        RandomizedCollection collection = new InsertDeleteGetRandomO1().getCollection();

/*// Inserts 1 to the collection. Returns true as the collection did not contain 1.
        collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
        collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
        collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
        collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
        collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
        collection.getRandom();*/
        collection.insert(1);
        collection.insert(1);
        collection.insert(2);
        collection.insert(1);
        collection.insert(2);
        collection.insert(2);
        collection.remove(1);
        collection.remove(2);
        collection.remove(2);
        collection.remove(2);
        collection.getRandom();
        collection.getRandom();
        collection.getRandom();
        collection.getRandom();
        collection.getRandom();
        collection.getRandom();
        collection.getRandom();
        collection.getRandom();
    }

    //59.25%
    class RandomizedCollection {
        private Map<Integer, Set<Integer>> loca;//<num, <locations>>
        private List<Integer> nums;//has duplicate
        private Random random;

        /** Initialize your data structure here. */
        public RandomizedCollection() {
            loca = new HashMap<>();
            nums = new ArrayList<>();
            random = new Random();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {//不含返回true
            boolean uncontained = false;
            if (!loca.containsKey(val)) {
                uncontained = true;
                loca.put(val, new HashSet<>());
            }
            if (loca.get(val).size() == 0)
                uncontained = true;
            loca.get(val).add(nums.size());
            nums.add(val);

            return uncontained;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {//含 返回true
            if (loca.containsKey(val) && loca.get(val).size() > 0) {
                int local = loca.get(val).iterator().next();
                int size = nums.size();
                if (local != nums.size() - 1) {//删掉数字后把最后一位往前面删掉的空位挪，避免后边的元素下标变化
                    int lastVal = nums.get(size - 1);
                    loca.get(val).remove(local);
                    loca.get(lastVal).remove(size - 1);
                    loca.get(lastVal).add(local);
                    nums.set(local, lastVal);
                }else {
                    loca.get(val).remove(size - 1);
                }
                nums.remove(size - 1);
                return true;
            }

            return false;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return nums.get(random.nextInt(nums.size()));//nextInt: 0 <= num < bound
        }
    }

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
