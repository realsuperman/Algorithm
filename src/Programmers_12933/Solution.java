package Programmers_12933;

import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        StringBuilder sb = new StringBuilder(String.valueOf(n));
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<sb.toString().length();i++){
            list.add((int)sb.charAt(i)-48);
        }
        Collections.sort(list,Collections.reverseOrder());
        String str = "";
        for(int v : list) str+=v;
        return Long.parseLong(str);
    }
}