package Programmers_77484;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        int zeroCnt = 0;
        int cnt = 0;

        for(int v : lottos){
            if(v==0){zeroCnt++; continue;}
            for(int value : win_nums){
                if(v==value){cnt++; break;}
            }
        }

        if(zeroCnt==0){
            answer[0]=rank(cnt);
            answer[1]=rank(cnt);
        }else{
            answer[1]=rank(cnt);
            answer[0]=rank(cnt+zeroCnt);
        }
        return answer;
    }

    public static int rank(int cnt){
        int v = 0;
        if(cnt==6) v = 1;
        else if(cnt==5) v=2;
        else if(cnt==4) v=3;
        else if(cnt==3) v=4;
        else if(cnt==2) v=5;
        else v=6;
        return v;
    }
}