package BaekJoon_9095;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-->0) System.out.println(solution(Integer.parseInt(br.readLine())));
    }

    public static int solution(int num){
        int[] dp = new int[11];
        dp[0]=1; dp[1]=2; dp[2]=4;
        for(int j=3;j<num;j++) dp[j]=dp[j-3]+dp[j-2]+dp[j-1];
        return dp[num-1];
    }
}