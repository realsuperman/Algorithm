package BaekJoon_1965;

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
        for(int i=0;i<N;i++) array[i]= Integer.parseInt(str[i]);
        dp[0]=1;

        int max = 1;
        for(int i=1;i<N;i++){
            int cnt = 1;
            for(int j=i-1;j>=0;j--){
                if(array[j]<array[i] && cnt<dp[j]+1) cnt=dp[j]+1;
            }

            dp[i]=cnt;
            max = max>dp[i]?max:dp[i];
        }
        System.out.println(max);
    }
}