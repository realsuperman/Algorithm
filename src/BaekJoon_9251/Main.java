package BaekJoon_9251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        int[][] dp = new int[b.length()+1][a.length()+1];
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[i].length;j++){
                if(i==0 || j==0) dp[i][j]=0;
            }
        }

        int max = 0;
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[i].length;j++){
                if(a.charAt(j-1)==b.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
                if(max<dp[i][j]) max = dp[i][j];
            }
        }
        System.out.println(max);
    }
}