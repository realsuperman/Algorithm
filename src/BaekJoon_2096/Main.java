package BaekJoon_2096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][3];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        System.out.println(findPath(true)+" "+findPath(false));
    }
    public static int findPath(boolean maxYn){
        int[][] dp = new int[N][3];
        for(int i=0;i<3;i++) dp[0][i] = array[0][i];

        for(int i=1;i<N;i++){
            for(int j=0;j<3;j++){
                int[] value = new int[3];
                if(maxYn) Arrays.fill(value,Integer.MIN_VALUE);
                else Arrays.fill(value,Integer.MAX_VALUE);

                if(i-1>=0) value[0] = array[i][j]+dp[i-1][j];
                if(i-1>=0 && j-1>=0) value[1] = array[i][j]+dp[i-1][j-1];
                if(i-1>=0 && j+1<3) value[2] = array[i][j]+dp[i-1][j+1];
                Arrays.sort(value);

                if(maxYn) dp[i][j] = value[2];
                else dp[i][j] = value[0];
            }
        }

        int sum;
        if(maxYn) sum = Integer.MIN_VALUE;
        else sum = Integer.MAX_VALUE;

        for(int i=0;i<3;i++){
            if(maxYn){
                if(sum<dp[N-1][i]) sum = dp[N-1][i];
            }else{
                if(sum>dp[N-1][i]) sum = dp[N-1][i];
            }
        }
        return sum;
    }
}