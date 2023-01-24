package BaekJoon_17135.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static int N,M,D;
    static int[][] array;
    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        D = Integer.parseInt(str[2]);
        array = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        dfs(0,0,new ArrayList<>());
        System.out.println(MAX);
    }

    public static void dfs(int start, int depth, List<Integer> list){
        if(depth==3){
            simulation(list);
            return;
        }

        for(int i=start;i<M;i++){
            list.add(i);
            int size = list.size()-1;
            dfs(i+1,depth+1,list);
            list.remove(size);
        }
    }

    private static void simulation(List<Integer> list) {
        int cnt = 0; // 제거수
        int[][] temp = new int[N][M];
        for(int i=0;i<N;i++) for(int j=0;j<M;j++) temp[i][j]=array[i][j];

        // x -> N, y-> list값
        while(isEnd(temp)){
            List<V> target = new ArrayList<>();

            for(int v : list) target.add(attack(N,v,temp));
            cnt+=target.size();
            for(V v : target) temp[v.x][v.y]=0;
            down(temp);

        }

        MAX = MAX<cnt?cnt:MAX;
    }

    private static boolean isEnd(int[][] array){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]!=0) return true;
            }
        }
        return false;
    }

    private static void down(int[][] array){
        for(int i=N-1;i>0;i--){
            for(int j=0;j<M;j++){
                array[i][j] = array[i-1][j];
            }
        }
        for(int index=0;index<M;index++) array[0][index]=0;
    }

    private static V attack(int x, int y, int[][] array){
        Queue<V> queue = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==1){
                    int dist = Math.abs(i-x)+Math.abs(y-j);
                    if(dist<=D) queue.add(new V(i,j,dist));
                }
            }
        }
        return queue.peek();
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
        if(this.time==o.time){
            return this.y-o.y;
        }
        return this.time-o.time;
    }
}