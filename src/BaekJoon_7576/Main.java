package BaekJoon_7576;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int column = Integer.parseInt(str[0]); int row = Integer.parseInt(str[1]);
        int[][] array = new int[row][column];
        boolean[][] check = new boolean[row][column];
        int value = 0;
        int i,j;

        for(i=0;i<row;i++){
            str = br.readLine().split(" ");
            for(j=0;j<str.length;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }
        if(isOk(array)){
            System.out.print(0);
            return;
        }

        Queue<String> queue = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();

        for(i=0;i<row;i++){
            for(j=0;j<column;j++){
                if(array[i][j] == 1){
                    queue.offer(i+","+j);
                }
            }
        }


        while(!queue.isEmpty()){
            str = queue.poll().split(",");
            i = Integer.parseInt(str[0]);
            j = Integer.parseInt(str[1]);

            if( (j-1>=0) && (array[i][j-1]==0) && (!check[i][j-1]) ){
                queue2.offer(i+","+(j-1));
                check[i][j-1] = true;
                array[i][j-1] = 1;
            }
            if( (j+1<column) && (array[i][j+1]==0) && (!check[i][j+1]) ){
                queue2.offer(i+","+(j+1));
                check[i][j+1] = true;
                array[i][j+1] = 1;
            }
            if( (i+1<row) && (array[i+1][j]==0) && (!check[i+1][j]) ){
                queue2.offer((i+1)+","+j);
                check[i+1][j] = true;
                array[i+1][j] = 1;
            }
            if( (i-1>=0) && (array[i-1][j]==0) && (!check[i-1][j]) ){
                queue2.offer((i-1)+","+j);
                check[i-1][j] = true;
                array[i-1][j] = 1;
            }

            if(queue.isEmpty()){
                queue.addAll(queue2);
                value++;
                queue2 = new LinkedList<>();
            }
        }

        if(!isOk(array)){
            System.out.print(-1);
            return;
        }
        System.out.print(value-1);
    }

    public static boolean isOk(int array[][]){
        boolean sw = true;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                if(array[i][j] == 0){
                    sw = false;
                    break;
                }
            }
            if(!sw) break;
        }
        return sw;
    }
}