package BaekJoon_11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        boolean[][] array = new boolean[N][N];
        boolean[] check = new boolean[N];
        for(int i=0;i<M;i++){
            str=br.readLine().split(" ");
            array[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1]=true;
            array[Integer.parseInt(str[1])-1][Integer.parseInt(str[0])-1]=true;
        }

        int cnt = 0;
        for(int i=0;i<N;i++){
            if(check[i]) continue;

            cnt++;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            check[i]=true;

            while(!queue.isEmpty()){
                int v = queue.remove();
                for(int index=0;index<N;index++){
                    if(array[v][index] && !check[index]){
                        check[index]=true;
                        queue.add(index);
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}