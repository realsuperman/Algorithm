package BaekJoon_16988.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] array;
    static Map<Integer,Integer> map = new HashMap<>();
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int MAX = Integer.MIN_VALUE;
    static int groupMaxCnt = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array  = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        bfs(); // 그룹 갯수 확인
        solution(0,0,new ArrayList<>());
        System.out.println(MAX==Integer.MIN_VALUE?0:MAX);
    }

    public static void solution(int value, int depth, List<Integer> list){
        if(depth==2){
            int[][] temp = new int[N][M];
            for(int i=0;i<N;i++) for(int j=0;j<M;j++) temp[i][j]=array[i][j];

            for(int v : list){
                int x = v/M;
                int y = v%M;
                array[x][y]=1;
            }

            int v = 0;
            for(int i=3;i<=groupMaxCnt;i++){
                if(find(i)) v+=map.get(i);
            }

            array = temp;
            if(v>0 && MAX<v) MAX=v;
            return;
        }

        for(int i=value;i<N*M;i++) {
            int x = i / M;
            int y = i % M;
            if (array[x][y] != 0) continue;
            list.add(i);
            int size = list.size() - 1;
            solution(i + 1, depth + 1, list);
            list.remove(size);
        }
    }

    public static boolean find(int startNumber){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==startNumber){
                    for(int k=0;k<4;k++){
                        int x = dx[k]+i;
                        int y = dy[k]+j;
                        if(x<0 || x>=N || y<0 || y>=M) continue;
                        if(array[x][y]==0) return false;
                    }
                }
            }
        }
        return true;
    }

    public static void bfs(){
        Queue<V> queue = new LinkedList<>();
        boolean[][] check = new boolean[N][M];
        int[][] array2 = new int[N][M];
        for(int i=0;i<N;i++) for(int j=0;j<M;j++) array2[i][j]=array[i][j];

        int groupNumber = 3;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==2 && !check[i][j]){
                    check[i][j]=true;
                    queue.add(new V(i,j,-1));
                    array2[i][j]=groupNumber;
                    int groupCnt = 1;
                    while(!queue.isEmpty()){
                        V v = queue.remove();
                        for(int k=0;k<4;k++){
                            int x = dx[k]+v.x;
                            int y = dy[k]+v.y;
                            if(x<0 || x>=N || y<0 || y>=M || check[x][y] || array[x][y]!=2) continue;
                            check[x][y]=true;
                            queue.add(new V(x,y,-1));
                            array2[x][y]=groupNumber;
                            groupCnt++;
                        }
                    }
                    groupMaxCnt = groupNumber;
                    map.put(groupNumber++,groupCnt);
                }
            }
        }
        array = array2;
    }

}

class V{
    int x;
    int y;
    int group;

    public V(int x,int y,int group){
        this.x=x;
        this.y=y;
        this.group=group;
    }
}