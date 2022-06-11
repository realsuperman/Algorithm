package BaekJoon_1941;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int cnt = 0;
    public static Character[][] array;
    static boolean[] check = new boolean[25];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        array = new Character[5][5];
        for(int i=0;i<5;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<5;j++) array[i][j] = str[j].charAt(0);
        }

        solution(0,0);
        System.out.println(cnt);
    }

    public static void solution(int x,int v) {
        if(v==7){
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<25;i++) if(check[i]) list.add(i+1);
            bfs(list);
            return;
        }

        for(int i=x;i<25;i++){
            if(check[i]) continue;
            check[i]=true;
            solution(i,v+1);
            check[i]=false;
        }
    }

    public static void bfs(List<Integer> list){
        Queue<V> queue = new LinkedList<>();

        for(int value : list){
            int dx = (int) Math.ceil(((double)value/5.0));
            int dy = value%5;
            queue.add(new V(dx-1,dy));
        }
        if(!connect(queue)) return;

        int size = 0;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(array[v.x][v.y]=='S') size++;
        }
        if(size>=4) cnt++;
    }

    public static boolean connect(Queue<V> queue){
        boolean[][] check = new boolean[5][5];

        Queue<V> test = new LinkedList<>();
        test.add(queue.peek());
        check[test.peek().x][test.peek().y] = true;

        while(!test.isEmpty()){
            V v = test.remove();
            int x = v.x;
            int y = v.y;

            if(x+1<5 && !check[x+1][y] && containValue(x+1,y,queue)){
                check[x+1][y] = true;
                test.add(new V(x+1,y));
            }
            if(x-1>=0 && !check[x-1][y] && containValue(x-1,y,queue)){
                check[x-1][y] = true;
                test.add(new V(x-1,y));
            }
            if(y+1<5 && !check[x][y+1] && containValue(x,y+1,queue)){
                check[x][y+1] = true;
                test.add(new V(x,y+1));
            }
            if(y-1>=0 && !check[x][y-1] && containValue(x,y-1,queue)){
                check[x][y-1] = true;
                test.add(new V(x,y-1));
            }
        }

        int num = 0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(check[i][j]) num++;
            }
        }
        if(num==7) return true;
        return false;
    }

    public static boolean containValue(int x,int y,Queue<V> queue){
        for(V v : queue) if(x==v.x && y==v.y) return true;
        return false;
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