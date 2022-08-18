package BaekJoon_5014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int F = Integer.parseInt(str[0]); // 총층수
        int S = Integer.parseInt(str[1]); // 현재위치
        int G = Integer.parseInt(str[2]); // 타겟
        int U = Integer.parseInt(str[3]); // 올라가는 수
        int D = Integer.parseInt(str[4]); // 내려가는 수

        boolean[] check = new boolean[F+1];
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(S,0));
        check[S] = true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.depth==G) { System.out.println(v.cnt); return;}

            int check1 = v.depth+U;
            int check2 = v.depth-D;

            if(check1<=F && !check[check1]){
                check[check1]=true;
                queue.add(new V(check1,v.cnt+1));
            }
            if(check2>0 && !check[check2]){
                check[check2]=true;
                queue.add(new V(check2,v.cnt+1));
            }
        }

        System.out.println("use the stairs");

    }
}

class V{
    int depth;
    int cnt;
    public V(int depth,int cnt){
        this.depth=depth;
        this.cnt=cnt;
    }
}