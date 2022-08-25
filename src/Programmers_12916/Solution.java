package Programmers_12916;

class Solution {
    boolean solution(String s) {
        int yCnt = 0;
        int pCnt = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='Y' || s.charAt(i)=='y') yCnt++;
            else if(s.charAt(i)=='p' || s.charAt(i)=='P') pCnt++;
        }

        return yCnt==pCnt?true:false;
    }
}