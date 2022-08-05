package BaekJoon_9328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static char[][] array;
    static String key;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int maxKeys;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T --> 0){
            String[] str = br.readLine().split(" ");
            N = Integer.parseInt(str[0]);
            M = Integer.parseInt(str[1]);
            array = new char[N][M];
            maxKeys = 0;
            for(int i=0;i<N;i++){
                str = br.readLine().split("");
                for(int j=0;j<str.length;j++){
                    array[i][j]=str[j].charAt(0);
                    if(array[i][j]=='$') maxKeys++;
                }
            }
            key = br.readLine();
            bfs();
        }
    }

    public static void bfs(){
        Set<Character> set = new HashSet<>(); // keys
        for(int i=0;i<key.length();i++) set.add(key.charAt(i));
        if(key.charAt(0)=='0') set = new HashSet<>();

        Queue<V> queue = new LinkedList<>();
        Queue<V> temp = new LinkedList<>();
        int result = 0;

        boolean[][] check = new boolean[N][M];
        border(check,set,queue,temp);

        while(result<maxKeys){
            if(queue.size()==0) break;

            while(!queue.isEmpty()){
                V v = queue.remove();
                if(array[v.x][v.y]=='$'){ array[v.x][v.y]='.'; result++;}

                for(int i=0;i<4;i++){
                    int x = dx[i]+v.x;
                    int y = dy[i]+v.y;
                    if(x<0 || x>=N || y<0 || y>=M ||  array[x][y]=='*' || check[x][y]) continue;

                    if(array[x][y]>64 && array[x][y]<91){
                        if(set.contains((char)(array[x][y]+32))){ // 키가 있는 경우
                            array[x][y]='.';
                            check[x][y]=true;
                            queue.add(new V(x,y));
                            continue;
                        }else{ // 키가 없는 경우
                            temp.add(new V(x,y));
                            continue;
                        }
                    }

                    if(array[x][y]>96 && array[x][y]<123){ // 키를 찾음
                        if(!set.contains(array[x][y])){
                            set.add(array[x][y]);

                            for(V value : temp){
                                if(array[value.x][value.y]=='.') continue;
                                char ch  = (char) (array[value.x][value.y]+32);
                                if(ch==array[x][y]){
                                    queue.add(new V(value.x,value.y));
                                    array[value.x][value.y]='.';
                                    check[value.x][value.y]=true;
                                }
                            }
                        }
                        array[x][y]='.';
                        queue.add(new V(x, y));
                        check[x][y] = true;
                        continue;
                    }

                    if(array[x][y]=='.' || array[x][y]=='$') {
                        queue.add(new V(x, y));
                        check[x][y] = true;
                    }

                }
            }
        }

        System.out.println(result);
    }

    public static void border(boolean[][] check,Set<Character> set,Queue<V> queue,Queue<V> temp){
        for(int i=0;i<M;i++){
            if(array[0][i]=='*' || check[0][i]) continue;
            if(array[0][i]>64 && array[0][i]<91 && !set.contains((char)(array[0][i]+32))){
                temp.add(new V(0,i));
                continue;
            }
            if(array[0][i]>96 && array[0][i]<123){
                if(!set.contains(array[0][i])){
                    set.add(array[0][i]);

                    for(V value : temp){
                        if(array[value.x][value.y]=='.') continue;
                        char ch  = (char) (array[value.x][value.y]+32);
                        if(ch==array[0][i]){
                            queue.add(new V(value.x,value.y));
                            array[value.x][value.y]='.';
                            check[value.x][value.y]=true;
                        }
                    }
                }
            }

            queue.add(new V(0,i));
            check[0][i]=true;
        }

        for(int i=0;i<M;i++){
            if(array[N-1][i]=='*' || check[N-1][i]) continue;
            if(array[N-1][i]>64 && array[N-1][i]<91 && !set.contains((char)(array[N-1][i]+32))){
                temp.add(new V(N-1,i));
                continue;
            }
            if(array[N-1][i]>96 && array[N-1][i]<123){
                if(!set.contains(array[N-1][i])){
                    set.add(array[N-1][i]);

                    for(V value : temp){
                        if(array[value.x][value.y]=='.') continue;
                        char ch  = (char) (array[value.x][value.y]+32);
                        if(ch==array[N-1][i]){
                            queue.add(new V(value.x,value.y));
                            array[value.x][value.y]='.';
                            check[value.x][value.y]=true;
                        }
                    }
                }
            }
            queue.add(new V(N-1,i));
            check[N-1][i]=true;
        }

        for(int i=0;i<N;i++){
            if(array[i][0]=='*' || check[i][0]) continue;
            if(array[i][0]>64 && array[i][0]<91 && !set.contains((char) (array[i][0]+32))){
                temp.add(new V(i,0));
                continue;
            }
            if(array[i][0]>96 && array[i][0]<123){
                if(!set.contains(array[i][0])){
                    set.add(array[i][0]);

                    for(V value : temp){
                        if(array[value.x][value.y]=='.') continue;
                        char ch  = (char) (array[value.x][value.y]+32);
                        if(ch==array[i][0]){
                            queue.add(new V(value.x,value.y));
                            array[value.x][value.y]='.';
                            check[value.x][value.y]=true;
                        }
                    }
                }
            }
            queue.add(new V(i,0));
            check[i][0]=true;
        }

        for(int i=0;i<N;i++){
            if(array[i][M-1]=='*'||check[i][M-1]) continue;
            if(array[i][M-1]>64 && array[i][M-1]<91 && !set.contains((char) (array[i][M-1]+32))){
                temp.add(new V(i,M-1));
                continue;
            }
            if(array[i][M-1]>96 && array[i][M-1]<123){
                if(!set.contains(array[i][M-1])){
                    set.add(array[i][M-1]);

                    for(V value : temp){
                        if(array[value.x][value.y]=='.') continue;
                        char ch  = (char) (array[value.x][value.y]+32);
                        if(ch==array[i][M-1]){
                            queue.add(new V(value.x,value.y));
                            array[value.x][value.y]='.';
                            check[value.x][value.y]=true;
                        }
                    }
                }
            }
            queue.add(new V(i,M-1));
            check[i][M-1]=true;
        }
    }
}

class V{
    int x;
    int y;
    public V(int x,int y){
        this.x=x;
        this.y=y;
    }
}