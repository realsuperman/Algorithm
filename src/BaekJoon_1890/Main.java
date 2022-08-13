package BaekJoon_1890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] array = new int[N][N];
        long[][] dp = new long[N][N];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        dp[0][0]=1;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(dp[i][j]==0 || array[i][j]==0 ) continue;
                int v = array[i][j];

                if(i+v<N) dp[i+v][j]+=dp[i][j];
                if(j+v<N) dp[i][j+v]+=dp[i][j];
            }
        }
        System.out.println(dp[N-1][N-1]);
    }
}