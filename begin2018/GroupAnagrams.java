import java.util.*;

/**
 * Created by Administrator on 2018/2/2.
 */
public class GroupAnagrams {
    public static void main(String[] s) {
        for(List<String> res : new GroupAnagrams().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"})) {
            for (String re : res)
                System.out.print(re);
            System.out.println();
        }
//        For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
//        Return:
//        [
//        ["ate", "eat","tea"],
//        ["nat","tan"],
//        ["bat"]
//        ]


        System.out.println();
        for(List<String> res : new GroupAnagrams().groupAnagrams(new String[]{"cab","pug","pei","nay","ron","rae","ems","ida","mes"})) {
            for (String re : res)
                System.out.print(re);
            System.out.println();
        }
        //2.
        //in ["cab","pug","pei","nay","ron","rae","ems","ida","mes"]
        //wrong [["pei","rae"],["ems","mes"],["cab"],["pug"],["nay"],["ida"],["ron"]]
        //expect [["ida"],["ems","mes"],["rae"],["ron"],["cab"],["pei"],["pug"],["nay"]]

        System.out.println();
        for(List<String> res : new GroupAnagrams().groupAnagrams(new String[]{"hos","boo","nay","deb","wow","bop","bob","brr","hey","rye","eve","elf","pup","bum","iva","lyx","yap","ugh","hem","rod","aha","nam","gap","yea","doc","pen","job","dis","max","oho","jed","lye","ram","pup","qua","ugh","mir","nap","deb","hog","let","gym","bye","lon","aft","eel","sol","jab"})) {
            for (String re : res)
                System.out.print(re);
            System.out.println();
        }
        //3.
        //["hos","boo","nay","deb","wow","bop","bob","brr","hey","rye","eve","elf","pup","bum","iva","lyx","yap","ugh","hem","rod","aha","nam","gap","yea","doc","pen","job","dis","max","oho","jed","lye","ram","pup","qua","ugh","mir","nap","deb","hog","let","gym","bye","lon","aft","eel","sol","jab"]
        //[["sol"],["wow"],["gap"],["hem"],["yap"],["bum"],["ugh","ugh"],["aha"],["jab"],["eve"],["bop"],["lyx"],["jed"],["iva"],["rod"],["boo"],["brr"],["hog"],["nay"],["mir"],["deb","deb"],["aft"],["dis"],["yea"],["hos"],["rye"],["hey"],["doc"],["bob"],["eel"],["pen"],["job"],["max"],["oho"],["lye"],["ram"],["nap"],["elf"],["qua"],["pup","pup"],["let"],["gym"],["nam"],["bye"],["lon"]]
    }

    /**85.46%*/
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> resultMap = new HashMap<>();
        List<String> kin;
        String key;
        for(String anagram : strs) {
            key = value(anagram);
            if(!resultMap.containsKey(key)) {
                kin = new ArrayList<>();
                resultMap.put(key, kin);
            }else {
                kin = resultMap.get(key);
            }
            kin.add(anagram);
        }
        
        return new ArrayList<>(resultMap.values());
    }

    //dhl != ill, eat = eta, boo != bob
    /*int value(String anagram) {
        int bin = 0, an, pos = 1;
        for(char ana : anagram.toCharArray()) {
            an = ana - 97;
            pos = 1;
            while (an-- > 0) {
                pos <<= 1;
            }
            bin = bin ^ pos;//position
        }
        return bin;
    }*/
    String value(String anagram) {
        char[] anas = anagram.toCharArray();
        Arrays.sort(anas);
        return String.valueOf(anas);
    }
}
