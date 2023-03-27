package BaekJoon_14499.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,M,x,y;
    static int[][] array;
    static int[] value = {0,0,0,0,0,0};
    static int[] dx = {0,0,-1,1}; // 동서북남
    static int[] dy = {1,-1,0,0}; // 동서북남
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        x = Integer.parseInt(str[2]);
        y = Integer.parseInt(str[3]);
        array = new int[N][M];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) solution(Integer.parseInt(str[i])-1);
        System.out.println(sb);
    }

    public static void solution(int direction){
        int tempX = x+dx[direction];
        int tempY = y+dy[direction];
        if(tempX<0 || tempX>=N || tempY<0 || tempY>=M) return;

        x = tempX;
        y = tempY;
        move(direction);
        sb.append(value[0]+"\n");
    }

    public static void move(int direction){
        /*
            0 -> 맨위
            1 -> 위
            2 -> 아래
            3 -> 좌
            4 -> 우
            5 -> 바닥
        */
        boolean check = array[x][y]==0; // true면 주사위의 바닥면에 쓰여 있는 수가 칸에 복사 false면 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되고 칸에 쓰여있는 수는 0이됨
        int[] temp = new int[6]; for(int i=0;i<6;i++) temp[i] = value[i];

        if(direction==0){
            value[0]=temp[3];
            value[4]=temp[0];
            value[5]=temp[4];
            value[3]=temp[5];
        }else if(direction==1){
            value[3]=temp[0];
            value[0]=temp[4];
            value[4]=temp[5];
            value[5]=temp[3];
        }else if(direction==2){
            value[5] = temp[2];
            value[2] = temp[0];
            value[0] = temp[1];
            value[1] = temp[5];
        }else if(direction==3){
            value[2] = temp[5];
            value[0] = temp[2];
            value[1] = temp[0];
            value[5] = temp[1];
        }

        if(check) array[x][y]=value[5];
        else{ value[5]=array[x][y]; array[x][y]=0;}
    }
}