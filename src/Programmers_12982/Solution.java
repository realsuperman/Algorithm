package Programmers_12982;

import java.util.*;
class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);
        for(int v : d){
            if(budget>=v){answer++; budget-=v;}
            else break;
        }
        return answer;
    }
}