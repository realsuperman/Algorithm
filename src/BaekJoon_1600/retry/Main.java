package BaekJoon_1600.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int W,H,K;
    static int[][] array;
    static boolean[][][] check;

    static int[] saruJumpX = {0,0,1,-1};
    static int[] saruJumpY = {1,-1,0,0};
    static int[] horseJumpX = {-2,-2,-1,-1,1,1,2,2};
    static int[] horseJumpY = {-1,1,-2,2,-2,2,-1,1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        W = Integer.parseInt(str[1]);
        H = Integer.parseInt(str[0]);
        array = new int[W][H];
        check = new boolean[W][H][K+1];
        for(int i=0;i<W;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j] = Integer.parseInt(str[j]);
        }
        bfs();
    }

    public static void bfs(){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(0,0,0,0));
        for(int i=0;i<K;i++) check[0][0][i] = true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x == W-1 && v.y == H-1){System.out.println(v.time); return;}

            if(v.moveCnt<K){
                insertQueue(queue, v.x, v.y, v.time+1, v.moveCnt+1,horseJumpX,horseJumpY);
            }
            insertQueue(queue, v.x, v.y, v.time+1, v.moveCnt,saruJumpX,saruJumpY);
        }
        System.out.println(-1);
    }

    public static void insertQueue(Queue<V> queue, int valueX, int valueY,int time ,
                                   int moveCnt,int[] arrayX, int[] arrayY){
        for(int i=0;i<arrayX.length;i++){
            int x = arrayX[i]+valueX;
            int y = arrayY[i]+valueY;
            if(x>=W || x<0 || y>=H || y<0 || check[x][y][moveCnt] || array[x][y]==1) continue;
            check[x][y][moveCnt] = true;
            queue.add(new V(x,y,time,moveCnt));
        }
    }
}

class V{
    int x;
    int y;
    int time;
    int moveCnt;
    public V(int x,int y,int time,int moveCnt){
        this.x=x;
        this.y=y;
        this.time=time;
        this.moveCnt=moveCnt;
    }
}