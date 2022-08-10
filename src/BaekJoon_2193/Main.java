package BaekJoon_2193;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] array = new long[2][N+1];
        array[1][0] = 0; array[1][1]=1;

        for(int i=2;i<=N;i++) {
            for (int j = 0; j < 2; j++) {
                if(j==0) array[j][i] = array[0][i-1]+array[1][i-1];
                else array[j][i] = array[0][i-1];
            }
        }

        long sum=array[0][N]+array[1][N];
        System.out.println(sum);
    }
}