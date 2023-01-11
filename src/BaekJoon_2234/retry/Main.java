package BaekJoon_2234.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] array;
    static int[][] array2;
    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};
    static Map<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[1]);
        M = Integer.parseInt(str[0]);
        array = new int[N][M];
        array2 = new int[N][M];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++) array[i][j] = Integer.parseInt(str[j]);
        }

        solution();
    }

    public static void solution(){
        int max = Integer.MIN_VALUE;
        int max2;
        int cnt = 0;
        boolean[][] check = new boolean[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!check[i][j]){
                    cnt++;
                    check[i][j]=true;
                    int v = bfs(i,j,array[i][j],check,cnt);
                    max = max<v?v:max;
                    map.put(cnt, v);
                }
            }
        }
        max2 = max;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                List<Integer> list = new ArrayList<>();
                list.add(0); list.add(1); list.add(2); list.add(3);
                List<Integer> diff = parsingDirection(array[i][j]);
                list.removeAll(diff);

                for(int index=0;index<list.size();index++) {
                    int sum = 0;
                    for (int k = 0; k < list.size(); k++) {
                        if(index==k) continue;
                        sum+=Math.pow(2,list.get(k));
                    }

                    diff = parsingDirection(sum); // diff 재활용
                    for(int temp : diff){
                        int x = dx[temp] + i;
                        int y = dy[temp] + j;
                        if(x<0 || x>=N || y<0 || y>=M || array2[x][y]==array2[i][j]) continue;

                        int csh = map.get(array2[x][y])+map.get(array2[i][j]);
                        max2 = max2<csh?csh:max2;
                    }

                }

            }
        }

        System.out.println(cnt);
        System.out.println(max);
        System.out.println(max2);
    }

    public static int bfs(int x,int y,int direction,boolean[][] check,int number){
        int cnt = 1;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(x,y,direction));
        check[x][y]=true;
        array2[x][y] = number;

        while(!queue.isEmpty()){
            V v = queue.remove();

            List<Integer> list  = parsingDirection(v.direction);
            for(int value : list){
                int valX = dx[value] + v.x;
                int valY = dy[value] + v.y;
                if(valX<0 || valX>=N || valY<0 || valY>=M || check[valX][valY]) continue;
                array2[valX][valY] = number;
                check[valX][valY]=true;
                queue.add(new V(valX,valY,array[valX][valY]));
                cnt++;
            }
        }

        return cnt;
    }

    public static List<Integer> parsingDirection(int directionValue){
        List<Integer> result = new ArrayList<>();

        switch(directionValue){
            case 0 : result.add(3); result.add(2); result.add(1); result.add(0);break;
            case 1 : result.add(3); result.add(2); result.add(1);break;
            case 2 : result.add(3); result.add(2); result.add(0); break;
            case 3 : result.add(2); result.add(3); break;
            case 4 : result.add(3); result.add(1); result.add(0); break;
            case 5 : result.add(1); result.add(3); break;
            case 6 : result.add(3); result.add(0); break;
            case 7 : result.add(3);break;
            case 8 : result.add(0); result.add(1); result.add(2); break;
            case 9 : result.add(1); result.add(2); break;
            case 10 : result.add(0); result.add(2); break;
            case 11 : result.add(2); break;
            case 12 : result.add(0); result.add(1); break;
            case 13 : result.add(1);break;
            case 14 : result.add(0);break;
        }

        return result;
    }
}

class V{
    int x;
    int y;
    int direction;

    public V(int x,int y,int direction){
        this.x=x;
        this.y=y;
        this.direction=direction;
    }
}