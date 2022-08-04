package BaekJoon_7569;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;


public class Main {
    static int[] dx = {1,-1,0,0,0,0};
    static int[] dy = {0,0,1,-1,0,0};
    static int[] dz = {0,0,0,0,1,-1};
    static int k,n,m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        k = Integer.parseInt(str[2]);
        n = Integer.parseInt(str[1]);
        m = Integer.parseInt(str[0]);
        int[][][] array = new int[k][n][m];
        boolean[][][] check = new boolean[k][n][m];
        Queue<V> queue = new PriorityQueue<>();

        for(int i=0;i<k;i++){
            for(int j=0;j<n;j++){
                str = br.readLine().split(" ");
                for(int index=0;index<str.length;index++){
                    array[i][j][index]= Integer.parseInt(str[index]);
                    if(array[i][j][index]==1){
                        check[i][j][index]=true;
                        queue.add(new V(i,j,index,0));
                    }
                }
            }
        }
        if(checkMethod(array)) {System.out.println(0); return;}
        int cnt = 0;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(checkMethod(array)) {
                System.out.println(cnt);
                return;
            }

            while(true){
                for(int i=0;i<6;i++){
                    int x = dx[i]+v.x;
                    int y = dy[i]+v.y;
                    int z = dz[i]+v.z;
                    if(x<0 || x>=n || y<0 || y>=m || z<0 || z>=k || check[z][x][y] || array[z][x][y]==-1) continue;
                    check[z][x][y]=true;
                    array[z][x][y]=1;
                    queue.add(new V(z,x,y,v.time+1));
                }
                if(queue.size()>0 && queue.peek().time==cnt && !queue.isEmpty()) v = queue.remove();
                else break;
            }
            cnt++;

        }
        System.out.println(-1);
    }

    public static boolean checkMethod(int[][][] array){
        for(int i=0;i<k;i++){
            for(int j=0;j<n;j++){
                for(int index=0;index<m;index++){
                    if(array[i][j][index]==0) return false;
                }
            }
        }
        return true;
    }
}

class V implements Comparable<V>{
    int x;
    int y;
    int z;
    int time;

    public V(int z,int x,int y,int time){
        this.z=z;
        this.x=x;
        this.y=y;
        this.time=time;
    }

    @Override
    public int compareTo(V o) {
        return this.time-o.time;
    }
}