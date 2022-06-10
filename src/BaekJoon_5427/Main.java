package BaekJoon_5427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for(int i=0;i<TC;i++) {
            String[] str = br.readLine().split(" ");
            int N = Integer.parseInt(str[1]);
            int M = Integer.parseInt(str[0]);
            String[][] array = new String[N][M];
            for(int j=0;j<N;j++) {
                str = br.readLine().split("");
                for(int k=0;k<str.length;k++) array[j][k]=str[k];
            }
            bfs(array,N,M);
        }
    }

    public static void bfs(String[][] array,int N,int M){
        Queue<V> move = new PriorityQueue<>();
        Queue<V> fire = new PriorityQueue<>();

        boolean[][] moveCheck = new boolean[N][M];
        boolean[][] fireCheck = new boolean[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j].equals("@")){
                    move.add(new V(i,j,0));
                    moveCheck[i][j]=true;
                }
                if(array[i][j].equals("*")){
                    fire.add(new V(i,j,0));
                    fireCheck[i][j]=true;
                }
            }
        }
        while(true){
            int size = fire.size();
            int value = 0;
            while(!fire.isEmpty()){
                V v = fire.remove();
                if(v.x+1<N && !fireCheck[v.x+1][v.y] && !array[v.x+1][v.y].equals("#")){
                    array[v.x+1][v.y]="*";
                    fire.add(new V(v.x+1,v.y,v.time+1));
                    fireCheck[v.x+1][v.y]=true;
                }
                if(v.x-1>=0 && !fireCheck[v.x-1][v.y] && !array[v.x-1][v.y].equals("#")){
                    array[v.x-1][v.y]="*";
                    fire.add(new V(v.x-1,v.y,v.time+1));
                    fireCheck[v.x-1][v.y]=true;
                }
                if(v.y+1<M && !fireCheck[v.x][v.y+1] && !array[v.x][v.y+1].equals("#")){
                    array[v.x][v.y+1]="*";
                    fire.add(new V(v.x,v.y+1,v.time+1));
                    fireCheck[v.x][v.y+1]=true;
                }
                if(v.y-1>=0 && !fireCheck[v.x][v.y-1] && !array[v.x][v.y-1].equals("#")){
                    array[v.x][v.y-1]="*";
                    fire.add(new V(v.x,v.y-1,v.time+1));
                    fireCheck[v.x][v.y-1]=true;
                }
                if(++value==size) break;
            }

            size = move.size();
            value = 0;
            while(!move.isEmpty()){
                V v = move.remove();
                if(v.x-1<0 || v.y-1<0 ||v.x+1>=N || v.y+1>=M){System.out.println(v.time+1);return;}

                if(!moveCheck[v.x+1][v.y] && array[v.x+1][v.y].equals(".")){
                    move.add(new V(v.x+1,v.y,v.time+1));
                    moveCheck[v.x+1][v.y]=true;
                }
                if(!moveCheck[v.x-1][v.y] && array[v.x-1][v.y].equals(".")){
                    move.add(new V(v.x-1,v.y,v.time+1));
                    moveCheck[v.x-1][v.y]=true;
                }
                if(!moveCheck[v.x][v.y+1] && array[v.x][v.y+1].equals(".")){
                    move.add(new V(v.x,v.y+1,v.time+1));
                    moveCheck[v.x][v.y+1]=true;
                }
                if(!moveCheck[v.x][v.y-1] && array[v.x][v.y-1].equals(".")){
                    move.add(new V(v.x,v.y-1,v.time+1));
                    moveCheck[v.x][v.y-1]=true;
                }
                if(++value==size) break;
            }
            while(move.size()==0 && fire.size()==0) {System.out.println("IMPOSSIBLE"); return;}
        }
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
