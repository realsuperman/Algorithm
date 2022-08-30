package Programmers_42895;

import java.util.*;
class Solution {
    public int solution(int N, int number) {
        String str = Integer.toString(N);
        Set<Integer> set1 = new HashSet<>();
        set1.add(Integer.parseInt(str));
        for(int i : set1) if(i==number) return 1;

        Set<Integer> set2 = new HashSet<>();
        str+=Integer.toString(N);
        set2.add(Integer.parseInt(str));
        set2.add(N+N);set2.add(N-N);set2.add(N*N);set2.add(N/N);
        for(int i : set2) if(i==number) return 2;

        Set<Integer> set3 = new HashSet<>();
        str+=Integer.toString(N);
        set3.add(Integer.parseInt(str));
        cal(set1,set2,set3);
        for(int i : set3) if(i==number) return 3;

        Set<Integer> set4 = new HashSet<>();
        str+=Integer.toString(N);
        set4.add(Integer.parseInt(str));
        cal(set1,set3,set4);
        cal(set2,set2,set4);
        for(int i : set4) if(i==number) return 4;

        Set<Integer> set5 = new HashSet<>();
        str+=Integer.toString(N);
        set5.add(Integer.parseInt(str));
        cal(set1,set4,set5);
        cal(set2,set3,set5);
        for(int i : set5) if(i==number) return 5;

        Set<Integer> set6 = new HashSet<>();
        str+=Integer.toString(N);
        set6.add(Integer.parseInt(str));
        cal(set1,set5,set6);
        cal(set2,set4,set6);
        cal(set3,set3,set6);
        for(int i : set6) if(i==number) return 6;

        Set<Integer> set7 = new HashSet<>();
        str+=Integer.toString(N);
        set7.add(Integer.parseInt(str));
        cal(set1,set6,set7);
        cal(set2,set5,set7);
        cal(set3,set4,set7);
        for(int i : set7) if(i==number) return 7;

        Set<Integer> set8 = new HashSet<>();
        str+=Integer.toString(N);
        set8.add(Integer.parseInt(str));
        cal(set1,set7,set8);
        cal(set2,set6,set8);
        cal(set3,set5,set8);
        cal(set4,set4,set8);
        for(int i : set8) if(i==number) return 8;

        return -1;
    }
    public void cal(Set<Integer> set1, Set<Integer> set2,Set<Integer> set3){
        for(int i : set1){
            for(int j : set2){
                if((i+j) > 0 ) set3.add(i+j);
                if((i-j) > 0 ) set3.add(i-j);
                if((i*j) > 0 ) set3.add(i*j);
                if(j>0 && (i/j) > 0 ) set3.add(i/j);
                if((j-i) > 0 ) set3.add(j-i);
                if(i>0 && (j/i) > 0 ) set3.add(j/i);
            }
        }
    }
}