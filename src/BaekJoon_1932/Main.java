package BaekJoon_1932;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] array = new int[n][n];
        String[] str;
        int max;

        for(int i=0;i<n;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<n;j++){
                max = array[i-1][j];
                if(j-1>=0 && max<array[i-1][j-1]) max = array[i-1][j-1];
                array[i][j] = array[i][j]+max;
            }
        }

        max = -1;
        for(int j=0;j<n;j++){
            if(max < array[n-1][j]) max = array[n-1][j];
        }
        System.out.print(max);
    }
}