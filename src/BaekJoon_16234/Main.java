package BaekJoon_16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static int N;
    public static boolean[][] chk;
    public static int MIN,MAX;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        int[][] array = new int[N][N];
        MIN = Integer.parseInt(str[1]);
        MAX = Integer.parseInt(str[2]);

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j]= Integer.parseInt(str[j]);
            }
        }

        int cnt = 0;
        while(true) {
            chk = new boolean[N][N];
            int[][] copy = new int[N][N];
            for(int i=0;i<N;i++) for(int j=0;j<N;j++) copy[i][j]=array[i][j];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(!chk[i][j]) bfs(i,j,array);
                }
            }
            if(compareArray(copy,array)) break;
            cnt++;
        }
        System.out.println(cnt);
    }

    public static void bfs(int x,int y,int[][] array){
        chk[x][y]=true;
        Queue<V> queue = new LinkedList<>();
        Queue<V> save = new LinkedList<>();

        int sum = 0;

        queue.add(new V(x,y));
        save.add(new V(x,y));
        while(!queue.isEmpty()){
            V v = queue.remove();
            sum+=array[v.x][v.y];
            if(v.x-1>=0 && !chk[v.x-1][v.y] && compareRange(array[v.x-1][v.y],array[v.x][v.y])){
                queue.add(new V(v.x-1,v.y));
                save.add(new V(v.x-1,v.y));
                chk[v.x-1][v.y]=true;
            }
            if(v.x+1<N && !chk[v.x+1][v.y] && compareRange(array[v.x+1][v.y],array[v.x][v.y])){
                queue.add(new V(v.x+1,v.y));
                save.add(new V(v.x+1,v.y));
                chk[v.x+1][v.y]=true;
            }
            if(v.y-1>=0 && !chk[v.x][v.y-1] && compareRange(array[v.x][v.y-1],array[v.x][v.y])){
                queue.add(new V(v.x,v.y-1));
                save.add(new V(v.x,v.y-1));
                chk[v.x][v.y-1]=true;
            }
            if(v.y+1<N && !chk[v.x][v.y+1] && compareRange(array[v.x][v.y+1],array[v.x][v.y])){
                queue.add(new V(v.x,v.y+1));
                save.add(new V(v.x,v.y+1));
                chk[v.x][v.y+1]=true;
            }
        }
        if(save.size()>0) sum = sum/save.size();
        while(!save.isEmpty()){
            V v = save.remove();
            array[v.x][v.y] = sum;
        }

    }

    public static boolean compareArray(int[][] fromArray,int[][] toArray){
        for(int i=0;i<fromArray.length;i++){
            for(int j=0;j<fromArray[i].length;j++){
                if(fromArray[i][j]!=toArray[i][j]) return false;
            }
        }
        return true;
    }

    public static boolean compareRange(int v1,int v2){
        int v = Math.abs(v1-v2);
        if(v>=MIN && v<=MAX) return true;
        return false;
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