package BaekJoon_14502.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Main {
    static int[][] array;
    static int cnt,N,M;
    static List<Integer> list;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        list = new ArrayList<>();

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                array[i][j] = Integer.parseInt(str[j]);
                if(array[i][j]==0) cnt++;
            }
        }
        solution(0,0);
        System.out.println(MAX);
    }

    public static void solution(int start,int depth){
        if(depth==3){
            simulation();
            return;
        }
        for(int i=start;i<cnt;i++){
            list.add(i);
            int size = list.size();
            solution(i+1,depth+1);
            list.remove(size-1);
        }
    }

    public static void simulation(){
        int[][] temp = new int[N][M];
        for(int i=0;i<N;i++) for(int j=0;j<M;j++) temp[i][j]=array[i][j];

        for(int v : list){
            int value = -1;
            boolean chk = false;
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(array[i][j]==0) value++;
                    if(value == v){ temp[i][j]=1; chk = true; break;}
                }
                if(chk) break;
            }
        }

        Queue<V> queue = new LinkedList<>();
        boolean[][] check = new boolean[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(temp[i][j]==2){ queue.add(new V(i,j)); check[i][j]=true;}
            }
        }

        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0 || x>=N || y<0 || y>=M || check[x][y] || temp[x][y]!=0) continue;
                check[x][y]=true;
                temp[x][y]=2;
                queue.add(new V(x,y));
            }
        }

        int cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(temp[i][j]==0) cnt++;
            }
        }

        MAX = MAX<cnt?cnt:MAX;
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