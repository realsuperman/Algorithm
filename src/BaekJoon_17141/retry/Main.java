package BaekJoon_17141.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,virusCnt;
    static int[][] array;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][N];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                array[i][j] = Integer.parseInt(str[j]);
                if(array[i][j]==2) virusCnt++;
            }
        }
        dfs(0,0,new ArrayList<>());
        System.out.println(MIN==Integer.MAX_VALUE?-1:MIN);
    }

    public static void dfs(int start,int depth,List<Integer> list){
        if(depth == M){
            solution(list);
            return;
        }

        for(int i=start+1;i<=virusCnt;i++){
            list.add(i);
            int size = list.size()-1;
            dfs(i,depth+1,list);
            list.remove(size);
        }
    }

    public static void solution(List<Integer> list){
        int[][] temp = new int[N][N];
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) temp[i][j]=array[i][j];

        for(int v : list){
            int count = 0;
            for(int i=0;i<N;i++){
                boolean chk = false;
                for(int j=0;j<N;j++){
                    if(array[i][j]==2) count++;
                    if(count==v){temp[i][j]=-1; chk = true; break;}
                }
                if(chk) break;
            }
        }

        for(int i=0;i<N;i++){ for(int j=0;j<N;j++){ if(temp[i][j]==2) temp[i][j]=0;}}
        for(int i=0;i<N;i++){ for(int j=0;j<N;j++){ if(temp[i][j]==-1) temp[i][j]=2;}}
        bfs(temp);
    }

    public static void bfs(int[][] arr){
        Queue<V> queue = new PriorityQueue<>();
        boolean[][] check = new boolean[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(arr[i][j]==2){ queue.add(new V(i,j,1)); check[i][j]=true;}
            }
        }

        int temp = 0;
        if(isContamination(arr)){
            MIN = temp;
            return;
        }
        while(!queue.isEmpty()){
            V v = queue.remove();
            temp = v.time;
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=N || x<0 || y>=N || y<0 || check[x][y] || arr[x][y]!=0) continue;
                check[x][y]=true;
                queue.add(new V(x,y,v.time+1));
                arr[x][y]=2;
            }
            if(isContamination(arr)){
                if(MIN>temp) MIN = temp;
                break;
            }
        }

    }

    public static boolean isContamination(int[][] arr){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(arr[i][j]==0) return false;
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