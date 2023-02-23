package BaekJoon_14891.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int size;
    static int[][] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = 4;
        array = new int[size][8];
        for(int i=0;i<size;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        int N = Integer.parseInt(br.readLine());
        while(N-->0){
            String[] str = br.readLine().split(" ");
            solution(Integer.parseInt(str[0])-1, Integer.parseInt(str[1]));
        }

        int result = 0;
        int count=1;
        for(int i=0;i<size;i++){
            if(array[i][0]==1) result+=count;
            count*=2;
        }
        System.out.println(result);
    }
    public static void solution(int moveNumber, int sw){ // 1 -> 시계, -1 -> 반시계
        int[][] temp = new int[size][8];
        for(int i=0;i<size;i++) for(int j=0;j<8;j++) temp[i][j]=array[i][j];

        if(moveNumber==0){
            rotate(0, sw);
            left(temp,0, sw);
        }else if(moveNumber==size-1){
            rotate(size-1, sw);
            right(temp,size-1,sw);
        }else{
            rotate(moveNumber, sw);
            left(temp,moveNumber,sw);
            right(temp,moveNumber,sw);
        }
    }

    private static void left(int[][] temp, int index, int rotate) {
        while(index <size-1){
            rotate = rotate ==1?-1:1;
            if(temp[index][2]!= temp[index +1][6]) rotate(index +1, rotate);
            else break;

            index++;
        }
    }

    private static void right(int[][] temp, int index, int rotate){
        while(index>=1){
            rotate = rotate ==1?-1:1;
            if(temp[index][6]!=temp[index-1][2]) rotate(index-1, rotate);
            else break;

            index--;
        }
    }

    private static void rotate(int moveNumber, int sw) {
        if(sw==1) { // 시계
            int temp = array[moveNumber][7];
            for (int i = 7; i >= 1; i--) array[moveNumber][i] = array[moveNumber][i - 1];
            array[moveNumber][0] = temp;
        }else{ // 반시계
            int temp = array[moveNumber][0];
            for (int i = 0; i < 7; i++) array[moveNumber][i] = array[moveNumber][i + 1];
            array[moveNumber][7] = temp;
        }
    }
}