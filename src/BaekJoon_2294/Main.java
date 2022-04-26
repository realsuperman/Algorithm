package BaekJoon_2294;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int[] n = new int[N];
        int find = Integer.parseInt(str[1]);
        for(int i=0;i<N;i++) n[i] = Integer.parseInt(br.readLine());
        System.out.println(coin(n,find));
    }


    public static int coin(int[] array,int find){
        int[] dp = new int[find+1];
        dp[0]=0;
        for(int i=1;i<=find;i++){
            int min = 100000000;
            for(int j=0;j<array.length;j++){
                if(i-array[j]>=0 && min>dp[i-array[j]]){
                    min = dp[i-array[j]];
                }
            }
            dp[i]=min+1;
        }
        return dp[find]==100000001?-1:dp[find];
    }
}