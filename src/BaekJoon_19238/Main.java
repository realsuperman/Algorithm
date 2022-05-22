package BaekJoon_19238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String,String> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int[][] array = new int[N+1][N+1];
        int M = Integer.parseInt(str[1]);
        int gas = Integer.parseInt(str[2]);

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i+1][j+1] = Integer.parseInt(str[j]);
            }
        }

        str = br.readLine().split(" ");
        PriorityQueue<V> queue = new PriorityQueue<>();
        queue.add(new V(Integer.parseInt(str[0]),Integer.parseInt(str[1]),gas,false,0,0,0,0));

        boolean[][] check = new boolean[N+1][N+1];
        for(int i=0;i<M;i++){
            str = br.readLine().split(" ");
            int startX = Integer.parseInt(str[0]);
            int startY = Integer.parseInt(str[1]);
            int endX = Integer.parseInt(str[2]);
            int endY = Integer.parseInt(str[3]);

            if(startX==queue.peek().x && startY==queue.peek().y){
                queue.remove();
                queue.add(new V(startX,startY,gas,true,0,0,startX,startY));
            }
            String key = Integer.toString(startX)+"-"+Integer.toString(startY);
            String value = Integer.toString(endX)+"-"+Integer.toString(endY);
            map.put(key,value);
        }

        while(!queue.isEmpty()){
            V v = queue.remove();
            check[v.x][v.y]=true;
            if(v.check){ // 태운상태
                String key = Integer.toString(v.startX)+"-"+Integer.toString(v.startY);
                String value = Integer.toString(v.x)+"-"+Integer.toString(v.y);

                if( map.get(key).equals(value) ){ // 목적지에 도달하였나
                    if(v.gas>=0){ // 가스내로 갔는가
                        map.remove(key);
                        if(map.size()==0){System.out.println((v.plus*2)+v.gas); return;} // 모든손님태움
                        queue = new PriorityQueue<>();
                        check = new boolean[N+1][N+1];
                        check[v.x][v.y] = true;
                        if(map.get(value)!=null){ // 바로 손님이 있음
                            queue.add(new V(v.x, v.y, (v.plus * 2) + v.gas,  true, 0, 0,v.x,v.y));
                        }else{ // 손님이 없음
                            queue.add(new V(v.x, v.y, (v.plus * 2) + v.gas,  false, 0, 0,0,0));
                        }
                    }else{
                        System.out.println(-1);
                        return;
                    }
                }else{ // 손님을 태우고 목적지로 가는중
                    if (v.x+1<= N && array[v.x + 1][v.y] != 1 && !check[v.x + 1][v.y]) {
                        check[v.x + 1][v.y] = true;
                        queue.add(new V(v.x + 1, v.y, v.gas - 1, true,v.plus+1,v.dist+1,v.startX,v.startY));
                    }
                    if (v.y-1>=1 && array[v.x][v.y-1] != 1 && !check[v.x][v.y-1]) {
                        check[v.x][v.y-1] = true;
                        queue.add(new V(v.x , v.y-1, v.gas - 1, true,v.plus+1,v.dist+1,v.startX,v.startY));
                    }
                    if (v.y+1<=N && array[v.x][v.y+1] != 1 && !check[v.x][v.y+1]) {
                        check[v.x][v.y+1] = true;
                        queue.add(new V(v.x , v.y+1, v.gas - 1, true,v.plus+1,v.dist+1,v.startX,v.startY));
                    }
                    if (v.x-1>=1 && array[v.x - 1][v.y] != 1 && !check[v.x - 1][v.y]) {
                        check[v.x - 1][v.y] = true;
                        queue.add(new V(v.x - 1, v.y, v.gas - 1, true,v.plus+1,v.dist+1,v.startX,v.startY));
                    }

                }
            }else{ // 안태운상태
                String key = Integer.toString(v.x)+"-"+Integer.toString(v.y);
                if(map.get(key)!=null){ // 고객을 만남
                    if(v.gas>=0){
                        queue = new PriorityQueue<>();
                        check = new boolean[N+1][N+1];
                        check[v.x][v.y] = true;
                        queue.add(new V(v.x,v.y,v.gas,true,0,0,v.x,v.y));
                    }else{
                        System.out.println(-1);
                        return;
                    }
                }else { // 고객을 못만남
                    if (v.x+1<= N && array[v.x + 1][v.y] != 1 && !check[v.x + 1][v.y]) {
                        check[v.x + 1][v.y] = true;
                        queue.add(new V(v.x + 1, v.y, v.gas - 1, false,0,v.dist+1,0,0));
                    }
                    if (v.y-1>=1 && array[v.x][v.y-1] != 1 && !check[v.x][v.y-1]) {
                        check[v.x][v.y-1] = true;
                        queue.add(new V(v.x , v.y-1, v.gas - 1, false,0,v.dist+1,0,0));
                    }
                    if (v.y+1<=N && array[v.x][v.y+1] != 1 && !check[v.x][v.y+1]) {
                        check[v.x][v.y+1] = true;
                        queue.add(new V(v.x , v.y+1, v.gas - 1, false,0,v.dist+1,0,0));
                    }
                    if (v.x-1>=1 && array[v.x - 1][v.y] != 1 && !check[v.x - 1][v.y]) {
                        check[v.x - 1][v.y] = true;
                        queue.add(new V(v.x - 1, v.y, v.gas - 1, false,0,v.dist+1,0,0));
                    }
                }
            }
        }
        System.out.println(-1);
    }
}

class V implements Comparable<V>{
    int x; // x좌표
    int y; // y좌표
    int gas; // 가스값
    boolean check; //태운상태면 true
    int plus; // 도착했을 때 plus만큼 더해진다
    int dist; // 거리
    int startX; // 승객을 태운위치(check가 true일 때 의미 있음)
    int startY; // 승객을 태운위치(check가 true일 때 의미 있음)

    public V(int x,int y,int gas,boolean check,int plus,int dist,int startX,int startY){
        this.x=x;
        this.y=y;
        this.gas=gas;
        this.check=check;
        this.plus=plus;
        this.dist=dist;
        this.startX=startX;
        this.startY=startY;
    }

    @Override
    public int compareTo(V o) {
        if(this.dist == o.dist){
            if(this.x==o.x){
                return this.y-o.y;
            }else{
                return this.x-o.x;
            }
        }else{
            return this.dist - o.dist;
        }
    }
}