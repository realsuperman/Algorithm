package Programmers_12943;

class Solution {
    public int solution(int num) {
        int answer = 0;
        long value = num;
        while(value!=1){
            if(value%2==0) value/=2;
            else{ value*=3; value++;}
            answer++;
        }

        return answer>500?-1:answer;
    }
}