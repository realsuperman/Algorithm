package BaekJoon_1600;

import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[1]);
        int M = Integer.parseInt(str[0]);
        int[][] array = new int[N+1][M+1];
        boolean[][][] check = new boolean[K+1][N+1][M+1];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i+1][j+1]=Integer.parseInt(str[j]);
            }
        }

        Queue<V> queue = new LinkedList<>();
        queue.add(new V(0,1,1,0));
        for(int i=0;i<K;i++) check[i][1][1]=true;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x == N && v.y==M){
                System.out.println(v.time);
                return;
            }
            if(v.cnt==K){
                if(v.x+1<=N && !check[v.cnt][v.x+1][v.y] && array[v.x+1][v.y]==0){
                    check[v.cnt][v.x+1][v.y]=true;
                    queue.add(new V(v.cnt,v.x+1,v.y,v.time+1));
                }
                if(v.x-1>=1 && !check[v.cnt][v.x-1][v.y] && array[v.x-1][v.y]==0){
                    check[v.cnt][v.x-1][v.y]=true;
                    queue.add(new V(v.cnt,v.x-1,v.y,v.time+1));
                }
                if(v.y+1<=M && !check[v.cnt][v.x][v.y+1] && array[v.x][v.y+1]==0){
                    check[v.cnt][v.x][v.y+1]=true;
                    queue.add(new V(v.cnt,v.x,v.y+1,v.time+1));
                }
                if(v.y-1>=1 && !check[v.cnt][v.x][v.y-1] && array[v.x][v.y-1]==0){
                    check[v.cnt][v.x][v.y-1]=true;
                    queue.add(new V(v.cnt,v.x,v.y-1,v.time+1));
                }
            }else if(v.cnt<K){
                if(v.x-2>=1 && v.y-1>=1 && !check[v.cnt+1][v.x-2][v.y-1] && array[v.x-2][v.y-1]==0){
                    check[v.cnt+1][v.x-2][v.y-1]=true;
                    queue.add(new V(v.cnt+1,v.x-2,v.y-1,v.time+1));
                }
                if(v.x-2>=1 && v.y+1<=M && !check[v.cnt+1][v.x-2][v.y+1] && array[v.x-2][v.y+1]==0){
                    check[v.cnt+1][v.x-2][v.y+1]=true;
                    queue.add(new V(v.cnt+1,v.x-2,v.y+1,v.time+1));
                }
                if(v.x-1>=1 && v.y-2>=1 && !check[v.cnt+1][v.x-1][v.y-2] && array[v.x-1][v.y-2]==0){
                    check[v.cnt+1][v.x-1][v.y-2]=true;
                    queue.add(new V(v.cnt+1,v.x-1,v.y-2,v.time+1));
                }
                if(v.x-1>=1 && v.y+2<=M && !check[v.cnt+1][v.x-1][v.y+2] && array[v.x-1][v.y+2]==0){
                    check[v.cnt+1][v.x-1][v.y+2]=true;
                    queue.add(new V(v.cnt+1,v.x-1,v.y+2,v.time+1));
                }
                if(v.x+1<=N && v.y-2>=1 && !check[v.cnt+1][v.x+1][v.y-2] && array[v.x+1][v.y-2]==0){
                    check[v.cnt+1][v.x+1][v.y-2]=true;
                    queue.add(new V(v.cnt+1,v.x+1,v.y-2,v.time+1));
                }
                if(v.x+1<=N && v.y+2<=M && !check[v.cnt+1][v.x+1][v.y+2] && array[v.x+1][v.y+2]==0){
                    check[v.cnt+1][v.x+1][v.y+2]=true;
                    queue.add(new V(v.cnt+1,v.x+1,v.y+2,v.time+1));
                }
                if(v.x+2<=N && v.y-1>=1 && !check[v.cnt+1][v.x+2][v.y-1] && array[v.x+2][v.y-1]==0){
                    check[v.cnt+1][v.x+2][v.y-1]=true;
                    queue.add(new V(v.cnt+1,v.x+2,v.y-1,v.time+1));
                }
                if(v.x+2<=N && v.y+1<=M && !check[v.cnt+1][v.x+2][v.y+1] && array[v.x+2][v.y+1]==0){
                    check[v.cnt+1][v.x+2][v.y+1]=true;
                    queue.add(new V(v.cnt+1,v.x+2,v.y+1,v.time+1));
                }

                if(v.x+1<=N && !check[v.cnt][v.x+1][v.y] && array[v.x+1][v.y]==0){
                    check[v.cnt][v.x+1][v.y]=true;
                    queue.add(new V(v.cnt,v.x+1,v.y,v.time+1));
                }
                if(v.x-1>=1 && !check[v.cnt][v.x-1][v.y] && array[v.x-1][v.y]==0){
                    check[v.cnt][v.x-1][v.y]=true;
                    queue.add(new V(v.cnt,v.x-1,v.y,v.time+1));
                }
                if(v.y+1<=M && !check[v.cnt][v.x][v.y+1] && array[v.x][v.y+1]==0){
                    check[v.cnt][v.x][v.y+1]=true;
                    queue.add(new V(v.cnt,v.x,v.y+1,v.time+1));
                }
                if(v.y-1>=1 && !check[v.cnt][v.x][v.y-1] && array[v.x][v.y-1]==0){
                    check[v.cnt][v.x][v.y-1]=true;
                    queue.add(new V(v.cnt,v.x,v.y-1,v.time+1));
                }
            }
        }
        System.out.println(-1);

    }
}

class V{
    int x;
    int y;
    int cnt;
    int time;
    public V(int cnt,int x,int y,int time){
        this.cnt=cnt;
        this.x=x;
        this.y=y;
        this.time=time;
    }
}