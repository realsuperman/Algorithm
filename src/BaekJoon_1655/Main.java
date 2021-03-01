package BaekJoon_1655;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<n;i++){
            int value = Integer.parseInt(br.readLine());
            if(minHeap.size() == maxHeap.size()){
                try {
                    if (minHeap.peek() < value) {
                        maxHeap.offer(minHeap.poll());
                        minHeap.offer(value);
                    } else {
                        maxHeap.offer(value);
                    }
                }catch(Exception e){
                    maxHeap.offer(value);
                }
            }
            else{
                try {
                    if (maxHeap.peek() > value) {
                        minHeap.offer(maxHeap.poll());
                        maxHeap.offer(value);
                    } else {
                        minHeap.offer(value);
                    }
                }catch(Exception e){
                    minHeap.offer(value);
                }
            }
            sb.append(maxHeap.peek()+"\n");
        }
        System.out.print(sb);
    }

}
