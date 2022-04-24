package BaekJoon_2293;

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
        coin(n,find);
        System.out.println(cnt);
    }

    public static void coin(int[] array,int find){
        int[] dp = new int[find+1];
        dp[0] = 1;
        for(int i=0;i<array.length;i++){
            for(int j=1;j<=find;j++){
                if(j-array[i]>=0) dp[j]+=dp[j-array[i]];
            }
        }
        cnt = dp[find];
    }
}