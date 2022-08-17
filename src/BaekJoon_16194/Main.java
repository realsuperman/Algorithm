package BaekJoon_16194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N+1];
        int[] dp = new int[N+1];

        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++){ array[i+1]= Integer.parseInt(str[i]); dp[i+1]=Integer.MAX_VALUE;}

        for(int i=1;i<=N;i++){
            for(int j=1;j<=i;j++){
                dp[i] = Math.min(dp[i],array[j]+dp[i-j]);
            }
        }
        System.out.println(dp[N]);
    }
}