package BaekJoon_20057.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N,startX,startY;
    static int[][] array;
    static int result;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    static int[][][] location = {
            {{-1,0},{1,0},{-2,0},{2,0},{-1,1},{1,1},{-1,-1},{1,-1},{0,-2},{0,-1}}, //좌
            {{0,-1},{0,1},{0,-2},{0,2},{-1,-1},{-1,1},{1,-1},{1,1},{2,0},{1,0}}, //하
            {{-1,0},{1,0},{-2,0},{2,0},{-1,-1},{1,-1},{-1,1},{1,1},{0,2},{0,1}}, //우
            {{0,-1},{0,1},{0,-2},{0,2},{1,-1},{1,1},{-1,-1},{-1,1},{-2,0},{-1,0}} //상
    };
    static List<Double> locationValue = Arrays.asList(0.07,0.07,0.02,0.02,0.01,0.01,0.10,0.10,0.05);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        startX = N/2;
        startY = N/2;
        array = new int[N][N];

        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<N;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        solution();
        System.out.println(result);
    }

    private static void solution() {
        int loop = 1;
        int startDirection = 0; // 좌, 하, 우, 상
        int x = startX;
        int y = startY;
        int check = 0;

        while( !(x==0 && y==0) ){
            for(int i=0;i<loop;i++){
                x+=dx[startDirection];
                y+=dy[startDirection];

                int sum = 0;
                for(int index=0;index<10;index++){
                    int calX = x+location[startDirection][index][0];
                    int calY = y+location[startDirection][index][1];
                    int value;

                    if(index==9) value = array[x][y]-sum;
                    else value = (int) ((double)array[x][y]*locationValue.get(index));
                    if(calX>=N || calX<0 || calY>=N || calY<0) result+=value;
                    else array[calX][calY]+=value;
                    sum+=value;
                }
                array[x][y]=0;
            }

            check++;
            startDirection=(startDirection+1)%4;
            if(check%2==0 && N-1!=loop) loop++;
        }
    }
}