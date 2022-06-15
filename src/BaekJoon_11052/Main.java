package BaekJoon_11052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N+1];
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i+1] = Integer.parseInt(str[i]);

        int[] dp = new int[N+1];
        for(int i=1;i<=N;i++){
            int value = array[i];
            for(int j=1;j<=N;j++){
                if(j-i<0) continue;
                int temp = dp[j-i];

                int sum =  value+temp;
                if(sum>dp[j]) dp[j]=sum;
            }
        }
        System.out.println(dp[N]);
    }
}