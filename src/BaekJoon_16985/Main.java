package BaekJoon_16985;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int result = Integer.MAX_VALUE;
    static int[] dx = {-1,1,0,0,0,0};
    static int[] dy = {0,0,-1,1,0,0};
    static int[] dz = {0,0,0,0,-1,1};

    static int[][][] array = new int[5][5][5];
    static boolean[] board1 = new boolean[5];
    static boolean[] board2 = new boolean[5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                String[] str = br.readLine().split(" ");
                for(int k=0;k<str.length;k++){
                    array[i][j][k]= Integer.parseInt(str[k]);
                }
            }
        }
        init(0,0);
        System.out.println(result==Integer.MAX_VALUE?-1:result);
    }

    public static void init(int start,int index){
        if(start==5){
            solution(0,new ArrayList<>());
            return;
        }

        for(int i=index;i<5;i++){
            if(board1[i]) continue;

            board1[i]=true;
            int[][] temp = new int[5][5];
            for(int j=0;j<5;j++) for(int k=0;k<5;k++) temp[j][k] = array[i][j][k];

            for(int j=0;j<4;j++){ // 방향으로 반복
                rotate(i);
                init(start+1,i);
            }
            array[i] = temp;
            board1[i]=false;
        }
    }

    public static void rotate(int k){
        int[][] temp = new int[5][5];
        int x = 0;
        int y = 0;

        for(int i=0;i<5;i++){
            for(int j=4;j>=0;j--){
                temp[x][y++] = array[k][j][i];
            }
            x++;
            y=0;
        }
        array[k] = temp;
    }

    public static void solution(int number, List<Integer> list){
        if(number==5){
            int[][][] arr = new int[5][5][5];

            int index = 0;
            for(int i : list ){
                arr[index++]=array[i];
            }
            bfs(arr);
            return;
        }

        for(int i=0;i<5;i++){
            if(board2[i]) continue;
            board2[i]=true;
            list.add(i);
            int size = list.size();
            solution(number+1,list);
            list.remove(size-1);
            board2[i]=false;
        }
    }

    public static void bfs(int[][][] array){
        Queue<V> queue = new LinkedList<>();
        boolean[][][] check = new boolean[5][5][5];
        if(array[0][0][0]==0 || array[4][4][4]==0) return;
        queue.add(new V(0,0,0,0));
        check[0][0][0]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.k==4 && v.x==4 && v.y==4){
                if(result>v.time) result=v.time;
                return;
            }
            for(int i=0;i<6;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                int z = dz[i]+v.k;

                if(x<0 || y<0 || z<0 || x>=5 || y>=5 || z>=5 || check[z][x][y] || array[z][x][y]==0) continue;

                check[z][x][y]=true;
                queue.add(new V(z,x,y,v.time+1));
            }
        }

    }

}

class V{
    int k;
    int x;
    int y;
    int time;

    public V(int k,int x,int y,int time){
        this.k=k;
        this.x=x;
        this.y=y;
        this.time=time;
    }
}