package BaekJoon_21609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] array;
    static int score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][N];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<N;j++) array[i][j] = Integer.parseInt(str[j]);
        }

        solution();
        System.out.println(score);
    }

    public static void solution() {
        while(true) {
            Queue<V> blockGroup = getBlockGroup(); // 가장 큰 블록을 BFS 돌면서 가지고옴
            if(blockGroup.size()==0) return;
            score=score+(blockGroup.size()*blockGroup.size());
            while (!blockGroup.isEmpty()) {
                V v = blockGroup.remove();
                array[v.x][v.y]=-2; // 비어있다는 의미로 값을 세팅
            }
            excuteGravity();
            rotateArray();
            excuteGravity();
        }

        // 우선 일단 짜고 그 다음에 BFS 돌리는 부분 리팩토링 하자
    }

    private static Queue<V> getBlockGroup(){
        Queue<V> list = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]<=0) continue;
                Queue<V> result = bfs(i, j, array[i][j]); // 이거를 N*N 만큼 반복하는건 좋은 방법이 아닌 듯(고쳐야 할 듯)
                if(result.size()<2) continue;

                if(list.size()<result.size()){
                    list = result;
                }else if(list.size() == result.size()){
                    // 무지개 블록의 개수 > 기준 블록 행이 큰 것 > 기준 블록 열이 큰 것
                    int sum1 = (int) list.stream().filter(x->x.color==0).mapToInt(x->x.color).count();
                    int sum2 = (int) result.stream().filter(x->x.color==0).mapToInt(x->x.color).count();

                    if(sum1 < sum2) list = result;
                    else if(sum1 == sum2){
                        int x1 = list.peek().x;
                        int y1 = list.peek().y;
                        int x2 = result.peek().x;
                        int y2 = result.peek().y;
                        if(x1<x2) list = result;
                        else if(x1==x2 && y1<y2) list = result;

                    }
                }

            }
        }
        return list;
    }

    private static Queue<V> bfs(int startX, int startY,int startColor){
        Queue<V> queue = new LinkedList<>();
        Queue<V> result = new PriorityQueue<>();
        result.add(new V(startX, startY,startColor));
        queue.add(new V(startX,startY,startColor));
        boolean[][] check = new boolean[N][N];
        check[startX][startY]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(array[i][j]<=-1 || (array[i][j] != array[startX][startY] && array[i][j]!=0) ) continue; // 블록 색이 다른데 무지개 블록이 아닌 경우 continue
                    if(Math.abs(v.x - i) + Math.abs(v.y - j) == 1 && !check[i][j]){
                        check[i][j] = true;
                        queue.add(new V(i,j,array[i][j]));
                        result.add(new V(i,j,array[i][j]));
                    }
                }
            }
        }
        return result;
    }

    public static void excuteGravity(){ // 격자에 중력 작용
        //중력이란 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동한다. 이동은 다른 블록이나 격자의 경계를 만나기 전까지 계속됨
        while(true){
            int[][] temp = new int[N][N];
            for(int i=0;i<N;i++) for(int j=0;j<N;j++) temp[i][j]=array[i][j];

            for(int i=0;i<N;i++){
                for(int k=0;k<N;k++) {
                    for (int j = N-1; j >= 1; j--) {
                        if(array[j][i]!=-2 || array[j-1][i]==-1) continue;
                        array[j][i] = array[j-1][i];
                        array[j-1][i]=-2;
                    }
                }
            }

            if(isCompareArray(temp)) break;
        }
    }

    public static void rotateArray(){
        int[][] temp = new int[N][N];
        int x = -1;
        for(int j=N-1;j>=0;j--){
            x++;
            int y = 0;
            for(int i=0;i<N;i++){
                temp[x][y++] = array[i][j];
            }
        }
        array = temp;
    }

    private static boolean isCompareArray(int[][] temp) {
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) if(array[i][j]!=temp[i][j]) return false;
        return true;
    }

    public static void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(array[i][j]+"   ");
            }
            System.out.println();
        }
    }

}

class V implements Comparable<V>{
    int x;
    int y;
    int color;
    public V(int x,int y,int color){
        this.x=x;
        this.y=y;
        this.color=color;
    }

    @Override
    public int compareTo(V o) {
        if(o.color==this.color){
            if(this.x==o.x){
                return this.y-o.y;
            }
            return this.x-o.x;
        }
        return o.color-this.color;
    }
}

/* 검은색 블록은 -1이고 무지개 블록은 0이고 일반 블록은 1이상 M이하다
   |r1 - r2| + |c1 - c2| = 1 을 만족해야 그룹 짓기 가능
   그룹(2개 이상이며 일반 블록이 1개이상 있어야함) -> 검은색 블록X, 무지개 블록 O, 일반 블록은 1개 이상인데 만약 2개이상이면 모든 일반블록의 색깔은 같아야함
   기준블록은 일반 블록 중 행의 번호가 가장 작은 블록 이런게 여러개면 열의 번호가 가장 작은 블록임(이건 나중에 오토 플레이시 사용됨)

   오토플레이(아래의 과정을 반복한다. 단 블록 그룹이 없으면 끝난다 그리고 획득한 점수의 합을 출력한다)
   1. 크기가 가장 큰 블록 그룹을 찾음( 그런게 여러개면 무지개 블록 많은순 > 기준 블록의 행이 가장 큰 것 > 기준 블록의 열이 가장 큰 것 )
   2. 1에서 찾은 블록 그룹의 모든 블록을 제거함(B만큼 파괴하면 B^2 점수를 얻음)
   3. 격자에 중력이 작용함
   4. 격자가 90도 반시계 방향으로 회전
   5. 격자에 중력이 작용함
   -> 중력이란 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동한다. 이동은 다른 블록이나 격자의 경계를 만나기 전까지 계속됨

   생각할 때 BFS로 한 번 한 번 찾는건 불이익일듯 이것도 전처럼 비슷하게 BFS 값을 기록해 둬야 할 듯?
 */