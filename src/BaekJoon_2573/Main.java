package BaekJoon_2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M;
    static int[][] array;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        if(region()<2) bfs();
        System.out.println(result);
    }

    public static void bfs(){
        Queue<V> queue = new LinkedList<>();
        int solution = 0;
        while(check()){
            int[][] copy = new int[N][M];
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    int value = 0;
                    if(array[i][j]>=1){
                        for(int k=0;k<4;k++){
                            int x = dx[k]+i;
                            int y = dy[k]+j;
                            if(x<0 || x>=N || y<0 || y>=M) continue;
                            if(array[x][y]==0) value++;
                        }
                    }
                    copy[i][j] = array[i][j]-value>=0?array[i][j]-value:0;
                }
            }
            solution++;
            for(int i=0;i<N;i++) for(int j=0;j<M;j++) array[i][j]=copy[i][j];
            if(region()>=2){result = solution; break;}
        }
    }

    public static boolean check(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]>=1) return true;
            }
        }
        return false;
    }

    public static int region(){
        boolean[][] check = new boolean[N][M];
        int cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]>=1 && !check[i][j]){
                    Queue<V> queue = new LinkedList<>();
                    queue.add(new V(i,j));
                    while(!queue.isEmpty()){
                        V v = queue.remove();
                        for(int index=0;index<4;index++){
                            int x = dx[index]+v.x;
                            int y = dy[index]+v.y;
                            if(x<0 || x>=N || y<0 || y>=M) continue;
                            if(array[x][y]>=1 && !check[x][y]) {
                                check[x][y] = true;
                                queue.add(new V(x, y));
                            }
                        }
                    }
                    cnt++;
                }
            }
        }
        return cnt;
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