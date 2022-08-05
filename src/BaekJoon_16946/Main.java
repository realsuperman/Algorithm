package BaekJoon_16946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] array,result;
    static boolean[][] check;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static Map<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        result = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        check = new boolean[N][M];
        int number = 2;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(check[i][j] || array[i][j]==1) continue;
                check[i][j]=true;
                array[i][j]=number;
                bfs(i,j,number++);
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==1){
                    Set<Integer> set = new HashSet<>();
                    for(int k=0;k<4;k++){
                        int x = dx[k]+i;
                        int y = dy[k]+j;
                        if(x<0 || y<0 || x>=N || y>=M || array[x][y]<2) continue;
                        set.add(array[x][y]);
                    }

                    int sum = 1;
                    for(int k : set) sum+=map.get(k);
                    result[i][j]=sum;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                sb.append(result[i][j]%10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void bfs(int startX,int startY,int number){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        int cnt = 1;
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0 || y<0 || x>=N || y>=M || check[x][y] || array[x][y]!=0) continue;
                check[x][y]=true;
                array[x][y]=number;
                queue.add(new V(x,y));
                cnt++;
            }
        }
        map.put(number,cnt);
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