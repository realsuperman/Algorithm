package BaekJoon_13975;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<N;i++){
            int size = Integer.parseInt(br.readLine());
            long[] array = new long[size];
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[j] = Integer.parseInt(str[j]);
            sb.append(solution(array)+"\n");
        }
        System.out.println(sb);
    }
    public static long solution(long[] array){
        Queue<Long> queue = new PriorityQueue<>();
        for(long value : array) queue.add(value);
        long value = 0;

        while(!queue.isEmpty()){
            if(queue.size()<=1){ break;}
            long from = queue.remove();
            long to = queue.remove();
            queue.add(from+to);
            value+=from+to;
        }
        return value;
    }
}