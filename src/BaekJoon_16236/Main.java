package BaekJoon_16236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] array = new int[N][N];
        boolean[][] check = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < str.length; j++) {
                array[i][j] = Integer.parseInt(str[j]);
            }
        }

        PriorityQueue<Value> queue =new PriorityQueue<>();
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                if(array[i][j]==9){
                    array[i][j]=0;
                    check[i][j]=true;
                    Value v = new Value(i,j,0);
                    queue.add(v);
                    break;
                }
            }
        }

        int size = 2; // 아기상어 사이즈
        int value = 0; // size까지 가야함
        int time = 0; // 최종시간

        while(!queue.isEmpty()){
            Value v = queue.remove();
            check[v.x][v.y]=true;

            if(array[v.x][v.y]!=0&&array[v.x][v.y]<size){
                check = new boolean[N][N];
                time+=v.time;
                value++;
                if(value==size){
                    size++;
                    value=0;
                }
                array[v.x][v.y]=0;
                check[v.x][v.y]=true;
                queue = new PriorityQueue<>();
                queue.add(new Value(v.x,v.y,0));
                //}
            }else{
                if(v.x-1>=0 && !check[v.x - 1][v.y] && array[v.x-1][v.y]<=size){
                    Value temp = new Value(v.x-1,v.y,v.time+1);
                    check[v.x-1][v.y]=true;
                    queue.add(temp);
                }
                if(v.x+1<N && !check[v.x + 1][v.y] && array[v.x+1][v.y]<=size){
                    Value temp = new Value(v.x+1,v.y,v.time+1);
                    check[v.x+1][v.y]=true;
                    queue.add(temp);
                }

                if(v.y-1>=0 && !check[v.x][v.y - 1] && array[v.x][v.y-1]<=size){
                    Value temp = new Value(v.x,v.y-1,v.time+1);
                    check[v.x][v.y-1]=true;
                    queue.add(temp);
                }

                if(v.y+1<N && !check[v.x][v.y + 1] && array[v.x][v.y+1]<=size){
                    Value temp = new Value(v.x,v.y+1,v.time+1);
                    check[v.x][v.y+1]=true;
                    queue.add(temp);
                }
            }
        }
        System.out.println(time);
    }
}

class Value implements Comparable<Value>{
    int x;
    int y;
    int time;
    public Value(int x,int y,int time){
        this.x=x;
        this.y=y;
        this.time=time;
    }

    @Override
    public int compareTo(Value o) {
        if(o.time == this.time){
            if(o.x==this.x){
                return this.y-o.y;
            }else{
                return this.x-o.x;
            }
        }else{
            return this.time - o.time;
        }
    }
}