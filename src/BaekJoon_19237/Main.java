package BaekJoon_19237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,K;
    static Map<String, Queue<PriorityNumber>> array = new HashMap<>(); // 특정 위치에 특정 상어가 들어가있다를 표현하기 위한 자료구조
    static Location[] shark; // 상어
    static V[][] scent; // 냄새
    static Map<Integer, Map<Integer, List<Integer>>> priority = new HashMap<>(); // 상어의 방향마다의 우선순위 ( 요청시 항상 상어 번호 - 1)
    static int[] dx = {-1,1,0,0}; // 위,아,왼,오
    static int[] dy = {0,0,-1,1}; // 위,아,왼,오

    // 상어 이동시 본인의 위치를 array에서 제거해야 하지 않나?
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);

        shark = new Location[M];
        scent = new V[N][N];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                int v = Integer.parseInt(str[j])-1;
                if(v>=0){ // 상어 위치
                    shark[v] = new Location(i,j,-1);
                    Queue<PriorityNumber> queue = new PriorityQueue<>();
                    queue.add(new PriorityNumber(v));
                    array.put(i+","+j,queue);
                }
            }
        }

        str = br.readLine().split(" ");
        for(int i=0;i<M;i++) shark[i].direction=Integer.valueOf(str[i])-1;
        for(int i=0;i<M;i++){
            Map<Integer, List<Integer>> map = new HashMap<>();
            for(int j=0;j<4;j++) {
                List<Integer> list = new ArrayList<>();
                str = br.readLine().split(" ");
                for (int k = 0; k < 4; k++)  list.add(Integer.valueOf(str[k])-1);
                map.put(j, list);
            }
            priority.put(i, map);
        }

        int v = solution();
        System.out.println(v==-1?-1:v-1);
    }

    public static int solution() {
        int result = 0;
        while(result++<1001){
            if(isEnd()) break;
            spreadScent(); // 상어들이 냄새를 남김
            moveShark(); // 상어들이 움직임
            removeScent(); // 냄새 제거
            removeDuplication(); // 상어들의 중복 제거
        }
        return result>1001?-1:result;
    }


    private static void spreadScent() {
        for(int i=0;i<shark.length;i++){
            if(shark[i].x!=-1 && shark[i].y!=-1 && shark[i].direction!=-1){
                scent[shark[i].x][shark[i].y] = new V(i, K);
            }
        }
    }

    private static void moveShark() {
        V[][] temp = new V[N][N]; for(int i=0;i<N;i++) for(int j=0;j<N;j++) temp[i][j]=scent[i][j];
        for(int i=0;i<shark.length;i++){
            if(shark[i].x!=-1 && shark[i].y!=-1 && shark[i].direction!=-1){ // 죽은 상어가 아니라면
                int oldX = shark[i].x; // 이전 상어의 X값
                int oldY = shark[i].y; // 이전 상어의 Y값

                Queue<PriorityNumber> init = array.get(oldX + "," + oldY);
                Queue<PriorityNumber> initBean = new PriorityQueue<>();
                while(!init.isEmpty()){
                    PriorityNumber v = init.remove();
                    if(v.v == i) continue;
                    initBean.add(v);
                }
                array.put(oldX+","+oldY,initBean);

                int blankCnt = 0; // 빈칸의 개수
                List<Location> blankList = new ArrayList<>(); // 빈칸의 좌표 리스트들
                int scentCnt = 0; // 본인 냄새의 개수
                List<Location> scentList = new ArrayList<>(); // 본인 냄새 좌표 리스트들

                for(int k=0;k<4;k++){
                    int x = shark[i].x+dx[k];
                    int y = shark[i].y+dy[k];
                    if(x>=N || x<0 || y>=N || y<0) continue;
                    if(temp[x][y] == null){ blankCnt++; blankList.add(new Location(x,y,k));}
                    else if(temp[x][y].host == i){ scentCnt++; scentList.add(new Location(x,y,k));}
                }

                if(blankCnt>=2 || (blankCnt==0 && scentCnt>=2)){ // 빈칸이 여러개인 경우
                    Map<Integer, List<Integer>> sharkPriority = priority.get(i);
                    List<Integer> directions = sharkPriority.get(shark[i].direction);
                    List<Location> list;

                    if(blankCnt>=2) list = blankList;
                    else list = scentList;

                    for(int index=0;index<directions.size();index++){ // 우선순위가 있는 방향
                        boolean loop = false;
                        for(Location location : list){ // 현재 원소들
                            if(location.direction == directions.get(index)){
                                String key = location.x+","+location.y;
                                if(array.get(key)==null){
                                    Queue queue = new PriorityQueue();
                                    queue.add(new PriorityNumber(i));
                                    array.put(key, queue);
                                }else{
                                    Queue<PriorityNumber> priorityNumbers = array.get(key);
                                    priorityNumbers.add(new PriorityNumber(i));
                                }
                                shark[i].x = location.x;
                                shark[i].y = location.y;
                                shark[i].direction = location.direction;
                                loop = true;
                                break;
                            }
                            if(loop) break;
                        }
                        if(loop) break;
                    }
                }else if(blankCnt==1 || (blankCnt==0 && scentCnt==1)){ // 빈칸이 한개거나 본인 냄새칸이 한개인 경우
                    int x,y,direction;

                    if(blankCnt==1) {
                        x = blankList.get(0).x;
                        y = blankList.get(0).y;
                        direction = blankList.get(0).direction;
                    }else{
                        x = scentList.get(0).x;
                        y = scentList.get(0).y;
                        direction = scentList.get(0).direction;
                    }

                    String key = x+","+y;
                    if(array.get(key)==null){
                        Queue queue = new PriorityQueue();
                        queue.add(new PriorityNumber(i));
                        array.put(key, queue);
                    }else{
                        Queue<PriorityNumber> priorityNumbers = array.get(key);
                        priorityNumbers.add(new PriorityNumber(i));
                    }
                    shark[i].x = x;
                    shark[i].y = y;
                    shark[i].direction = direction;
                }
            }
        }
    }
    private static void removeDuplication() {
        for(String str : array.keySet()){
            if(array.get(str).size()==0) continue;
            Queue<PriorityNumber> priorityNumbers = array.get(str);
            int peek = priorityNumbers.peek().v;
            int index = 0;
            while(!priorityNumbers.isEmpty()){
                PriorityNumber v = priorityNumbers.remove();
                if(index>=1) shark[v.v]=new Location(-1,-1,-1); // 최초 상어를 제외한 나머지 상어의 위치를 -1로 세팅
                index++;
            }
            priorityNumbers.add(new PriorityNumber(peek));
            array.put(str, priorityNumbers);
        }
    }

    private static void removeScent(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(scent[i][j]!=null){
                    scent[i][j].time--;
                    if(scent[i][j].time==0) scent[i][j]=null;
                }
            }
        }
    }

    private static boolean isEnd(){
        int count = 0;
        for(Location location : shark){
            if(location.x != -1 && location.y != -1) count++;
            if(count>=2) return false;
        }
        return true;
    }
}

class V{
    int host;
    int time;
    public V(int host, int time){
        this.host=host;
        this.time=time;
    }
    public V(int host){
        this.host=host;
    }
}

class Location{
    int x;
    int y;
    int direction;
    public Location(int x,int y,int direction){
        this.x=x;
        this.y=y;
        this.direction=direction;
    }
}

class PriorityNumber implements Comparable<PriorityNumber>{
    int v;
    public PriorityNumber(int v){
        this.v=v;
    }

    @Override
    public int compareTo(PriorityNumber o) {
        return this.v-o.v;
    }
}