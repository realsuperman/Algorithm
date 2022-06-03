package BaekJoon_14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int[] dice = new int[7];
    public static int x,y,N,M;
    public static int[][] array;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        x = Integer.parseInt(str[2]);
        y = Integer.parseInt(str[3]);
        int K = Integer.parseInt(str[4]);

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }
        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++){
            move(Integer.parseInt(str[i]));
        }
    }
    public static void move(int direction) {
        if (direction == 1) { // 동
            if(y+1<M){
                y++;
                change(direction);
            }else{
                return;
            }
        } else if (direction == 2) { // 서
            if(y-1>=0){
                y--;
                change(direction);
            }else{
                return;
            }
        } else if (direction == 3) { // 북
            if(x-1>=0){
                x--;
                change(direction);
            }else{
                return;
            }

        } else { // 남
            if(x+1<N){
                x++;
                change(direction);
            }else{
                return;
            }
        }
        if (array[x][y] == 0) {
            array[x][y] = dice[6];
        }
        else {
            dice[6] = array[x][y];
            array[x][y] = 0;
        }
        System.out.println(dice[1]);
    }

    public static void change(int d){
        int temp = dice[1];
        if (d == 1) {
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;
        }
        else if (d == 2) {
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        }
        else if (d == 3) {
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        }
        else {
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        }
    }
}