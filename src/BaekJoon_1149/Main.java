package BaekJoon_1149;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] array = new int[n][3];
        int[][] value = new int[n][3];
        String[] str;

        for(int i=0;i<n;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<3;j++){
                if(i==0) value[i][j] = Integer.parseInt(str[j]);
                array[i][j] = Integer.parseInt(str[j]);
            }
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<3;j++){
                value[i][j] = array[i][j] + min(value,i-1,j);
            }
        }

        int min = 2147483647;
        for(int i=0;i<3;i++){
            if(min > value[n-1][i]) min = value[n-1][i];
        }
        System.out.print(min);
    }

    public static int min(int[][] array,int row,int column){
        int min = 2147483647;
        for(int i=0;i<3;i++){
            if(i==column) continue;
            if(min > array[row][i]) min = array[row][i];
        }
        return min;
    }

}

