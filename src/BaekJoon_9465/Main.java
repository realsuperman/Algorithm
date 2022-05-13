package BaekJoon_9465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            int length = Integer.parseInt(br.readLine());
            int[][] array = new int[2][length];
            for(int j=0;j<2;j++){
                String[] str = br.readLine().split(" ");
                for(int k=0;k<str.length;k++){
                    array[j][k] = Integer.parseInt(str[k]);
                }
            }
            System.out.println(dp(array,length));
        }
    }
    public static int dp(int[][] array,int length){
        int max = array[0][0]>array[1][0]?array[0][0]:array[1][0];
        for(int i=1;i<length;i++){
            int v1 = array[0][i];
            int v2 = array[1][i];
            int v3 = 0;

            array[0][i] += array[1][i-1];
            array[1][i] += array[0][i-1];

            if(i-2>=0){
                v3 = array[0][i-2]>array[1][i-2]?array[0][i-2]:array[1][i-2];
                if(v1+v3 > array[0][i]) array[0][i] = v1+v3;
                if(v2+v3 > array[1][i]) array[1][i] = v2+v3;
            }
            max = max<array[0][i]?array[0][i]:max;
            max = max<array[1][i]?array[1][i]:max;
        }
        return max;
    }
}