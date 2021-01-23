package BaekJoon_18258;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        String[] array;
        StringBuilder sb = new StringBuilder();
        int cnt = Integer.parseInt(br.readLine());
        Queue stack = new Queue(cnt);

        for(int i=0;i<cnt;i++){
            str = br.readLine();
            array = str.split(" ",2);
            if("push".equals(array[0])){
                stack.push(Integer.parseInt(array[1]));
            }else if("pop".equals(array[0])){
                sb.append(stack.pop()).append('\n');
            }else if("size".equals(array[0])){
                sb.append(stack.size()).append('\n');
            }else if("empty".equals(array[0])){
                sb.append(stack.empty()).append('\n');
            }else if("front".equals(array[0])){
                sb.append(stack.front()).append('\n');
            }else{
                sb.append(stack.back()).append('\n');
            }
        }
        System.out.print(sb);
    }
}

class Queue{
    private int array[];
    private int front=0; // 삽입위치
    private int rear=0; // 삭제위치

    public Queue(int capacity){
        array = new int[capacity];
    }
    public void push(int x){
        array[front++] = x;
    }
    public int pop(){
        if(front==rear) return -1;
        return array[rear++];
    }
    public int size(){
        return front-rear;
    }
    public int empty(){
        if(front==rear) return 1;
        return 0;
    }
    public int front(){
        if(front==rear) return -1;
        return array[rear];
    }
    public int back(){
        if(front==rear) return -1;
        int f = front-1;
        return array[f];
    }
}