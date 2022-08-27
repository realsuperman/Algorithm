package Programmers_12913;

class Solution {
    int solution(int[][] land) {
        int[][] dp = new int[land.length][4];
        for(int i=0;i<4;i++) dp[0][i]=land[0][i];

        for(int i=1;i<dp.length;i++){
            for(int j=0;j<4;j++){
                if(j==0){
                    int MAX = dp[i-1][1]>dp[i-1][2]?dp[i-1][1]:dp[i-1][2];
                    MAX = dp[i-1][3]>MAX?dp[i-1][3]:MAX;
                    dp[i][j] = land[i][j]+MAX;
                }else if(j==1){
                    int MAX = dp[i-1][0]>dp[i-1][2]?dp[i-1][0]:dp[i-1][2];
                    MAX = dp[i-1][3]>MAX?dp[i-1][3]:MAX;
                    dp[i][j] = land[i][j]+MAX;
                }else if(j==2){
                    int MAX = dp[i-1][0]>dp[i-1][1]?dp[i-1][0]:dp[i-1][1];
                    MAX = dp[i-1][3]>MAX?dp[i-1][3]:MAX;
                    dp[i][j] = land[i][j]+MAX;
                }else{
                    int MAX = dp[i-1][0]>dp[i-1][1]?dp[i-1][0]:dp[i-1][1];
                    MAX = dp[i-1][2]>MAX?dp[i-1][2]:MAX;
                    dp[i][j] = land[i][j]+MAX;
                }
            }
        }
        int MAX = Integer.MIN_VALUE;
        for(int i=0;i<4;i++) MAX = MAX<dp[dp.length-1][i]?dp[dp.length-1][i]:MAX;

        return MAX;
    }
}