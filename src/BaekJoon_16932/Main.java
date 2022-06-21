package BaekJoon_16932;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int N,M,MAX=Integer.MIN_VALUE;
    static int[][] array;
    static int[][] temp;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        temp = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                array[i][j]=Integer.parseInt(str[j]);
            }
        }

        boolean[][] check = new boolean[N][M];
        int group = 1;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!check[i][j] && array[i][j]==1){
                    bfs(i,j,check,group++);
                }
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==0){
                    array[i][j]=1;
                    Set<Integer> set = new HashSet<>();
                    for(int k=0;k<4;k++){
                        int solutionX = i+dx[k];
                        int solutionY = j+dy[k];
                        if(solutionX<0 || solutionX>=N || solutionY<0 || solutionY>=M) continue;
                        if(temp[solutionX][solutionY]>=1) set.add(temp[solutionX][solutionY]);
                    }
                    int sum =1;
                    for(int value : set) sum+=list.get(value-1);
                    if(sum>MAX) MAX=sum;
                    array[i][j]=0;
                }
            }
        }
        System.out.println(MAX);
    }
    public static void bfs(int x,int y,boolean[][] check,int group){
        int sum = 1;
        check[x][y]=true;
        temp[x][y]=group;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(x,y));
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int solutionX = v.x+dx[i];
                int solutionY = v.y+dy[i];
                if(solutionX<0 || solutionX>=N || solutionY<0 || solutionY>=M) continue;
                if(!check[solutionX][solutionY] && array[solutionX][solutionY]==1){
                    sum++;
                    temp[solutionX][solutionY]=group;
                    queue.add(new V(solutionX,solutionY));
                    check[solutionX][solutionY]=true;
                }
            }
        }
        list.add(sum);
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