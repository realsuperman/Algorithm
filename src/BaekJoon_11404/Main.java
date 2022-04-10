package BaekJoon_11404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] array = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i!=j && array[i][j]==0) array[i][j] = 100000000;
            }
        }

        int loop = Integer.parseInt(br.readLine());
        for(int i=0;i<loop;i++){
            String[] str = br.readLine().split(" ");
            if(array[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1]!=0){
                if(array[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1]>Integer.parseInt(str[2])) array[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1] = Integer.parseInt(str[2]);
            }else{
                array[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1] = Integer.parseInt(str[2]);
            }
        }

        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(array[i][k]+array[k][j] < array[i][j]) array[i][j] = array[i][k]+array[k][j];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(array[i][j] == 100000000) sb.append(0+" ");
                else sb.append(array[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}