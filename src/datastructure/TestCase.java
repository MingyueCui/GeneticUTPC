package datastructure;

import java.util.*;

public class TestCase {
    public static void printTs(Hashtable<String,Boolean> testcase){
        ArrayList<String> list = new ArrayList(testcase.keySet());
        Collections.sort(list,new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(0+o1.charAt(0)>0+o2.charAt(0)){
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        for (String key : list) {
            System.out.print("字句:"+ key +"_"+testcase.get(key)+" ");
        }
        System.out.println(" ");
    }
    public static void printTs(List<Literal> testcases){
        List<Literal> temp = new ArrayList<>(testcases);
        temp.sort(new Comparator<Literal>() {
            @Override
            public int compare(Literal o1, Literal o2) {
                if (o1.token.charAt(0) > o2.token.charAt(0)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (Literal key : temp) {
            System.out.print("字句:"+ key.token +" 取值："+key.value+" ");
        }
        System.out.println(" ");
    }
    public static List<Literal> sortTestcases(HashSet<Literal> testcases){
        List<Literal> rs = new ArrayList<>(testcases);
        rs.sort(new Comparator<Literal>() {
            @Override
            public int compare(Literal o1, Literal o2) {
                if (o1.token.charAt(0) > o2.token.charAt(0)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return rs;
    }
    public static List<Literal> sortTestcases(List<Literal> rs){
        rs.sort(new Comparator<Literal>() {
            @Override
            public int compare(Literal o1, Literal o2) {
                if (o1.token.charAt(0) > o2.token.charAt(0)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return rs;
    }
}
