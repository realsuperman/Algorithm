package BaekJoon_1374;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<V> queue = new PriorityQueue<>();
        Queue<Room> queue2 = new PriorityQueue<>();

        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            queue.add(new V(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2])));
        }

        int result = 0;
        int[] array = new int[N+1];
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(!queue2.isEmpty()){
                if(v.start>=queue2.peek().value){
                    Room room = queue2.remove();
                    array[v.number] = room.roomNumber;
                    queue2.add(new Room(v.number,room.roomNumber,v.end));
                }else{
                    queue2.add(new Room(v.number,result+1,v.end));
                    array[v.number] = result+1;
                    result++;
                }
            }else if(queue2.isEmpty()){
                queue2.add(new Room(v.number,result+1,v.end));
                array[v.number] = result+1;
                result++;
            }
        }
        System.out.println(result);
        //for(int i=1;i<=N;i++) System.out.println(array[i]);
    }
}

class Room implements Comparable<Room>{
    int number;
    int roomNumber;
    int value;

    public Room(int number,int roomNumber,int value){
        this.number=number;
        this.roomNumber=roomNumber;
        this.value=value;
    }

    @Override
    public int compareTo(Room o) {
        return this.value-o.value;
    }
}

class V implements Comparable<V>{
    int number;
    int start;
    int end;

    public V(int number,int start,int end){
        this.number=number;
        this.start=start;
        this.end=end;
    }

    @Override
    public int compareTo(V o) {
        if(this.start==o.start) return this.end-o.end;
        return this.start-o.start;
    }

}