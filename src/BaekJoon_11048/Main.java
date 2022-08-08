package BaekJoon_11048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int[][] array = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        int[][] dp = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                int[] comp = new int[4];
                if(i-1>=0) comp[0] = array[i][j]+dp[i-1][j];
                if(j-1>=0) comp[1] = array[i][j]+dp[i][j-1];
                if(i-1>=0&& j-1>=0) comp[2] = array[i][j]+dp[i-1][j-1];
                comp[3] = array[i][j];
                Arrays.sort(comp);
                dp[i][j]=comp[3];
            }
        }
        System.out.println(dp[N-1][M-1]);
    }
}