package BaekJoon_2178;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args)throws Exception {
        int[][] array;
        int i,j,value;
        boolean check[][];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ",2);

        int size = Integer.parseInt(str[0]);
        int size2 = Integer.parseInt(str[1]);
        array = new int[size][size2];
        check = new boolean[size][size2];
        String parse;

        for(i=0;i<size;i++){
            parse = br.readLine();
            for(j=0;j<parse.length();j++){
                array[i][j] = Integer.parseInt(String.valueOf(parse.charAt(j)));
                check[i][j] = false;
            }
        }
        Queue<Maze> queue = new LinkedList<>();
        Maze first = new Maze(0,0,1);
        queue.offer(first);

        while(!queue.isEmpty()){
            Maze m = queue.poll();
            i = m.getI();
            j = m.getJ();
            value = m.getValue();
            check[i][j] = true;

            if( (i == size-1) && (j == size2-1) ){
                System.out.println(value);
                break;
            }

            if( (j-1>=0) && (array[i][j-1]==1) && (!check[i][j-1]) ){
                queue.offer(new Maze(i,j-1,value+1));
                check[i][j-1] = true;
            }
            if( (j+1<size2) && (array[i][j+1]==1) && (!check[i][j+1]) ){
                queue.offer(new Maze(i,j+1,value+1));
                check[i][j+1] = true;
            }
            if( (i+1<size) && (array[i+1][j]==1) && (!check[i+1][j]) ){
                queue.offer(new Maze(i+1,j,value+1));
                check[i+1][j] = true;
            }
            if( (i-1>=0) && (array[i-1][j]==1) && (!check[i-1][j]) ){
                queue.offer(new Maze(i-1,j,value+1));
                check[i-1][j] = true;
            }
        }

    }
}

class Maze{
    private int i; // i좌표
    private int j; // j좌표
    private int value; // 누적 경로 값
    public Maze(int i,int j,int value){
        this.i = i;
        this.j = j;
        this.value = value;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getValue() {
        return value;
    }
}