package BaekJoon_2156;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N==1){System.out.println(br.readLine()); return;}
        int[] array = new int[N+1];
        int[] dp = new int[N+1];
        for(int i=0;i<N;i++) array[i+1]= Integer.parseInt(br.readLine());
        dp[1]=array[1]; dp[2]=array[1]+array[2];

        for(int i=3;i<=N;i++){
            dp[i] = Math.max(array[i]+array[i-1]+dp[i-3],array[i]+dp[i-2]);
            dp[i] = Math.max(dp[i-1], dp[i]); // 나를 안먹고 연속적인 i-1 or i를 택한다
        }
        System.out.println(dp[N]);
    }
}