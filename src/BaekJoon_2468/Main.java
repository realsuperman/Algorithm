package BaekJoon_2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int N;
    static int[][] array;
    static int MAX = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        for(int i=1;i<=100;i++) bfs(i);

        System.out.println(MAX);
    }
    public static void bfs(int depth){
        int[][] temp = new int[N][N];
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) temp[i][j]=array[i][j];
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) if(array[i][j]<=depth) array[i][j]=-1;

        boolean[][] check = new boolean[N][N];
        int sum=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(temp[i][j]!=-1 && !check[i][j]){
                    sum++;
                    Queue<V> queue = new LinkedList<>();
                    queue.add(new V(i,j));
                    while(!queue.isEmpty()){
                        V v = queue.remove();
                        for(int k=0;k<4;k++){
                            int x = v.x+dx[k];
                            int y = v.y+dy[k];
                            if(x>=N || x<0 || y>=N || y<0) continue;
                            if(!check[x][y] && temp[x][y]!=-1){
                                check[x][y]=true;
                                queue.add(new V(x,y));
                            }
                        }
                    }
                }
            }
        }
        if(MAX<sum) MAX=sum;
    }
}

class V{
    int x;
    int y;
    public V(int x,int y){
        this.x=x;
        this.y=y;
    }
}