package BaekJoon_2636.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int N,M;
    static boolean[][] array;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new boolean[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++) array[i][j] = Integer.parseInt(str[j])==1?true:false;
        }

        int days = 0;
        int cheeseCnt = countCheese();
        while(checkArray()){
            cheeseCnt = countCheese();
            simulation();
            days++;
        }

        System.out.println(days);
        System.out.println(cheeseCnt);
    }

    public static void simulation(){
        Queue<V> queue = new LinkedList<>();
        List<V> banList = new ArrayList<>();

        boolean[][] check = new boolean[N][M];
        queue.add(new V(0,0));
        check[0][0]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x<0 || x>=N || y<0 || y>=M || check[x][y]) continue;

                if(array[x][y]) banList.add(new V(x,y));
                else queue.add(new V(x,y));
                check[x][y]=true;
            }
        }

        for(V v : banList) array[v.x][v.y]=false;
    }

    public static boolean checkArray(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]) return true;
            }
        }
        return false;
    }

    public static int countCheese(){
        int v = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]) v++;
            }
        }
        return v;
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