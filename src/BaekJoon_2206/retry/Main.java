package BaekJoon_2206.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M;
    static int[][] array;
    static boolean[][][] check;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        check = new boolean[N][M][2];
        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        int returnValue = -1;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(0,0,0,false));
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x == N-1 && v.y == M-1){
                returnValue = v.time+1;
                break;
            }

            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0 || x>=N || y<0 || y>=M) continue;
                if(v.isBreak){ // 벽부숨
                    if(check[x][y][0] || array[x][y]==1) continue;
                    check[x][y][0]=true;
                    queue.add(new V(x,y,v.time+1,true));
                }else{ // 벽안부숨
                    if(check[x][y][1]) continue;
                    if(array[x][y]==1){
                        check[x][y][0]=true;
                        queue.add(new V(x,y,v.time+1,true));
                    }else{
                        check[x][y][1]=true;
                        queue.add(new V(x,y,v.time+1,false));
                    }
                }
            }
        }
        return returnValue;
    }
}

class V{
    int x;
    int y;
    int time;
    boolean isBreak;
    public V(int x,int y,int time,boolean isBreak){
        this.x=x;
        this.y=y;
        this.time=time;
        this.isBreak=isBreak;
    }
}