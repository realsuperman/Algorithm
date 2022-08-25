package Programmers_12932;

class Solution {
    public int[] solution(long n) {
        StringBuilder sb = new StringBuilder(String.valueOf(n));
        sb.reverse();
        int[] answer = new int[sb.toString().length()];
        for(int i=0;i<sb.toString().length();i++){
            answer[i] = sb.charAt(i)-'0';
        }
        return answer;
    }
}