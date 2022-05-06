package BaekJoon_1261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int[][] array = new int[M][N];
        boolean[][] check = new boolean[M][N];

        for(int i=0;i<M;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }
        PriorityQueue<V> queue = new PriorityQueue<>();
        queue.add(new V(0,0,0));
        check[0][0]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x==M-1 && v.y==N-1){
                System.out.println(v.breakCnt);
                return;
            }
            if(v.x+1<M && !check[v.x+1][v.y]){
                check[v.x+1][v.y] = true;
                queue.add(new V(v.x+1,v.y,v.breakCnt+array[v.x+1][v.y]));
            }
            if(v.y+1<N && !check[v.x][v.y+1]){
                check[v.x][v.y+1] = true;
                queue.add(new V(v.x,v.y+1,v.breakCnt+array[v.x][v.y+1]));
            }
            if(v.x-1>=0 && !check[v.x-1][v.y]){
                check[v.x-1][v.y] = true;
                queue.add(new V(v.x-1,v.y,v.breakCnt+array[v.x-1][v.y]));
            }
            if(v.y-1>=0 && !check[v.x][v.y-1]){
                check[v.x][v.y-1] = true;
                queue.add(new V(v.x,v.y-1,v.breakCnt+array[v.x][v.y-1]));
            }

        }

    }
}

class V implements Comparable<V>{
    int x;
    int y;
    int breakCnt;
    public V(int x,int y,int breakCnt){
        this.x=x;
        this.y=y;
        this.breakCnt=breakCnt;
    }

    @Override
    public int compareTo(V o) {
        return this.breakCnt-o.breakCnt;
    }
}