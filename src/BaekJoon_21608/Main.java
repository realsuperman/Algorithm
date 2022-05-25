package BaekJoon_21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int solution = 0;
        N = Integer.parseInt(br.readLine());
        int[][] array = new int[N+1][N+1];
        V[] v = new V[N*N+1];
        int[] location = new int[N*N]; // 순서

        for(int i=0;i<N*N;i++){
            String[] str = br.readLine().split(" ");
            v[Integer.parseInt(str[0])] = new V(Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]));
            location[i] = Integer.parseInt(str[0]);
        }

        for(int i=0;i<location.length;i++){
            int max = -1;
            List<Location> list = new ArrayList<>();

            for(int j=1;j<=N;j++){
                for(int k=1;k<=N;k++){
                    if(array[j][k]!=0) continue;
                    int value = friend(array,v,j,k,location[i]);
                    if(value>max){
                        max = value;
                        list = new ArrayList<>();
                        list.add(new Location(j,k));
                    }else if(value==max){
                        list.add(new Location(j,k));
                    }
                }
            }
            if(list.size()==1){ // 1번조건 만족
                Location l = list.get(0);
                array[l.x][l.y] = location[i];
            }else{ // 2번조건 체크
                for(Location l : list){
                    int cnt = 0;
                    if(l.x-1>=1 && array[l.x-1][l.y]==0){
                        cnt++;
                    }
                    if(l.x+1<=N && array[l.x+1][l.y]==0){
                        cnt++;
                    }
                    if(l.y-1>=1 && array[l.x][l.y-1]==0){
                        cnt++;
                    }
                    if(l.y+1<=N && array[l.x][l.y+1]==0){
                        cnt++;
                    }
                    l.v = cnt;
                }
                PriorityQueue<Location> queue = new PriorityQueue<>();
                for(Location l : list) queue.add(l);
                array[queue.peek().x][queue.peek().y] = location[i];
                //break;
            }
        }
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                int value = friend(array,v,i,j,array[i][j]);
                switch (value){
                    case 0 : solution+=0; break;
                    case 1 : solution+=1; break;
                    case 2 : solution+=10; break;
                    case 3 : solution+=100; break;
                    default: solution+=1000;
                }
            }
        }
        System.out.println(solution);
    }

    public static int friend(int[][] array,V[] v,int x,int y,int vIndex){
        int cnt = 0;
        if(x-1>=1){
            for(int i=0;i<4;i++){
                if(array[x-1][y]==v[vIndex].array[i]) cnt++;
            }
        }
        if(x+1<=N){
            for(int i=0;i<4;i++){
                if(array[x+1][y]==v[vIndex].array[i]) cnt++;
            }
        }
        if(y-1>=1){
            for(int i=0;i<4;i++){
                if(array[x][y-1]==v[vIndex].array[i]) cnt++;
            }
        }
        if(y+1<=N){
            for(int i=0;i<4;i++){
                if(array[x][y+1]==v[vIndex].array[i]) cnt++;
            }
        }
        return cnt;
    }
}

class V{
    int[] array = new int[4];
    public V(int friend1,int friend2,int friend3,int friend4){
        array[0] = friend1;
        array[1] = friend2;
        array[2] = friend3;
        array[3] = friend4;
    }
}

class Location implements Comparable<Location>{
    int x;
    int y;
    int v;
    public Location(int x,int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public int compareTo(Location o) {
        if(this.v==o.v){
            if(this.x==o.x){
                return this.y-o.y;
            }else{
                return this.x-o.x;
            }
        }
        return o.v-this.v;
    }
}