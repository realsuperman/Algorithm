package Programmers_12926;

class Solution {
    public String solution(String s, int n) {
        StringBuilder answer = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==' ') answer.append(" ");
            else{
                char ch = (char)s.charAt(i);
                boolean chk = false;
                if(ch>='a' && ch<='z') chk=true;
                else chk=false;
                int temp = n;
                while(temp-->0){
                    if(chk){
                        ch++;
                        if(ch>'z') ch='a';
                    }else{
                        ch++;
                        if(ch>'Z') ch='A';
                    }
                }
                answer.append(ch);
            }
        }
        return answer.toString();
    }
}