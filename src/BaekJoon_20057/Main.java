package BaekJoon_20057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][][] direct = {
            //left
            { { -1,1,-2,2,-1,1,-1,1,0,0 }, { 1,1,0,0,0,0,-1,-1,-2,-1 } },
            //down
            { { -1,-1,0,0,0,0,1,1,2,1 }, { -1,1,-2,2,-1,1,-1,1,0,0 } },
            //right
            { { -1,1,-2,2,-1,1,-1,1,0,0 }, { -1,-1,0,0,0,0,1,1,2,1 } },
            //top
            { { 1,1,0,0,0,0,-1,-1,-2,-1 }, { -1,1,-2,2,-1,1,-1,1,0,0 } }
    };
    static double[] percent = {0.01, 0.01, 0.02, 0.02, 0.07, 0.07, 0.1, 0.1,0.05};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] array = new int[N+1][N+1];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i+1][j+1]= Integer.parseInt(str[j]);
            }
        }

        int startX = (N+1)/2;
        int startY = (N+1)/2;

        int space = 1;
        int act = 0;
        String direction = "left";
        int remainder = 0;
        while(true){
            boolean chk = false;
            int sand;

            if(direction.equals("left")){
                for(int i=0;i<space;i++){
                    if(startX==1 && startY==1) {chk = true; break;}
                    startY = startY-1;
                    sand = array[startX][startY];

                    for(int j=0;j<10;j++){
                        int x = startX+direct[0][0][j];
                        int y = startY+direct[0][1][j];
                        if(j==9){
                            if (x < 1 || x > N || y < 1 || y > N) remainder += sand;
                            else array[x][y] += sand;
                        }else {
                            int v = (int) (array[startX][startY] * percent[j]);
                            if (x < 1 || x > N || y < 1 || y > N) remainder += v;
                            else array[x][y] += v;
                            sand -= v;
                        }
                    }
                }
                direction = "down";
            }else if(direction.equals("right")){
                for(int i=0;i<space;i++){
                    if(startX==1 && startY==1) {chk = true; break;}
                    startY = startY+1;
                    sand = array[startX][startY];

                    for(int j=0;j<10;j++){
                        int x = startX+direct[2][0][j];
                        int y = startY+direct[2][1][j];
                        if(j==9){
                            if (x < 1 || x > N || y < 1 || y > N) remainder += sand;
                            else array[x][y] += sand;
                        }else {
                            int v = (int)(array[startX][startY] * percent[j]);
                            if (x < 1 || x > N || y < 1 || y > N) remainder += v;
                            else array[x][y] += v;
                            sand -= v;
                        }
                    }
                }
                direction = "top";
            }else if(direction.equals("top")){
                for(int i=0;i<space;i++){
                    if(startX==1 && startY==1) {chk = true; break;}
                    startX = startX-1;
                    sand = array[startX][startY];

                    for(int j=0;j<10;j++){
                        int x = startX+direct[3][0][j];
                        int y = startY+direct[3][1][j];
                        if(j==9){
                            if (x < 1 || x > N || y < 1 || y > N) remainder += sand;
                            else array[x][y] += sand;
                        }else {
                            int v = (int)(array[startX][startY] * percent[j]);
                            if (x < 1 || x > N || y < 1 || y > N) remainder += v;
                            else array[x][y] += v;
                            sand -= v;
                        }
                    }
                }
                direction = "left";
            }else if(direction.equals("down")){
                for(int i=0;i<space;i++){
                    if(startX==1 && startY==1) {chk = true; break;}
                    startX = startX+1;
                    sand = array[startX][startY];

                    for(int j=0;j<10;j++){
                        int x = startX+direct[1][0][j];
                        int y = startY+direct[1][1][j];
                        if(j==9){
                            if (x < 1 || x > N || y < 1 || y > N) remainder += sand;
                            else array[x][y] += sand;
                        }else {
                            int v = (int)(array[startX][startY] * percent[j]);
                            if (x < 1 || x > N || y < 1 || y > N) remainder += v;
                            else array[x][y] += v;
                            sand -= v;
                        }
                    }
                }
                direction="right";
            }

            if(chk) break;
            act++;
            if(act==2){space++; act=0;}
        }
        System.out.println(remainder);
    }
}