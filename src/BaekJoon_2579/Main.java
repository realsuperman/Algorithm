package BaekJoon_2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N+1];
        int[] dp = new int[N+1];

        for(int i=1;i<=N;i++) array[i]= Integer.parseInt(br.readLine());
        if(N==1) { System.out.println(array[1]); return;}
        dp[1] = array[1]; dp[2] = array[1]+array[2];
        for(int i=3;i<=N;i++) dp[i] = Math.max(array[i]+array[i-1]+dp[i-3],array[i]+dp[i-2]);
        System.out.println(dp[N]);
    }
}