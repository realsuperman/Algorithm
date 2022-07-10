package BaekJoon_15903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        Queue<Long> queue = new PriorityQueue<>();

        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) queue.add(Long.valueOf(str[i]));
        for(int i=0;i<M;i++){
            long from = queue.remove();
            long to = queue.remove();
            queue.add(from+to);
            queue.add(from+to);
        }

        long sum = 0;
        while(!queue.isEmpty()) sum+=queue.remove();
        System.out.println(sum);
    }
}
