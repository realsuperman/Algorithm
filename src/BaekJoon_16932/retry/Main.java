package BaekJoon_16932.retry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] array;
    static int[][] array2;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static Map<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        array2 = new int[N][M];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        boolean[][] check = new boolean[N][M];
        int cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==1&&!check[i][j]){
                    int v = bfs(i,j,++cnt,check);
                    map.put(cnt,v);
                }
            }
        }

        int MAX = Integer.MIN_VALUE;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==0){
                    int v = 1;
                    Set<Integer> set = new HashSet<>();
                    for(int k=0;k<4;k++){
                        int x = dx[k]+i;
                        int y = dy[k]+j;
                        if(x>=N || x<0 || y>=M || y<0 || array[x][y]==0) continue;
                        set.add(array2[x][y]);
                    }
                    for(int s : set) v+=map.get(s);
                    MAX = MAX<v?v:MAX;
                }
            }
        }
        System.out.println(MAX);
    }

    private static int bfs(int x, int y, int value,boolean[][] check) {
        int cnt = 1;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(x,y));
        check[x][y]=true;
        array2[x][y]=value;
        int compareValue = array[x][y];

        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int varX = dx[i]+v.x;
                int varY = dy[i]+v.y;
                if(varX>=N || varX<0 || varY>=M || varY<0 || check[varX][varY] || array[varX][varY]!=compareValue) continue;
                cnt++;
                array2[varX][varY]=value;
                check[varX][varY]=true;
                queue.add(new V(varX,varY));
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