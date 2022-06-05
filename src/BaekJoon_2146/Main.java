package BaekJoon_2146;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static boolean[][] chk;
    static int N;
    static int[][] array;
    static int solution = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        chk = new boolean[N][N];

        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }

        while(true) {
            boolean breakVal = true;

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(array[i][j] == 1 && !chk[i][j]){
                        breakVal = false;
                        simulation(i,j);
                        break;
                    }
                }
            }

            if(breakVal) break;
        }

        System.out.println(solution==Integer.MAX_VALUE?0:solution);
    }

    public static void simulation(int x,int y) {
        chk[x][y] = true;
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) arr[i][j] = array[i][j];

        arr[x][y] = -1;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(x,y,0));

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x+1<N && arr[v.x+1][v.y]==1 && !chk[v.x+1][v.y]){
                chk[v.x+1][v.y]=true;
                arr[v.x+1][v.y]=-1;
                queue.add(new V(v.x+1,v.y,0));
            }
            if(v.x-1>=0 && arr[v.x-1][v.y]==1 && !chk[v.x-1][v.y]){
                chk[v.x-1][v.y]=true;
                arr[v.x-1][v.y]=-1;
                queue.add(new V(v.x-1,v.y,0));
            }
            if(v.y+1<N && arr[v.x][v.y+1]==1 && !chk[v.x][v.y+1]){
                chk[v.x][v.y+1]=true;
                arr[v.x][v.y+1]=-1;
                queue.add(new V(v.x,v.y+1,0));
            }
            if(v.y-1>=0 && arr[v.x][v.y-1]==1 && !chk[v.x][v.y-1]){
                chk[v.x][v.y-1]=true;
                arr[v.x][v.y-1]=-1;
                queue.add(new V(v.x,v.y-1,0));
            }
        }

        boolean[][] check = new boolean[N][N];
        List<V> list = new ArrayList<>();

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(arr[i][j]==-1){
                    if(i+1<N && arr[i+1][j]==0 && !check[i+1][j]){
                        check[i+1][j] = true;
                        list.add(new V(i+1,j,1));
                    }
                    if(i-1>=0 && arr[i-1][j]==0 && !check[i-1][j]){
                        check[i-1][j] = true;
                        list.add(new V(i-1,j,1));
                    }
                    if(j+1<N && arr[i][j+1]==0 && !check[i][j+1]){
                        check[i][j+1] = true;
                        list.add(new V(i,j+1,1));
                    }
                    if(j-1>=0 && arr[i][j-1]==0 && !check[i][j-1]){
                        check[i][j-1] = true;
                        list.add(new V(i,j-1,1));
                    }
                }
            }
        }

        while(true){
            V temp = list.remove(0);
            check = new boolean[N][N];
            queue = new LinkedList<>();
            queue.add(new V(temp.x,temp.y,temp.length));
            while(!queue.isEmpty()){
                V v = queue.remove();
                if(arr[v.x][v.y]==1){
                    if(solution==Integer.MAX_VALUE || solution>v.length-1) solution = v.length-1;
                }else {
                    if (v.x + 1 < N && arr[v.x + 1][v.y] != -1 && !check[v.x + 1][v.y]) {
                        check[v.x + 1][v.y] = true;
                        queue.add(new V(v.x + 1, v.y, v.length + 1));
                    }
                    if (v.x - 1 >= 0 && arr[v.x - 1][v.y] != -1 && !check[v.x - 1][v.y]) {
                        check[v.x - 1][v.y] = true;
                        queue.add(new V(v.x - 1, v.y, v.length + 1));
                    }
                    if (v.y + 1 < N && arr[v.x][v.y + 1] != -1 && !check[v.x][v.y + 1]) {
                        check[v.x][v.y + 1] = true;
                        queue.add(new V(v.x, v.y + 1, v.length + 1));
                    }
                    if (v.y - 1 >= 0 && arr[v.x][v.y - 1] != -1 && !check[v.x][v.y - 1]) {
                        check[v.x][v.y - 1] = true;
                        queue.add(new V(v.x, v.y - 1, v.length + 1));
                    }
                }
            }

            if(list.size()==0) break;
        }

    }

}

class V{
    int x;
    int y;
    int length;
    public V(int x,int y,int length){
        this.x=x;
        this.y=y;
        this.length=length;
    }
}