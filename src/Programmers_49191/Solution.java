package Programmers_49191;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        boolean[][] sw = new boolean[n][n];
        for(int i=0; i<results.length; i++){ sw[results[i][0]-1][results[i][1]-1]=true; }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(sw[i][k] && sw[k][j]) sw[i][j]=true;
                }
            }
        }
        for(int i=0;i<n;i++){
            int result=0;
            for(int j=0;j<n;j++){
                //System.out.print(sw[i][j]+" ");
                if(sw[i][j] || sw[j][i]){result++;}
            }
            //System.out.println();
            if(result==n-1){answer++;}
        }

        return answer;
    }
}