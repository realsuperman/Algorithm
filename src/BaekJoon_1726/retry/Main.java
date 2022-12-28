package BaekJoon_1726.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] array;
    static int startX,startY,startDirection,endX,endY,endDirection;
    static int[] eastX = {0,0,0};
    static int[] eastY = {1,2,3};
    static int[] westX = {0,0,0};
    static int[] westY = {-1,-2,-3};
    static int[] southX = {1,2,3};
    static int[] southY = {0,0,0};
    static int[] northX = {-1,-2,-3};
    static int[] northY = {0,0,0};
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        str = br.readLine().split(" ");
        startX = Integer.parseInt(str[0])-1;
        startY = Integer.parseInt(str[1])-1;
        startDirection = Integer.parseInt(str[2])-1;
        str = br.readLine().split(" ");
        endX = Integer.parseInt(str[0])-1;
        endY = Integer.parseInt(str[1])-1;
        endDirection = Integer.parseInt(str[2])-1;
        bfs();
        System.out.println(result);
    }

    public static void bfs(){
        boolean[][][] check = new boolean[N][M][4];
        check[startX][startY][startDirection] = true;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY,startDirection,0));

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x == endX && v.y == endY){
                if(v.direction == endDirection){
                    result = result>v.time?v.time:result;
                    continue;
                }

                if( rotateDirection(v.direction,true)==endDirection || rotateDirection(v.direction,false)==endDirection){
                    result = result>v.time+1?v.time+1:result;
                    continue;
                }

            }

            int value = rotateDirection(v.direction,true);
            if(!check[v.x][v.y][value]){
                check[v.x][v.y][value]=true;
                queue.add(new V(v.x,v.y,value,v.time+1));
            }

            value = rotateDirection(v.direction,false);
            if(!check[v.x][v.y][value]){
                check[v.x][v.y][value] = true;
                queue.add(new V(v.x,v.y,value,v.time+1));
            }

            for(int i=0;i<3;i++){
                int x,y;

                if(v.direction==0){
                    x = eastX[i]+v.x;
                    y = eastY[i]+v.y;
                }else if(v.direction==1){
                    x = westX[i]+v.x;
                    y = westY[i]+v.y;
                }else if(v.direction==2){
                    x = southX[i]+v.x;
                    y = southY[i]+v.y;
                }else{
                    x = northX[i]+v.x;
                    y = northY[i]+v.y;
                }

                if(x>=N || x<0 || y>=M || y<0 || array[x][y]==1) break;
                if(check[x][y][v.direction]) continue;
                check[x][y][v.direction] = true;
                queue.add(new V(x,y,v.direction,v.time+1));
            }

        }
    }

    public static int rotateDirection(int direction, boolean isLeft){
        if(isLeft){ // 왼쪽으로 회전
            if(direction==0) return 3;
            else if(direction==1) return 2;
            else if(direction==2) return 0;
            else return 1;
        }else{ // 오른쪽으로 회전
            if(direction==0) return 2;
            else if(direction==1) return 3;
            else if(direction==2) return 1;
            else return 0;
        }
    }
}

class V{
    int x;
    int y;
    int direction; // 동,서,남,북
    int time;
    public V(int x,int y,int direction,int time){
        this.x=x;
        this.y=y;
        this.direction=direction;
        this.time=time;
    }
}