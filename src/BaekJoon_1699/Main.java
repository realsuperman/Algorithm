package BaekJoon_1699;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[100001];
        dp[1]=1; dp[2]=2; dp[3]=3; dp[4]=1;
        for(int i=5;i<=N;i++){
            dp[i] = Integer.MAX_VALUE;
            for(int j=1;j<=i/2;j++){
                if(j*j==i){dp[i]=1; break;}
                dp[i] = Math.min(dp[j]+dp[i-j],dp[i]);
            }
        }
        System.out.println(dp[N]);
    }
}