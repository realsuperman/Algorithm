package BaekJoon_16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N,M,K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);

        int[][] array = new int[N+1][N+1]; // 양분의 양
        int[][] plus = new int[N+1][N+1]; // S2D2가 공급할 양분
        V[][] trees = new V[N+1][N+1]; // 나무정보들

        for(int i=1;i<=N;i++) for(int j=1;j<=N;j++) array[i][j]=5;
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                plus[i+1][j+1] = Integer.parseInt(str[j]);
            }
        }
        for(int i=0;i<M;i++){
            str = br.readLine().split(" ");
            V v = new V(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
            trees[v.list.get(0).x][v.list.get(0).y] = v;

        }

        for(int i=0;i<K;i++) investment(trees,array,plus);
        int cnt = 0;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(trees[i][j]!=null){
                    cnt+=trees[i][j].list.size();
                }
            }
        }
        System.out.println(cnt);
    }
    public static void investment(V[][] trees,int[][] array,int[][] plus){
        for(int i=1;i<=N;i++){ // 봄과 여름
            for(int j=1;j<=N;j++){
                if(trees[i][j]!=null){ // 양분을 먹는다
                    LinkedList<Info> copy = new LinkedList<>();
                    LinkedList<Info> die = new LinkedList<>();
                    for(Info info : trees[i][j].list){
                        if(info.age<=array[i][j]){
                            array[i][j] -= info.age;
                            info.age+=1;
                            copy.add(info);
                        }else{
                            die.add(info);
                        }
                    }
                    trees[i][j].list = copy;
                    for(Info info : die){
                        array[i][j]+=info.age/2;
                    }
                }
            }
        }

        for(int i=1;i<=N;i++) { // 가을
            for (int j = 1; j <= N; j++) {
                if (trees[i][j] != null) {
                    for(Info info : trees[i][j].list){
                        if(info.age%5==0) {
                            int x, y;
                            if (info.x - 1 >= 1 && info.y - 1 >= 1) {
                                x = info.x - 1;
                                y = info.y - 1;
                                if (trees[x][y] == null) {
                                    V v = new V(x, y, 1);
                                    trees[x][y] = v;
                                } else {
                                    trees[x][y].add(x, y, 1);
                                }
                            }
                            if (info.x - 1 >= 1) {
                                x = info.x - 1;
                                y = info.y;
                                if (trees[x][y] == null) {
                                    V v = new V(x, y, 1);
                                    trees[x][y] = v;
                                } else {
                                    trees[x][y].add(x, y, 1);
                                }
                            }
                            if (info.x - 1 >= 1 && info.y + 1 <= N) {
                                x = info.x - 1;
                                y = info.y + 1;
                                if (trees[x][y] == null) {
                                    V v = new V(x, y, 1);
                                    trees[x][y] = v;
                                } else {
                                    trees[x][y].add(x, y, 1);
                                }
                            }
                            if (info.y - 1 >= 1) {
                                x = info.x;
                                y = info.y - 1;
                                if (trees[x][y] == null) {
                                    V v = new V(x, y, 1);
                                    trees[x][y] = v;
                                } else {
                                    trees[x][y].add(x, y, 1);
                                }
                            }
                            if (info.y + 1 <= N) {
                                x = info.x;
                                y = info.y + 1;
                                if (trees[x][y] == null) {
                                    V v = new V(x, y, 1);
                                    trees[x][y] = v;
                                } else {
                                    trees[x][y].add(x, y, 1);
                                }
                            }
                            if (info.x + 1 <= N && info.y - 1 >= 1) {
                                x = info.x + 1;
                                y = info.y - 1;
                                if (trees[x][y] == null) {
                                    V v = new V(x, y, 1);
                                    trees[x][y] = v;
                                } else {
                                    trees[x][y].add(x, y, 1);
                                }
                            }
                            if (info.x + 1 <= N) {
                                x = info.x + 1;
                                y = info.y;
                                if (trees[x][y] == null) {
                                    V v = new V(x, y, 1);
                                    trees[x][y] = v;
                                } else {
                                    trees[x][y].add(x, y, 1);
                                }
                            }
                            if (info.x + 1 <= N && info.y + 1 <= N) {
                                x = info.x + 1;
                                y = info.y + 1;
                                if (trees[x][y] == null) {
                                    V v = new V(x, y, 1);
                                    trees[x][y] = v;
                                } else {
                                    trees[x][y].add(x, y, 1);
                                }
                            }
                        }
                    }
                }
            }
        }

        for(int i=1;i<=N;i++){ // 겨울
            for(int j=1;j<=N;j++){
                array[i][j]+=plus[i][j];
            }
        }

    }
}

class V{
    LinkedList<Info> list;
    public V(int x,int y,int age){
        list = new LinkedList<>();
        list.add(new Info(x,y,age));
    }
    public void add(int x,int y,int age){
        list.addFirst(new Info(x,y,age));
    }
}

class Info{
    int x;
    int y;
    int age;
    boolean flag;
    public Info(int x,int y,int age){
        this.x=x;
        this.y=y;
        this.age=age;
        this.flag=true;
    }
}