package BaekJoon_16236.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] array;
    static int sharkSize = 2; // 상어 사이즈
    static int eatenFood = 0; // 먹은 물고기양
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        int startX=0,startY=0;

        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                array[i][j]= Integer.parseInt(str[j]);
                if(array[i][j]==9) {array[i][j]=0; startX = i; startY=j;}
            }
        }
        System.out.println(bfs(startX,startY));
    }

    public static int bfs(int startX,int startY){
        int simulCnt = 0;
        Queue<V> queue = new PriorityQueue<>();
        queue.add(new V(startX,startY,0));
        boolean sw = true;

        while(sw) {
            boolean[][] check = new boolean[N][N];
            sw = false;

            while (!queue.isEmpty()) {
                V v = queue.remove();
                if(array[v.x][v.y]!=0 && sharkSize > array[v.x][v.y]){ // 물고기를 찾고 먹을 수 있는 경우
                    eatenFood++;
                    array[v.x][v.y]=0;
                    if(eatenFood==sharkSize){
                        sharkSize++;
                        eatenFood=0;
                    }
                    queue = new PriorityQueue<>();
                    queue.add(new V(v.x,v.y,0));
                    simulCnt+=v.time;
                    sw = true;
                    break;
                }
                check[v.x][v.y]=true;

                for(int i=0;i<4;i++){
                    int x = dx[i]+v.x;
                    int y = dy[i]+v.y;
                    if(x>=N || x<0 || y>=N || y<0 || check[x][y] || sharkSize<array[x][y]) continue;
                    check[x][y]=true;
                    queue.add(new V(x,y,v.time+1));
                }
            }

        }

        return simulCnt;
    }
}

class V implements Comparable<V>{
    int x;
    int y;
    int time;
    public V(int x,int y,int time){
        this.x=x;
        this.y=y;
        this.time=time;
    }
    @Override
    public int compareTo(V v){
        if(v.time == this.time){
            if(v.x == this.x){
                return this.y-v.y;
            }
            return this.x-v.x;
        }
        return this.time-v.time;
    }
}