package Programmers_68935;

class Solution {
    public int solution(int n) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();

        while(n>0){
            sb.append(n%3);
            n=n/3;
        }

        int value = 1;
        for(int i=sb.toString().length()-1;i>=0;i--){
            int v = (int)sb.charAt(i)-48;
            answer+=value*v;
            value*=3;
        }
        return answer;
    }
}