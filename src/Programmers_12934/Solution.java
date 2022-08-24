package Programmers_12934;

class Solution {
    public long solution(long n) {
        long answer = 0;
        String str = new String(String.valueOf(Math.sqrt(n)));

        boolean chk = false;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='.'){chk=true; continue;}
            if(chk){
                if(str.charAt(i)=='0' && i+1>=str.length()){
                    answer=(int)Math.sqrt(n);
                    answer++;
                }else{
                    answer=-1L;
                }
                break;
            }
        }
        if(answer==-1) return -1;
        return answer*answer;
    }
}