package Programmers_12930;

class Solution {
    public String solution(String s) {
        String answer="";
        int index = 0;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==' '){answer+=' '; index=0; continue;}

            if(index%2==0){
                if(s.charAt(i)>='a' && s.charAt(i)<='z'){
                    int ch = (int)s.charAt(i)-32;
                    answer+=(char)ch;
                }else{
                    answer+=s.charAt(i);
                }
            }else{
                if(s.charAt(i)>='A' && s.charAt(i)<='Z'){
                    int ch = (int)s.charAt(i)+32;
                    answer+=(char)ch;
                }else{
                    answer+=s.charAt(i);
                }
            }
            index++;
        }
        return answer;
    }
}