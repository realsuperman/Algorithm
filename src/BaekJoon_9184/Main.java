package BaekJoon_9184;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][][] array = new int[21][21][21];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<21;i++){
            for(int j=0;j<21;j++){
                for(int k=0;k<21;k++){
                    if(i <= 0 || j <= 0 || k <= 0){
                        array[i][j][k] = 1;
                    }
                    else if(i > 20 || j > 20 || k > 20){
                        array[i][j][k] = array[20][20][20];
                    }
                    else if(i < j && j < k){
                        array[i][j][k] =  array[i][j][k-1] + array[i][j-1][k-1]-array[i][j-1][k];
                    }
                    else{
                        array[i][j][k] =  array[i-1][j][k] + array[i-1][j-1][k] + array[i-1][j][k-1] - array[i-1][j-1][k-1];
                    }

                }
            }
        }
        while(true){
            String str = br.readLine();
            if(str.equals("-1 -1 -1")) break;
            String[] a = str.split(" ",3);
            int index1 = Integer.parseInt(a[0]);
            int index2 = Integer.parseInt(a[1]);
            int index3 = Integer.parseInt(a[2]);
            int value;

            if(index1 <= 0 || index2 <= 0 || index3 <= 0) value = 1;
            else if(index1 > 20 || index2 > 20 || index3 > 20) value = array[20][20][20];
            else value = array[index1][index2][index3];

            sb.append("w("+index1+", "+index2+", "+index3+") = "+value+"\n");
        }
        System.out.println(sb);
    }
}