package BaekJoon_1726;

import java.util.*;
import java.io.*;

public class Main{
    public static int solution = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int[][] array = new int[N+1][M+1];
        boolean[][][] check = new boolean[5][N+1][M+1];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                array[i+1][j+1]=Integer.parseInt(str[j]);
            }
        }
        str = br.readLine().split(" ");
        int startX = Integer.parseInt(str[0]);
        int startY = Integer.parseInt(str[1]);
        int startDirection = Integer.parseInt(str[2]);

        str = br.readLine().split(" ");
        int endX = Integer.parseInt(str[0]);
        int endY = Integer.parseInt(str[1]);
        int endDirection = Integer.parseInt(str[2]);

        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY,startDirection,0));
        check[startDirection][startX][startY]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x==endX && v.y==endY){
                if(v.direction==endDirection) solution = solution>v.time?v.time:solution;
                else check(v,endDirection);
                continue;
            }
            if(v.direction==1){
                for(int i=1;i<=3;i++){
                    if(v.y+i<=M && !check[v.direction][v.x][v.y+i]){
                        if(array[v.x][v.y+i]==1) break;
                        check[v.direction][v.x][v.y+i] = true;
                        queue.add(new V(v.x,v.y+i,v.direction,v.time+1));
                    }
                }
                if(!check[4][v.x][v.y]){check[4][v.x][v.y]=true; queue.add(new V(v.x,v.y,4,v.time+1));}
                if(!check[3][v.x][v.y]){check[3][v.x][v.y]=true; queue.add(new V(v.x,v.y,3,v.time+1));}
            }else if(v.direction==2){
                for(int i=1;i<=3;i++){
                    if(v.y-i>=1 && !check[v.direction][v.x][v.y-i]){
                        if(array[v.x][v.y-i]==1) break;
                        check[v.direction][v.x][v.y-i] = true;
                        queue.add(new V(v.x,v.y-i,v.direction,v.time+1));
                    }
                }
                if(!check[4][v.x][v.y]){check[4][v.x][v.y]=true; queue.add(new V(v.x,v.y,4,v.time+1));}
                if(!check[3][v.x][v.y]){check[3][v.x][v.y]=true; queue.add(new V(v.x,v.y,3,v.time+1));}
            }else if(v.direction==3){
                for(int i=1;i<=3;i++){
                    if(v.x+i<=N && !check[v.direction][v.x+i][v.y]){
                        if(array[v.x+i][v.y]==1) break;
                        check[v.direction][v.x+i][v.y] = true;
                        queue.add(new V(v.x+i,v.y,v.direction,v.time+1));
                    }
                }
                if(!check[1][v.x][v.y]){check[1][v.x][v.y]=true; queue.add(new V(v.x,v.y,1,v.time+1));}
                if(!check[2][v.x][v.y]){check[2][v.x][v.y]=true; queue.add(new V(v.x,v.y,2,v.time+1));}
            }else{
                for(int i=1;i<=3;i++){
                    if(v.x-i>=1 && !check[v.direction][v.x-i][v.y]){
                        if(array[v.x-i][v.y]==1) break;
                        check[v.direction][v.x-i][v.y] = true;
                        queue.add(new V(v.x-i,v.y,v.direction,v.time+1));
                    }
                }
                if(!check[1][v.x][v.y]){check[1][v.x][v.y]=true; queue.add(new V(v.x,v.y,1,v.time+1));}
                if(!check[2][v.x][v.y]){check[2][v.x][v.y]=true; queue.add(new V(v.x,v.y,2,v.time+1));}
            }
        }
        System.out.println(solution);
    }
    public static void check(V v,int direction){
        int time = v.time;
        Queue<Direction> queue = new LinkedList<>();
        queue.add(new Direction(v.direction,0));
        while(!queue.isEmpty()){
            Direction dire = queue.remove();
            if(dire.value == direction){
                time+=dire.time;
                break;
            }
            switch(dire.value){
                case 1:
                    queue.add(new Direction(4, dire.time+1));
                    queue.add(new Direction(3, dire.time+1));
                    break;
                case 2 :
                    queue.add(new Direction(3, dire.time+1));
                    queue.add(new Direction(4, dire.time+1));
                    break;
                case 3 :
                    queue.add(new Direction(1, dire.time+1));
                    queue.add(new Direction(2, dire.time+1));
                    break;
                case 4 :
                    queue.add(new Direction(2, dire.time+1));
                    queue.add(new Direction(1, dire.time+1));
                    break;
            }
        }
        solution = solution>time?time:solution;
    }
}

class V{
    int x;
    int y;
    int direction; // 1234 동서남북순
    int time;
    public V(int x,int y,int direction,int time){
        this.x=x;
        this.y=y;
        this.direction=direction;
        this.time=time;
    }
}

class Direction{
    int value;
    int time;
    public Direction(int value,int time){
        this.value=value;
        this.time=time;
    }
}