package Programmers_12912;

class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        if(a==b) return a;
        long max = a>b?a:b;
        long min = a>b?b:a;
        for(long i=min;i<=max;i++) answer+=i;
        return answer;
    }
}