package BaekJoon_15989;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N+1];
        for(int i=0;i<N;i++){
            array[i]= Integer.parseInt(br.readLine());
            System.out.println(solution(array[i]));
        }

    }

    private static int solution(int value) {
        int[] dp = new int[value+1];
        for(int i=0;i<dp.length;i++) dp[i]=1;
        int[] array = {2,3};

        for(int i=0;i<array.length;i++){
            for(int j=2+i;j<dp.length;j++){
                dp[j]+=dp[j-array[i]];
            }
        }
        return dp[value];
    }
}