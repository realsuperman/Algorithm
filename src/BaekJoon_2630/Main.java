package BaekJoon_2630;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int[][] array;
    static int blue,white;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        array = new int[n][n];
        for(int i=0;i<n;i++){
            String[] str = br.readLine().split(" ",n);
            for(int j=0;j<n;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }
        paper(0,0,n,n,n*n);
        System.out.print(white + "\n"+blue);
    }
    public static void paper(int fromX,int fromY,int x,int y,int n){
        if(n<=1){
            if(array[fromX][fromY] == 0){
                white++;
            }else{
                blue++;
            }
            return;
        }

        boolean whiteSw = false;
        boolean blueSw = false;

        for(int i=fromX;i<x;i++){
            for(int j=fromY;j<y;j++){
                if(array[i][j] == 0){
                    whiteSw = true;
                }else{
                    blueSw = true;
                }
            }
        }
        if(whiteSw&&blueSw){ // 4방향으로 재귀
            n = n/4;

            paper(fromX, fromY, (fromX + x) / 2, (fromY + y) / 2,n);
            paper((fromX + x) / 2, (fromY + y) / 2, x, y,n);
            paper(fromX, (fromY + y) / 2, (fromX + x) / 2, y,n);
            paper((fromX + x) / 2, fromY, x, (fromY + y) / 2,n);
        }else{
            if(whiteSw){
                white++;
            }else{
                blue++;
            }
        }

    }

}