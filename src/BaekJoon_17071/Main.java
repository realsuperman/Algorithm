package BaekJoon_17071;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int start = Integer.parseInt(str[0]);
        int solution = Integer.parseInt(str[1]);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] check = new boolean[500001][2];
        check[start][0]=true;
        int time = 0;
        if(solution==start){System.out.println(0); return;}
        while(!queue.isEmpty()){
            int size = queue.size();
            time++;
            solution+=time;
            if(solution>500000) { System.out.println(-1); return; }

            for(int i=0;i<size;i++) {
                int value = queue.remove();
                if (value - 1 >= 0 && !check[value - 1][time % 2]) {
                    check[value-1][time%2] = true;
                    queue.add(value-1);
                }
                if (value + 1 <= 500000 && !check[value + 1][time % 2]) {
                    check[value+1][time%2] = true;
                    queue.add(value+1);
                }
                if (value * 2 <= 500000 && !check[value * 2][time % 2]) {
                    check[value*2][time%2] = true;
                    queue.add(value*2);
                }
            }
            if(check[solution][time%2]){System.out.println(time); return;}
        }
        System.out.println(-1);
    }
}