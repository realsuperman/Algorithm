package BaekJoon_16933;

import java.io.*;
import java.util.*;

public class Main {
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int K = Integer.parseInt(str[2]);
        int[][] array = new int[N][M];
        boolean[][][] check = new boolean[N][M][K+1];

        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }

        Queue<V> queue = new PriorityQueue<>();
        queue.add(new V(0,0,1,0,true));
        for(int i=0;i<K;i++) check[0][0][i]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x==N-1 && v.y==M-1){
                System.out.println(v.value);
                return;
            }

            for(int i=0;i<4;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x<0 || x>=N || y<0 || y>=M) continue;
                if(array[x][y]==0 && !check[x][y][v.crash]){
                    if(!check[x][y][v.crash]){
                        check[x][y][v.crash] = true;
                        queue.add(new V(x,y,v.value+1,v.crash,!v.sunYn));
                    }
                }else if(array[x][y]==1 && v.crash+1<=K && !check[x][y][v.crash+1]){
                    if(v.sunYn){
                        check[x][y][v.crash+1] = true;
                        queue.add(new V(x,y,v.value+1,v.crash+1,!v.sunYn));
                    }else{
                        check[x][y][v.crash+1] = true;
                        queue.add(new V(x,y,v.value+2,v.crash+1,v.sunYn));
                    }
                }
            }

        }
        System.out.println(-1);
    }
}
class V implements Comparable<V>{
    int x;
    int y;
    int value;
    int crash;
    boolean sunYn;
    public V(int x,int y,int value,int crash,boolean sunYn){
        this.x=x;
        this.y=y;
        this.value=value;
        this.crash=crash;
        this.sunYn=sunYn;
    }
    @Override
    public int compareTo(V o){
        if(this.value==o.value){
            return this.crash-o.crash;
        }else{
            return this.value-o.value;
        }
    }
}