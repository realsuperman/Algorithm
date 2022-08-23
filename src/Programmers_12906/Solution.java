package Programmers_12906;

import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> queue = new Stack<>();

        for(int v : arr){
            if(queue.size()>0 && queue.peek()==v) queue.pop();
            queue.add(v);
        }

        int[] answer = new int[queue.size()];
        int index = 0;
        for(int v : queue ) answer[index++]=v;
        return answer;
    }
}