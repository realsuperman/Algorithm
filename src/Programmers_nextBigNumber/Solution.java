package Programmers_nextBigNumber;

class Solution {
    public int solution(int n) {
        int answer = 0;
        String str = Integer.toBinaryString(n);

        int oneSize = 0;
        for(int i=0;i<str.length();i++) if(str.charAt(i)=='1') oneSize++;

        for(int i=n+1;i<=1000000;i++){
            int size = 0;
            String temp = Integer.toBinaryString(i);
            for(int j=0;j<temp.length();j++) if(temp.charAt(j)=='1') size++;
            if(size==oneSize){answer=i; break;}
        }
        return answer;
    }
}

