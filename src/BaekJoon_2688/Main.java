package BaekJoon_2688;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[10][1001];
        for(int i=0;i<=9;i++) dp[i][1]=1;
        for(int i=2;i<=1000;i++){
            for(int j=0;j<=9;j++){
                for(int k=j;k<=9;k++){
                    dp[j][i]+=dp[k][i-1];
                }
            }
        }
        while(N-->0){
            int v = Integer.parseInt(br.readLine());
            long sum = 0;
            for(int i=0;i<=9;i++){
                sum+=dp[i][v];
            }
            System.out.println(sum);
        }
    }
}