package Programmers_12910;

import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        List<Integer> list = new ArrayList<>();
        for(int v : arr) if(v%divisor==0) list.add(v);
        if(list.size()==0) {int[] t = new int[1]; t[0]=-1; return t;}

        int[] t= new int[list.size()];
        int index = 0;
        Collections.sort(list);
        for(int v : list) t[index++]=v;
        return t;
    }
}