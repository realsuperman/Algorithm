package BaekJoon_3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    static int[] dx = {-1, 0, 1, 0}; // 상 좌 하 우 순서
    static int[] dy = {0, -1, 0, 1};
    static int dir = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] array = new int[N+1][N+1];
        List<V> list = new ArrayList<>();

        int loop = Integer.parseInt(br.readLine());
        for(int i=0;i<loop;i++){
            String[] str = br.readLine().split(" ");
            array[Integer.parseInt(str[0])][Integer.parseInt(str[1])]=1;
        }
        loop = Integer.parseInt(br.readLine());
        for(int i=0;i<loop;i++){
            String[] str = br.readLine().split(" ");
            list.add(new V(Integer.parseInt(str[0]),str[1]));
        }

        Deque<Location> deque = new ArrayDeque<>();
        deque.add(new Location(1,1));
        int result=0;
        while(!deque.isEmpty()){
            int x = deque.peekLast().x+dx[dir];
            int y = deque.peekLast().y+dy[dir];

            if(x>N || y>N || x<1 || y<1) {System.out.println(result+1); return;}
            for(Location location : deque){
                if(location.x == x && location.y == y){
                    System.out.println(result+1);
                    return;
                }
            }
            deque.add(new Location(x,y));

            if(array[x][y]==1){
                array[x][y]=0;
            }else{
                deque.removeFirst();
            }
            result++;
            if(list.size()>0&&list.get(0).time==result){
                rotate(list.get(0).direction);
                list.remove(0);
            }
        }

    }

    public static void rotate(char ch){
        if(ch=='L'){
            dir = (dir+1)%4;
        }else{
            dir = (dir+3)%4;
        }
    }
}

class Location{
    int x;
    int y;
    public Location(int x,int y){this.x=x;this.y=y;}
}

class V{
    int time;
    char direction;
    public V(int time, String direction){
        this.time=time;
        this.direction=direction.charAt(0);
    }
}