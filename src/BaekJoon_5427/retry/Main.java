package BaekJoon_5427.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static int N,M;
    static char[][] array;
    static boolean[][] check;
    static boolean[][] fireCheck;
    static Queue<V> fire;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            String[] str = br.readLine().split(" ");
            N = Integer.parseInt(str[1]);
            M = Integer.parseInt(str[0]);
            array = new char[N][M];
            check = new boolean[N][M];
            fireCheck = new boolean[N][M];
            fire = new PriorityQueue<>();

            int startX=0;
            int startY=0;
            for(int i=0;i<N;i++){
                str = br.readLine().split("");
                for(int j=0;j<M;j++){
                    array[i][j]=str[j].charAt(0);
                    if(array[i][j]=='@') {startX=i; startY=j; array[i][j]='.';}
                    else if(array[i][j]=='*') fire.add(new V(i,j,0));
                }
            }

            int v = bfs(startX,startY);
            System.out.println(v==0?"IMPOSSIBLE":v);
        }
    }

    public static int bfs(int startX,int startY){
        int result = 0;
        Queue<V> queue = new PriorityQueue<>();
        queue.add(new V(startX,startY,0));

        int loop = 0;
        while(!queue.isEmpty()){

            while(!fire.isEmpty()){
                V v = fire.remove();
                if(v.time>loop) {fire.add(v); break;}
                for(int i=0;i<4;i++){
                    int x = dx[i]+v.x;
                    int y = dy[i]+v.y;
                    if(x<0 || x>=N || y<0 || y>=M || fireCheck[x][y] || array[x][y]!='.') continue;
                    fireCheck[x][y]=true;
                    array[x][y]='@';
                    fire.add(new V(x,y,v.time+1));
                }
            }
            while(!queue.isEmpty()){
                V v = queue.remove();
                if(v.time>loop) {queue.add(v); break;}

                for(int i=0;i<4;i++){
                    int x = dx[i]+v.x;
                    int y = dy[i]+v.y;
                    if(x>=N || x<0 || y>=M || y<0) return v.time+1;

                    if(x<0 || x>=N || y<0 || y>=M || check[x][y] || array[x][y]!='.') continue;
                    check[x][y]=true;
                    queue.add(new V(x,y,v.time+1));
                }
            }

            loop++;
        }
        return result;
    }
}

class V implements Comparable<V> {
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