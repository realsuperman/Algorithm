package Programmers_12954;

class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        int index = 0;

        long temp = x;
        while(n-->0){
            answer[index++] = temp;
            temp = temp+x;
        }
        return answer;
    }
}