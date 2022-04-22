package BaekJoon_1912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        int[] dp = new int[N];
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i] = Integer.parseInt(str[i]);

        int max = array[0];
        dp[0] = array[0];
        for(int i=1;i<N;i++){
            int temp = array[i]+dp[i-1];
            dp[i] = temp>array[i]?temp:array[i];
            if(max<dp[i]) max = dp[i];
        }

        System.out.println(max);
    }

}