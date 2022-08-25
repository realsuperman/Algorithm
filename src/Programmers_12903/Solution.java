package Programmers_12903;

class Solution {
    public String solution(String s) {
        String answer = "";
        if(s.length()%2==0){
            int start = s.length()/2-1;
            answer += s.charAt(start);
            answer += s.charAt(++start);
        }else{
            answer+=s.charAt(s.length()/2);
        }

        return answer;
    }
}