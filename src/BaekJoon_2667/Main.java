package BaekJoon_2667;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static boolean[][] check;
    static int cnt=0;
    public static void main(String[] args)throws Exception {
        int[][] array;
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        array = new int[size][size];
        check = new boolean[size][size];
        String parse;

        for(int i=0;i<size;i++){
            parse = br.readLine();
            for(int j=0;j<size;j++){
                array[i][j] = Integer.parseInt(String.valueOf(parse.charAt(j)));
                check[i][j] = false;
            }
        }

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(array[i][j]==1 && (!check[i][j])){
                    cnt = 0;
                    dfs(array,i,j,size);
                    list.add(cnt+1);
                }
            }
        }
        System.out.println(list.size());
        Collections.sort(list);

        for(int i : list){
            System.out.println(i);
        }

    }
    public static void dfs(int array[][],int i,int j,int size){
        check[i][j] = true;
        if( (j-1>=0) && (array[i][j-1]==1)&& (!check[i][j-1])){ // 좌측
            cnt++;
            dfs(array,i,j-1,size);
        }
        if( (j+1<size) && (array[i][j+1]==1)&& (!check[i][j+1])){ // 우측
            cnt++;
            dfs(array,i,j+1,size);
        }

        if( (i-1>=0) && (array[i-1][j]==1) && (!check[i-1][j])){ // 상단
            cnt++;
            dfs(array,i-1,j,size);
        }
        if( (i+1<size) && (array[i+1][j]==1)&& (!check[i+1][j])){ // 하단
            cnt++;
            dfs(array,i+1,j,size);
        }
    }
}