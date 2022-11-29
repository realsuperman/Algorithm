package BaekJoon_2667.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int[][] array;
    static boolean[][] check;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int cnt = 0;
        array = new int[N][N];
        check = new boolean[N][N];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<N;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }

        List<Integer> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]==1 && !check[i][j]){ check[i][j]=true; list.add(bfs(i,j)); cnt++;}
            }
        }
        Collections.sort(list);
        System.out.println(cnt);
        for(int v : list) System.out.println(v);
    }

    public static int bfs(int startX,int startY){
        int cnt = 1;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x>=N || x<0 || y>=N || y<0 || check[x][y] || array[x][y]==0) continue;
                check[x][y]=true;
                queue.add(new V(x,y));
                cnt++;
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