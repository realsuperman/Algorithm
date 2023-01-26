package BaekJoon_17142.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int MAX = Integer.MAX_VALUE; // 최소 시간
    static int N,M;
    static int[][] array;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][N];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<N;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        dfs(0,0,new ArrayList<>());
        System.out.println(MAX==Integer.MAX_VALUE?-1:MAX);
    }

    public static void dfs(int start,int depth,List<Integer> list){
        if(depth==M){
            List<String> locationLists = new ArrayList<>();

            for(int v : list){
                int x = v/N;
                int y = v%N;
                locationLists.add(x+","+y);
            }
            solution(locationLists);
            return;
        }

        for(int i=start;i<N*N;i++){
            int x = i/N;
            int y = i%N;
            if(array[x][y]!=2) continue;
            list.add(i);
            int size = list.size()-1;
            dfs(i+1,depth+1,list);
            list.remove(size);
        }
    }

    public static void solution(List<String> locationLists){
        int time = 0;

        int[][] temp = new int[N][N];
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) temp[i][j]=array[i][j];

        boolean[][] check = new boolean[N][N];
        Queue<V> queue = new PriorityQueue<>();

        for(String s : locationLists){
            String[] str = s.split(",");
            check[Integer.parseInt(str[0])][Integer.parseInt(str[1])]=true;
            queue.add(new V(Integer.parseInt(str[0]),Integer.parseInt(str[1]),1));
        }

        if(isEnd(temp)){ MAX = 0; return;}
        while(!queue.isEmpty()){
            V v = queue.remove();
            time = v.time;

            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0 || x>=N || y<0 || y>=N || check[x][y] || array[x][y]==1) continue;
                check[x][y]=true;
                temp[x][y]=2;
                queue.add(new V(x,y,v.time+1));
            }
            if(isEnd(temp)){
                MAX = MAX>time ? time : MAX;
                return;
            }
        }

        if(isEnd(temp) && MAX>time ) MAX = time;
    }

    public static boolean isEnd(int[][] array){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]==0) return false;
            }
        }
        return true;
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