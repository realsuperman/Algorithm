package BaekJoon_2164;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> queue = new LinkedList<>();

        int i=1;
        while(i<=n){
            queue.offer(i);
            i++;
        }

        while(queue.size()>1){
            queue.poll();
            i = queue.poll();
            queue.offer(i);
        }
        System.out.print(queue.peek());

    }
}