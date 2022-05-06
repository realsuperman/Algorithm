package BaekJoon_1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[][] check;
    static int cnt = 0;
    static int row,column,value;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int i=0;
        int[][] array;
        StringBuilder sb = new StringBuilder();

        while(i<n){
            String str[] = br.readLine().split(" ",3);
            row = Integer.parseInt(str[0]);column = Integer.parseInt(str[1]);value = Integer.parseInt(str[2]);
            array = new int[row][column];
            check = new boolean[row][column];
            cnt = 0;

            for(int j=0;j<value;j++){
                str = br.readLine().split(" ",2);
                array[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = 1;
            }

            for(int j=0;j<row;j++){
                for(int k=0;k<column;k++){
                    if(array[j][k] == 1 && !check[j][k]){
                        cnt++;
                        dfs(array,j,k);
                    }
                }
            }

            sb.append(cnt+"\n");
            i++;
        }
        System.out.print(sb);
    }
    public static void dfs(int array[][],int index1,int index2){
        if(check[index1][index2]) return;
        check[index1][index2] = true;
        if(index1-1>=0 && array[index1-1][index2] == 1) dfs(array,index1-1,index2);
        if(index2-1>=0 && array[index1][index2-1] == 1) dfs(array,index1,index2-1);
        if(index2+1<column && array[index1][index2+1] == 1) dfs(array,index1,index2+1);
        if(index1+1<row && array[index1+1][index2] == 1) dfs(array,index1+1,index2);
    }
}