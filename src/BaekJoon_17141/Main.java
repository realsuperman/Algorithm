package BaekJoon_17141;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int MIN = -1;
    public static List<Integer> list = new ArrayList<>();
    public static int mCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int[][] array = new int[N][N];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j] = Integer.parseInt(str[j]);
                if(array[i][j]==2) mCnt++;
            }
        }

        solution(array,M,N,0,0);
        System.out.println(MIN);
    }

    public static void solution(int[][] array,int depth,int N,int M,int start){
        if(M==depth){
            int[][] temp = new int[N][N];
            init(temp,array);
            int v = bfs(temp,N);
            if((v!=-1 && MIN == -1) || (v!=-1 && MIN!=-1 && MIN>v)) MIN = v;
            return;
        }
        for(int i=start;i<mCnt;i++){
            list.add(i);
            int index = list.size()-1;
            solution(array,depth,N,M+1,i+1);
            list.remove(index);
        }
    }

    public static int bfs(int[][] temp,int N){
        boolean[][] check = new boolean[N][N];
        Queue<V> queue = new LinkedList<>();
        for(int i=0;i<temp.length;i++){
            for(int j=0;j<temp[i].length;j++){
                if(temp[i][j]==2){
                    queue.add(new V(i,j,0));
                    check[i][j]=true;
                }
            }
        }

        int cnt = 0;
        while(!queue.isEmpty()){
            V v = queue.remove();
            cnt = v.time;
            if(v.x-1>=0 && !check[v.x-1][v.y] && temp[v.x-1][v.y]==0){
                check[v.x-1][v.y]=true;
                temp[v.x-1][v.y]=2;
                queue.add(new V(v.x-1,v.y,v.time+1));
            }
            if(v.x+1<N && !check[v.x+1][v.y] && temp[v.x+1][v.y]==0){
                check[v.x+1][v.y]=true;
                temp[v.x+1][v.y]=2;
                queue.add(new V(v.x+1,v.y,v.time+1));
            }
            if(v.y+1<N && !check[v.x][v.y+1] && temp[v.x][v.y+1]==0){
                check[v.x][v.y+1]=true;
                temp[v.x][v.y+1]=2;
                queue.add(new V(v.x,v.y+1,v.time+1));
            }
            if(v.y-1>=0 && !check[v.x][v.y-1] && temp[v.x][v.y-1]==0){
                check[v.x][v.y-1]=true;
                temp[v.x][v.y-1]=2;
                queue.add(new V(v.x,v.y-1,v.time+1));
            }
        }
        if(checkMethod(temp)) return cnt;
        return -1;
    }

    public static boolean checkMethod(int[][] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                if(array[i][j]==0) return false;
            }
        }
        return true;
    }

    public static void init(int[][] temp,int[][] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                temp[i][j] = array[i][j];
            }
        }


        int cnt = 0;
        int index = 0;
        for(int i=0;i<temp.length;i++){
            for(int j=0;j<temp[i].length;j++){
                if(index>=list.size()) break;

                if(temp[i][j] == 2) cnt++;
                if(cnt==(list.get(index))+1){
                    temp[i][j]=-1;
                    index++;
                }
            }
        }


        for(int i=0;i<temp.length;i++){
            for(int j=0;j<temp[i].length;j++){
                if(temp[i][j]==2) temp[i][j]=0;
            }
        }

        for(int i=0;i<temp.length;i++){
            for(int j=0;j<temp[i].length;j++){
                if(temp[i][j]==-1) temp[i][j]=2;
            }
        }

    }
}

class V{
    int x;
    int y;
    int time;
    public V(int x,int y,int time){
        this.x=x;
        this.y=y;
        this.time=time;
    }
}