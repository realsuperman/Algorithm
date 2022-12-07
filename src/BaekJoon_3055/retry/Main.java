package BaekJoon_3055.retry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main{
    static int N,M;
    static char[][] array;
    static boolean[][] waterCheck,check;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new char[N][M];
        waterCheck = new boolean[N][M];
        check = new boolean[N][M];

        int startX = 0;
        int startY = 0;
        int destX = 0;
        int destY = 0;

        Queue<V> queue = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<M;j++){
                array[i][j]=str[j].charAt(0);
                if(array[i][j]=='S') { startX = i; startY= j; check[i][j]=true;}
                else if(array[i][j]=='D') { destX = i; destY= j;}
                else if(array[i][j]=='*'){ queue.add(new V(i,j,0)); waterCheck[i][j]=true;}
            }
        }

        int v = bfs(startX, startY, destX, destY,queue);
        System.out.println(v==0?"KAKTUS":v);
    }

    public static void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static int bfs(int startX, int startY, int destX, int destY, Queue<V> water){
        Queue<V> move = new PriorityQueue<>();
        move.add(new V(startX,startY,0));

        int cnt = 0;
        while(move.size()>0){
            while (!water.isEmpty()) {
                V v = water.remove();
                if (v.time > cnt) {
                    water.add(v);
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int x = dx[i] + v.x;
                    int y = dy[i] + v.y;
                    if(x<0 || x>=N || y<0 || y>=M || waterCheck[x][y] || array[x][y]!='.' ) continue;
                    waterCheck[x][y]=true;
                    array[x][y]='*';
                    water.add(new V(x,y,v.time+1));
                }
            }


            while (!move.isEmpty()) {
                V v = move.remove();
                if(v.x == destX && v.y == destY) return v.time;
                if(v.time>cnt){move.add(v); break;}

                for (int i = 0; i < 4; i++) {
                    int x = dx[i] + v.x;
                    int y = dy[i] + v.y;
                    if(x<0 || x>=N || y<0 || y>=M || check[x][y] || array[x][y]=='*' || array[x][y]=='X' ) continue;
                    check[x][y]=true;
                    move.add(new V(x,y,v.time+1));
                }
            }
            cnt++;
        }

        return 0;
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
    public int compareTo(V o) {
        return this.time-o.time;
    }
}