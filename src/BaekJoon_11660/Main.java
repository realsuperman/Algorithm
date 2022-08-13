package BaekJoon_11660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int[][] array = new int[N][N];
        int[][] dp = new int[N][N];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        for(int i=0;i<N;i++){
            dp[i][0] = array[i][0];
            for(int j=1;j<N;j++) dp[i][j] = dp[i][j-1]+array[i][j];
        }

        StringBuilder sb = new StringBuilder();
        while(M-->0){
            str = br.readLine().split(" ");
            int fromX = Integer.parseInt(str[0])-1;
            int fromY = Integer.parseInt(str[1])-1;
            int endX = Integer.parseInt(str[2])-1;
            int endY = Integer.parseInt(str[3])-1;
            if(fromX==endX && fromY==endY){ sb.append(array[fromX][fromY]+"\n"); continue;}

            int sum = 0;
            for(int i=fromX;i<=endX;i++){
                sum += dp[i][endY];
                if(fromY-1>=0) sum-=dp[i][fromY-1];
            }
            sb.append(sum+"\n");
        }
        System.out.println(sb);
    }
}