package BaekJoon_15662;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static boolean[] chk;
    public static int SIZE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        SIZE = Integer.parseInt(br.readLine());
        int[][] array = new int[SIZE][8];
        String[] str;

        for(int i=0;i<SIZE;i++){
            str = br.readLine().split("");
            for(int j=0;j<array[i].length;j++){
                array[i][j]= Integer.parseInt(str[j]);
            }
        }

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            chk = new boolean[SIZE];
            str = br.readLine().split(" ");
            boolean bool = Integer.parseInt(str[1])==1?true:false;
            move(array,Integer.parseInt(str[0])-1,bool);
        }

        int cnt = 0;
        for(int i=0;i<SIZE;i++) if(array[i][0]==1) cnt++;
        System.out.println(cnt);
    }

    public static void move(int[][] array,int rotate,boolean direction){ // true는 시계 false는 반시계
        chk[rotate]=true;
        if(rotate-1>=0 && !chk[rotate-1] &&equalValue(array,rotate,rotate-1,false)){
            move(array,rotate-1,!direction);
        }
        if(rotate+1<SIZE && !chk[rotate+1] && equalValue(array,rotate,rotate+1,true)){
            move(array,rotate+1,!direction);
        }

        if(direction) {
            int temp = array[rotate][7];
            for(int i=7;i>0;i--) array[rotate][i]=array[rotate][i-1];
            array[rotate][0]=temp;
        }else{
            int temp = array[rotate][0];
            for(int i=0;i<7;i++) array[rotate][i]=array[rotate][i+1];
            array[rotate][7]=temp;
        }
    }

    public static boolean equalValue(int[][] array,int target1,int target2,boolean sw){
        if(sw){ // +방향으로
            if(array[target1][2]!=array[target2][6]) return true;
        }else{ // -방향으로
            if(array[target1][6]!=array[target2][2]) return true;
        }
        return false;
    }
}