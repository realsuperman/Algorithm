package BaekJoon_11866;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int i=1;
        Queue<Integer> queue = new LinkedList<>();
        Iterator<Integer> iter;
        StringBuilder sb = new StringBuilder();

        while(i<=n){
            queue.offer(i);
            i++;
        }

        iter = queue.iterator();
        sb.append("<");
        while(!queue.isEmpty()){
            int su = 0;
            for(i=0;i<k;i++){
                try{
                    su = iter.next();
                }catch(Exception e){
                    iter = queue.iterator();
                    su = iter.next();
                }
            }
            sb.append(su+", ");
            iter.remove();
        }
        sb.deleteCharAt(sb.lastIndexOf(" "));
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(">");
        System.out.print(sb);
    }
}
