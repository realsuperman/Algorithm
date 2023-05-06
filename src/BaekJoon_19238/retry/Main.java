package BaekJoon_19238.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,GAS,cabX,cabY;
    static int[][] array;
    static Map<String,String> locations = new HashMap<>(); // 승객들 위치(시작, 도착)
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]); // 배열 사이즈
        array = new int[N][N];
        M = Integer.parseInt(str[1]); // 반복변수
        GAS = Integer.parseInt(str[2]); // 초기가스
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<N;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        str = br.readLine().split(" ");
        cabX = Integer.parseInt(str[0])-1; // 택시 X좌표
        cabY = Integer.parseInt(str[1])-1; // 택시 Y좌표
        while(M-->0){
            str = br.readLine().split(" ");
            locations.put(String.valueOf(Integer.parseInt(str[0])-1)+","+String.valueOf(Integer.parseInt(str[1])-1),String.valueOf(Integer.parseInt(str[2])-1)+","+String.valueOf(Integer.parseInt(str[3])-1));
        }
        solution();
    }

    public static void solution(){
        while(locations.size()>0){
            int[] location = getCustomer(cabX, cabY); // 손님을 찾음
            GAS-=location[2]; // 손님까지 갈 때 사용되는 연료랑을 빼줌
            if(GAS<0){System.out.println(-1); return;} // 만약 가스가 0보다 적은경우 손님까지 못가는 경우이므로 뺀다

            String[] targetLocation = locations.get(location[0] + "," + location[1]).split(",");// 손님의 목적지를 찾음
            int v = getDistance(location[0],location[1],Integer.parseInt(targetLocation[0]),Integer.parseInt(targetLocation[1])); // 거리를 구함
            GAS-=v; // 손님을 목적지까지 데려줄 때 사용되는 연료량을 빼줌
            if(GAS<0){System.out.println(-1); return;} // 만약 가스가 0보다 적은경우 손님까지 못가는 경우이므로 뺀다
            GAS = GAS + (v*2);
            cabX = Integer.parseInt(targetLocation[0]);
            cabY = Integer.parseInt(targetLocation[1]);
            locations.remove(location[0]+","+location[1]);
        }
        System.out.println(GAS);
    }

    private static int[] getCustomer(int xLocation, int yLocation) { // 태울 손님을 구할 메소드, 리턴은 태울 손님의 X,Y 좌표 및 거리
        int[] result = new int[3];
        Queue<V> shortPath = new PriorityQueue<>();
        int[][] value = new int[N][N];
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) value[i][j]=Integer.MAX_VALUE;
        bfs(xLocation, yLocation,value);

        for(String key : locations.keySet()){
            String[] v = key.split(",");
            int x = Integer.valueOf(v[0]);
            int y = Integer.valueOf(v[1]);
            shortPath.add(new V(x,y,value[x][y]));
        }

        V v = shortPath.remove();
        result[0] = v.x;
        result[1] = v.y;
        result[2] = v.time;
        return result;
    }

    private static void bfs(int xLocation, int yLocation,int[][] value) { // 한 점에서 출발해서 손님의 모든 최단 경로를 구함
        boolean[][] check = new boolean[N][N];
        check[xLocation][yLocation]=true;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(xLocation,yLocation,0));

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(value[v.x][v.y]>v.time) value[v.x][v.y]=v.time;
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=N || x<0 || y>=N || y<0 || check[x][y] || array[x][y]==1) continue;
                check[x][y]=true;
                queue.add(new V(x,y,v.time+1));
            }
        }
    }

    private static int getDistance(int x1, int y1, int x2, int y2){ // 특정위치랑 특정위치의 거리 계산
        int distance = Integer.MAX_VALUE;
        boolean[][] check = new boolean[N][N];
        check[x1][y1]=true;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(x1,y1,0));

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x==x2 && v.y==y2){
                distance = v.time;
                break;
            }
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=N || x<0 || y>=N || y<0 || check[x][y] || array[x][y]==1) continue;
                check[x][y]=true;
                queue.add(new V(x,y,v.time+1));
            }
        }
        return distance;
    }

}

class V implements Comparable<V>{
    int x;
    int y;
    int time;

    public V(int x,int y,int time){
        this.x=x;
        this.y=y;
        this.time=time;
    }

    public int compareTo(V v){
        if(this.time==v.time){
            if(this.x==v.x){
                return this.y-v.y;
            }
            return this.x-v.x;
        }
        return this.time-v.time;
    }
}