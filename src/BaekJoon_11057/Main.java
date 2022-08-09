package BaekJoon_11057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] array = new long[10][N+1];
        for(int i=0;i<=9;i++) array[i][1] = 1;

        for(int i=2;i<=N;i++){
            for(int j=0;j<=9;j++){
                int sum = 0;
                for(int k=j;k<=9;k++){
                    sum+=array[k][i-1]%10007;
                }
                array[j][i] = sum;
            }
        }

        long sum = 0;
        for(int i=0;i<=9;i++) sum+=array[i][N];
        System.out.println(sum%10007);
    }
}