package main;

import utils.DnfTransfer;

public class Main {
    public static void main(String[] args){
        String expr = DnfTransfer.toDnf("~(a&b)");
        System.out.println(expr);
    }
}
