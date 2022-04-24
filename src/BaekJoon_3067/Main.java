package BaekJoon_3067;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str;
        int[] n;
        int find;
        for(int i=0;i<N;i++){
            cnt = 0;
            br.readLine();
            str = br.readLine().split(" ");
            n = new int[str.length];
            for(int j=0;j<str.length;j++) n[j] = Integer.parseInt(str[j]);
            find = Integer.parseInt(br.readLine());
            coin(n,find);
            System.out.println(cnt);
        }
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