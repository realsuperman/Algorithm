package BaekJoon_11559.retry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main{
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static char[][] array;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        array = new char[12][6];
        for(int i=0;i<12;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<6;j++) array[i][j] = str[j].charAt(0);
        }
        System.out.println(simulation());
    }

    public static int simulation() {
        int cnt = 0;

        while(true) {
            char[][] temp = new char[12][6];
            boolean[][] check = new boolean[12][6];
            for(int i=0;i<12;i++) for(int j=0;j<6;j++) temp[i][j]=array[i][j];

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (array[i][j] != '.') {
                        check[i][j] = true;
                        bfs(i, j, array[i][j], check);
                    }
                }
            }

            if(checkEqual(temp)) break;

            downward();
            cnt++;
        }

        return cnt;
    }

    public static void bfs(int startX, int startY, char ch, boolean[][] check) {
        List<V> list = new ArrayList<>();
        list.add(new V(startX,startY));
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));

        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0 || x>=12 || y<0 || y>=6 || check[x][y] || array[x][y]!=ch) continue;
                check[x][y]=true;
                queue.add(new V(x,y));
                list.add(new V(x,y));
            }
        }
        if(list.size()>=4) for(V v : list) array[v.x][v.y]='.';
    }

    public static void downward(){
        char[][] temp = new char[12][6];

        while(!checkEqual(temp)) {
            for(int i=0;i<12;i++) for(int j=0;j<6;j++) temp[i][j]=array[i][j];

            for (int i = 11; i > 0; i--) {
                for(int j=0;j<6;j++){
                    if(array[i][j]=='.'){ array[i][j] = array[i-1][j]; array[i-1][j]='.';}
                }
            }
        }

    }

    public static boolean checkEqual(char[][] temp){
        for(int i=0;i<12;i++){
            for(int j=0;j<6;j++){
                if(array[i][j]!=temp[i][j]) return false;
            }
        }
        return true;
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