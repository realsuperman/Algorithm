package BaekJoon_1038;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N<10){System.out.println(N); return;}

        int cnt = 10;
        Queue<Long> queue = new PriorityQueue<>();
        for(int i=1;i<=9;i++){
            for(int j=0;j<i;j++){
                String str = i+""+j;
                queue.add(Long.valueOf(str));
            }
        }
        while(!queue.isEmpty()){
            long value = queue.remove();
            if(cnt==N){System.out.println(value); return;}
            StringBuilder sb = new StringBuilder(String.valueOf(value));
            int last = sb.charAt(sb.length()-1)-48;

            for (int i = last-1; i >= 0; i--) {
                queue.add(Long.valueOf((sb.toString()+""+i)));
            }
            cnt++;
        }
        System.out.println(-1);
    }
}
