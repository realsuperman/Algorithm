package BaekJoon_1941.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] array = new char[5][5];
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<5;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<5;j++) array[i][j]=str[j].charAt(0);
        }
        dfs(0,0,new ArrayList<>());
        System.out.println(result);
    }

    public static void dfs(int depth,int start,List<Integer> list){
        if(depth==7){
            bfs(list);
            return;
        }

        for(int i=start;i<25;i++){
            list.add(i);
            int size = list.size()-1;
            dfs(depth+1,i+1,list);
            list.remove(size);
        }
    }

    public static void bfs(List<Integer> list){
        List<V> converter = new ArrayList<>();
        boolean[][] check = new boolean[5][5];
        for(int v : list){
            int[] result = returnLocation(v);
            converter.add(new V(result[0],result[1]));
        }

        Queue<V> queue = new LinkedList<>();
        V temp = converter.get(0);
        check[temp.x][temp.y]=true;
        queue.add(new V(temp.x,temp.y));

        int size = 1;
        while(!queue.isEmpty()) {
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=5 || x<0 || y>=5 || y<0 || check[x][y]) continue;
                if(!getConnectionInfo(x,y,converter)) continue;
                queue.add(new V(x,y));
                check[x][y]=true;
                size++;
            }
        }

        if(size == 7){
            int S = 0;
            for(V v : converter) if(array[v.x][v.y]=='S') S++;
            if(S>=4) result++;
        }
    }

    public static int[] returnLocation(int num){
        return new int[] { num / 5, num % 5 };
    }

    public static boolean getConnectionInfo(int x,int y, List<V> list){
        for(V v : list) if(x == v.x && y == v.y) return true;
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