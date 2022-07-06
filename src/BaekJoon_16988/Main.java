package BaekJoon_16988;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int N,M,result;
    static int[][] array;
    static boolean[] check;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        check = new boolean[N*M];
        for(int i=0;i<N;i++){
            str=br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        List<V> list = new ArrayList<>();
        solution(0,0,list);
        System.out.println(result);
    }

    public static void solution(int start,int seat,List<V> list){
        if(seat==2){
            int[][] temp = new int[N][M];
            for(int i=0;i<N;i++) for(int j=0;j<M;j++) temp[i][j]=array[i][j];

            for(V v : list) temp[v.x][v.y]=1;
            int cnt = bfs(temp);
            result = result<cnt?cnt:result;
            return;
        }
        for(int i=start;i<N*M;i++){
            int x = i/M;
            int y = i%M;

            if(check[i] || array[x][y]!=0) continue;
            check[i]=true;
            list.add(new V(x,y));
            int size = list.size();
            solution(i,seat+1,list);
            list.remove(size-1);
            check[i]=false;
        }
    }

    public static int bfs(int[][] array){
        boolean[][] chk = new boolean[N][M];
        int returnValue = 0;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==2 && !chk[i][j]){
                    Queue<V> queue = new LinkedList<>();
                    boolean sw = false;
                    int cnt = 1;
                    queue.add(new V(i,j));
                    chk[i][j]=true;

                    while(!queue.isEmpty()){
                        V v = queue.remove();
                        for(int index=0;index<4;index++){
                            int x = v.x+dx[index];
                            int y = v.y+dy[index];
                            if(x<0 || x>=N || y<0 || y>=M) continue;
                            if(!sw && array[x][y]==0){ sw = true; continue;}

                            if(!chk[x][y] && array[x][y]==2){
                                cnt++;
                                chk[x][y]=true;
                                queue.add(new V(x,y));
                            }
                        }
                    }
                    if(!sw) returnValue+=cnt;
                }
            }
        }
        return returnValue;
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