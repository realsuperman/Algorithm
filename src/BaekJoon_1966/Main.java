package BaekJoon_1966;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<String> queue;
        int size;
        int findIndex;
        String[] str;
        StringBuilder sb = new StringBuilder();
        int printCnt;

        for(int i=0;i<n;i++){
            queue = new LinkedList<>();
            str = br.readLine().split(" ");
            size = Integer.parseInt(str[0]);
            findIndex = Integer.parseInt(str[1]);
            str = br.readLine().split(" ");
            printCnt = 0;

            for(int j=0;j<size;j++) queue.offer(str[j]+","+j);


            while(!queue.isEmpty()){
                if(isBig(queue)){ // 더 큰게 있다면
                    queue.offer(queue.poll());
                }else{
                    str = queue.poll().split(",");
                    printCnt++;

                    if(str[1].equals(String.valueOf(findIndex))){
                        sb.append(printCnt+"\n");
                        break;
                    }
                }
            }
        }
        System.out.print(sb);
    }
    public static boolean isBig(Queue queue){
        Iterator<String> iter;
        iter = queue.iterator();
        String[] str = iter.next().split(",");

        int value = Integer.parseInt(str[0]);
        boolean sw = false;

        while(iter.hasNext()){
            str = iter.next().split(",");
            if(Integer.parseInt(str[0]) > value){
                sw = true;
                break;
            }
        }
        return sw;
    }
}