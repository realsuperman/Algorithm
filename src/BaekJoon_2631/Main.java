package BaekJoon_2631;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        for(int i=0;i<N;i++) array[i]= Integer.parseInt(br.readLine());
        int[] dp = new int[N];

        dp[0]=1;
        int result = 0;
        for(int i=1;i<N;i++){
            int max = 0;
            for(int j=i-1;j>=0;j--){
                if(array[i]>array[j] && max<dp[j]) max = dp[j];
            }
            dp[i] = max+1;
            if(dp[i]>result) result=dp[i];
        }
        System.out.println(N-result);
    }
}