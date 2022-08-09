package BaekJoon_11722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];

        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i] = Integer.parseInt(str[i]);

        int[] dp = new int[N];
        dp[0]=1;
        int max = 1;
        for(int i=1;i<dp.length;i++){
            int min = Integer.MIN_VALUE;
            for(int j=i;j>=0;j--){
                if(array[i]<array[j] && min<dp[j]){
                    min = dp[j];
                }
            }
            if(min==Integer.MIN_VALUE) dp[i]=1;
            else dp[i] = min+1;

            if(max<dp[i]) max=dp[i];
        }
        System.out.println(max);
    }
}