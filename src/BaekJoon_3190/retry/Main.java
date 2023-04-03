package BaekJoon_3190.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static int[] dx = {0,0,1,-1}; // 동서남북
    static int[] dy = {1,-1,0,0}; // 동서남북
    static int N;
    static int[][] array;
    static int time=1;
    static List<V> list = new ArrayList<>();
    static int head = 0;
    static int index = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        int number = Integer.parseInt(br.readLine());
        while(number-->0){
            String[] str = br.readLine().split(" ");
            array[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1] = 1;
        }
        number = Integer.parseInt(br.readLine());
        while(number-->0){
            String[] str = br.readLine().split(" ");
            list.add(new V(Integer.parseInt(str[0]),str[1].charAt(0)));
        }

        solution();
        System.out.println(time);
    }

    private static void solution() {
        List<Location> locations = new LinkedList<>(); // 뱀의 위치
        locations.add(new Location(0,0));

        while(true){
            int x = dx[head]+locations.get(0).x;
            int y = dy[head]+locations.get(0).y;
            boolean breakLoop = false;

            if(x>=N || x<0 || y>=N || y<0) break; // 영역밖으로 나간경우
            for(Location location : locations) if(location.x==x && location.y==y) breakLoop=true; // 자기몸을 만난경우
            if(breakLoop) break;

            locations.add(0, new Location(x,y));
            if(array[x][y]==1) array[x][y]=0;
            else locations.remove(locations.size()-1);

            if(index<list.size() && list.get(index).time == time){ // 뱀의 방향 변환
                if(list.get(index).action=='L'){
                    if(head==0) head = 3; // 북
                    else if(head==1) head = 2; // 남
                    else if(head==2) head = 0; // 동
                    else if(head==3) head = 1; // 서
                }else{
                    if(head==0) head=2; // 남
                    else if(head==1) head=3; // 북
                    else if(head==2) head=1; // 서
                    else if(head==3) head=0; // 동
                }
                index++;
            }

            time++;
        }

    }
}

class V{
    int time;
    char action;
    public V(int time,char action){
        this.time=time;
        this.action=action;
    }
}

class Location{
    int x;
    int y;
    public Location(int x,int y){
        this.x=x;
        this.y=y;
    }
}