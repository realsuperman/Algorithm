package BaekJoon_2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        int[][] array = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }

        int time = 0;
        List<V> list = new ArrayList<>();
        Queue<V> queue = new LinkedList<>();
        while(count(array)>0){
            time++;
            list = new ArrayList<>();

            queue.add(new V(0,0));
            boolean[][] check = new boolean[N][M];
            check[0][0] = true;
            int[][] temp = new int[N][M];
            for(int i=0;i<N;i++) for(int j=0;j<M;j++) temp[i][j] = array[i][j];

            while(!queue.isEmpty()){
                V v = queue.remove();
                if(v.x-1>=0 && !check[v.x-1][v.y]){
                    if(temp[v.x-1][v.y]>0){
                        if(temp[v.x-1][v.y]==2){
                            list.add(new V(v.x-1,v.y));
                            check[v.x-1][v.y]=true;
                        }
                        temp[v.x-1][v.y]+=1;
                    }else{
                        queue.add(new V(v.x-1,v.y));
                        check[v.x-1][v.y]=true;
                    }
                }

                if(v.x+1<N && !check[v.x+1][v.y]){
                    if(temp[v.x+1][v.y]>0){
                        if(temp[v.x+1][v.y]==2){
                            list.add(new V(v.x+1,v.y));
                            check[v.x+1][v.y]=true;
                        }
                        temp[v.x+1][v.y]+=1;
                    }else{
                        queue.add(new V(v.x+1,v.y));
                        check[v.x+1][v.y]=true;
                    }
                }

                if(v.y-1>=0 && !check[v.x][v.y-1]){
                    if(temp[v.x][v.y-1]>0){
                        if(temp[v.x][v.y-1]==2){
                            list.add(new V(v.x,v.y-1));
                            check[v.x][v.y-1]=true;
                        }
                        temp[v.x][v.y-1]+=1;
                    }else{
                        queue.add(new V(v.x,v.y-1));
                        check[v.x][v.y-1]=true;
                    }
                }

                if(v.y+1<M && !check[v.x][v.y+1]){
                    if(temp[v.x][v.y+1]>0){
                        if(temp[v.x][v.y+1]==2){
                            list.add(new V(v.x,v.y+1));
                            check[v.x][v.y+1]=true;
                        }
                        temp[v.x][v.y+1]+=1;
                    }else{
                        queue.add(new V(v.x,v.y+1));
                        check[v.x][v.y+1]=true;
                    }
                }
            }
            for(V v : list) array[v.x][v.y] = 0;
        }
        System.out.println(time);
    }

    public static int count(int array[][]){
        int cnt = 0;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                if(array[i][j] == 1) cnt++;
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