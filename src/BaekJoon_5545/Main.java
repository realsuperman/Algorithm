package BaekJoon_5545;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int value = Integer.parseInt(str[0]); // 도우가격
        int extraValue = Integer.parseInt(str[1]); // 토핑가격

        int result = Integer.parseInt(br.readLine()); // 열량의 합
        int sum = result/value;

        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<N;i++) queue.add(Integer.valueOf(br.readLine()));

        int cnt = 0;
        int s = 0;
        while(!queue.isEmpty()){
            cnt++;
            int v = extraValue*cnt; // 누적토핑가격
            s+=queue.remove(); // 누적토핑열량

            int temp = (result+s)/(value+v);
            if(temp>=sum) sum = temp;
            else break;
        }

        System.out.println(sum);
    }
}