package Programmers_binaryTransformationRepeat;

import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2]; // 0은 이진변환 횟수, 1은 제거한 0의개수

        StringBuilder sb = new StringBuilder(s);
        while(!sb.toString().equals("1")){
            int zeroSize = 0;
            int oneSize = 0;

            for(int i=0;i<sb.toString().length();i++){
                if(sb.charAt(i)=='0') zeroSize++;
                else oneSize++;
            }
            sb = calculator(oneSize);
            answer[0]+=1;
            answer[1]+=zeroSize;
        }

        return answer;
    }
    public StringBuilder calculator(int v){
        Stack<Integer> stack = new Stack<>();

        while(v>0){
            stack.push(v%2);
            v/=2;
        }

        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) result.append(stack.pop());

        return result;
    }
}